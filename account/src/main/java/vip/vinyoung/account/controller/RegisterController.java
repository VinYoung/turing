package vip.vinyoung.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.vinyoung.account.dao.UserInfoDao;
import vip.vinyoung.account.params.UserDetailParam;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.config.valication.group.DefaultGroup;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.service.MailService;

@Slf4j
@RestController
@Tag(name = "注册中心")
@RequestMapping("/register-center")
public class RegisterController implements Log {
    @Override
    public Logger getLogger() {
        return log;
    }

    @Resource
    UserInfoDao userInfoDao;

    @Resource
    MailService mailService;

    @Operation(summary = "注册账号")
    @PostMapping("/register")
    public CommonResult search(@RequestBody @Validated( {DefaultGroup.class}) UserDetailParam param) {
        info("Account registration interface input parameters {}",
            param.getUserName()); // 账号体中绝大部分信息属于敏感信息，不打印入参的其他参数的日志
        return CommonResult.success();
    }
}
