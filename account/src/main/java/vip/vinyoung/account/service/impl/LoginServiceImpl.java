package vip.vinyoung.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vip.vinyoung.account.bean.dbunit.TUser;
import vip.vinyoung.account.dao.UserContactInfoDao;
import vip.vinyoung.account.dao.UserInfoDao;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.account.service.LoginService;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.enums.AccountEnum;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.utils.CommonUtils;

@Slf4j
public class LoginServiceImpl implements LoginService, Log {
    @Autowired
    private UserContactInfoDao userContactInfoDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public CommonResult login(UserParam param) {
        String account = param.getAccount();
        AccountEnum accountEnum = CommonUtils.guessAccount(account);
        TUser user;
        if (AccountEnum.EMAIL.equals(accountEnum)) {
            String user_id = userContactInfoDao.checkEmail(account);
        } else {
            user = userInfoDao.checkUserName(account);
        }
        return null;
    }

    @Override
    public CommonResult check(String account) {
        AccountEnum accountEnum = checkAccount(account);
        return CommonResult.success(!AccountEnum.NONE.equals(accountEnum));
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
