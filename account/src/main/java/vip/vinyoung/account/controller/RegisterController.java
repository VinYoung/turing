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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.vinyoung.account.params.UserDetailParam;
import vip.vinyoung.account.service.RegisterService;
import vip.vinyoung.tools.annotation.validation.Email;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.config.valication.group.DefaultGroup;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.utils.CommonUtils;

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
    RegisterService registerService;

    @Operation(summary = "邮箱校验并发送验证码")
    @PostMapping("/mail/code")
    public CommonResult mailCode(@RequestParam @Email String mailAddr) {
        info("Account registration interface input parameters {}", mailAddr);
        return registerService.mailCode(mailAddr);
    }

    @Operation(summary = "注册账号")
    @PostMapping("/register")
    public CommonResult search(@RequestBody @Validated( {DefaultGroup.class}) UserDetailParam param) {
        info("Account registration interface input parameters {}", CommonUtils.toJson(param));
        return registerService.register(param);
    }
}
