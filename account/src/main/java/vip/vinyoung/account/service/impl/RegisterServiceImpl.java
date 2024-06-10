package vip.vinyoung.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.vinyoung.account.dao.UserContactInfoDao;
import vip.vinyoung.account.params.UserDetailParam;
import vip.vinyoung.account.service.RegisterService;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.service.MailService;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService, Log {
    @Override
    public Logger getLogger() {
        return log;
    }

    @Autowired
    private MailService mailService;

    @Autowired
    private UserContactInfoDao userContactInfoDao;

    @Override
    public CommonResult<String> mailCode(String mailAddr) {
        // String checkResult = userContactInfoDao.checkEmail(mailAddr);
        // if (StringUtils.isNotEmpty(checkResult)) {
        //     return CommonResult.failed(ErrorCode);
        // }
        // mailService.send();
        return null;
    }

    @Override
    public CommonResult<String> register(UserDetailParam param) {

        return null;
    }
}
