package vip.vinyoung.account.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vip.vinyoung.account.bean.dbunit.TUser;
import vip.vinyoung.account.dao.UserContactInfoDao;
import vip.vinyoung.account.dao.UserInfoDao;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.account.service.LoginService;
import vip.vinyoung.account.task.UnlockUserTimerTask;
import vip.vinyoung.cache.service.CacheHelper;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.bean.jwt.JwtTokenBasic;
import vip.vinyoung.tools.config.ErrorConstants;
import vip.vinyoung.tools.enums.AccountEnum;
import vip.vinyoung.tools.enums.ParameterEnum;
import vip.vinyoung.tools.enums.UserStatusEnum;
import vip.vinyoung.tools.exception.AuthLoginException;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.utils.CommonUtils;
import vip.vinyoung.tools.utils.SecurityUtils;
import vip.vinyoung.tools.utils.TokenUtils;
import java.time.LocalDateTime;
import java.util.Timer;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService, Log {
    @Value("${account.user.lock_times:5}")
    private String userErrorTimes;

    @Value("${account.user.lock_time:5}")
    private String userErrorLockTime;

    @Resource
    private UserContactInfoDao userContactInfoDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private CacheHelper cacheHelper;

    @Resource
    private TokenUtils tokenUtils;

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public CommonResult login(UserParam param) {
        String account = param.getAccount();
        AccountEnum accountEnum = CommonUtils.guessAccount(account);
        TUser user = queryUser(account, accountEnum);
        if (user == null) {
            throw new AuthLoginException(ErrorConstants.NOT_ALLOW, HttpStatus.FORBIDDEN);
        }
        if (user.getStatus() < UserStatusEnum.NORMAL.getCode()) {
            throw new AuthLoginException(ErrorConstants.ACCOUNT_STATUS, HttpStatus.FORBIDDEN);
        }
        String encryptPwd = SecurityUtils.passwordEncrypt(param.getPassword());
        String userId = userInfoDao.checkPassword(user.getUserId(), encryptPwd);
        if (StringUtils.isNotEmpty(userId)) {
            // 登录成功, 清空失败次数
            cacheHelper.remove(ParameterEnum.USER.name(), CommonUtils.getLoginPasswordErrorRedisKey(userId));
            String token = tokenUtils.createToken(new JwtTokenBasic().setSubject(user.getUserId()));
            userInfoDao.updateLoginTime(user.getUserId(), LocalDateTime.now());
            return CommonResult.success(token);
        }

        // 登录密码错误
        userId = user.getUserId();
        int times = getPasswordErrorTimes(userId);
        info("用户: {} 第 {} 次登录密码错误.", userId, times);
        if (times >= Integer.parseInt(userErrorTimes)) { // 1分钟内错误次数大于上限
            error("用户 {} 登录失败次数过多, 锁定用户 {} 分钟", userId, userErrorLockTime);
            // 锁定用户, lockTime分钟后解锁
            userInfoDao.lockUser(userId);
            new Timer().schedule(new UnlockUserTimerTask(userId), Integer.parseInt(userErrorLockTime) * 60 * 1000L);
        }
        throw new AuthLoginException(ErrorConstants.PASSWORD_INCORRECT, HttpStatus.FORBIDDEN);
    }

    @Override
    public CommonResult check(String account) {
        AccountEnum accountEnum = checkAccount(account);
        return CommonResult.success(!AccountEnum.NONE.equals(accountEnum));
    }

    public int getPasswordErrorTimes(String userId) {
        String key = CommonUtils.getLoginPasswordErrorRedisKey(userId);
        String timesCache = cacheHelper.get(ParameterEnum.USER.name(), key, String.class);
        int times = StringUtils.isEmpty(timesCache) ? 1 : Integer.parseInt(timesCache) + 1;
        cacheHelper.put(ParameterEnum.USER.name(), key, String.valueOf(times), 60);
        return times;
    }

    private TUser queryUser(String account, AccountEnum accountEnum) {
        if (AccountEnum.EMAIL.equals(accountEnum)) {
            String user_id = userContactInfoDao.checkEmail(account);
            return userInfoDao.queryById(user_id);
        } else {
            return userInfoDao.queryByUserName(account);
        }
    }

    private AccountEnum checkAccount(String account) {
        AccountEnum accountEnum = CommonUtils.guessAccount(account);
        String user_id;
        if (AccountEnum.EMAIL.equals(accountEnum)) {
            user_id = userContactInfoDao.checkEmail(account);
        } else {
            user_id = userInfoDao.checkUserName(account);
        }
        if (StringUtils.isEmpty(user_id)) {
            accountEnum = AccountEnum.NONE;
        }
        return accountEnum;
    }
}
