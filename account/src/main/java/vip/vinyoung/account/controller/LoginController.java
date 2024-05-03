package vip.vinyoung.account.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.account.service.LoginService;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.config.valication.group.DefaultGroup;
import vip.vinyoung.tools.service.Log;

@Slf4j
@RestController
@Tag(name = "登录中心")
@RequestMapping("/login-center")
public class LoginController implements Log {
    @Override
    public Logger getLogger() {
        return log;
    }

    @Resource
    LoginService loginService;

    @Operation(summary = "登录，并返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Validated( {DefaultGroup.class}) UserParam param) {
        info("Login interface input parameters {}", param.getAccount()); // 账号体中绝大部分信息属于敏感信息，不打印入参的其他参数的日志
        return loginService.login(param);
    }

    @ApiOperation("账号存在校验接口")
    @Operation(summary = "</br>账号存在校验，用户可以使用邮箱或者用户名进行登录，服务测要区分邮箱还是用户名进行校验。校验结果返回前端true，</br>"
        + "</br>校验不通过则抛出账号不存在的异常</br>")
    @GetMapping("/check")
    public CommonResult check(@RequestParam("account") String account) {
        info("Account verification interface input parameters {}", account);
        return loginService.check(account);
    }
}
