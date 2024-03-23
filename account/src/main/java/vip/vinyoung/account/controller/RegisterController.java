package vip.vinyoung.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.vinyoung.account.dao.UserInfoDao;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.bean.mail.MailBasic;
import vip.vinyoung.tools.service.Log;
import vip.vinyoung.tools.service.MailService;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "注册中心")
@RequestMapping("/register-center")
public class RegisterController implements Log {
    @Override
    public Logger getLogger() {
        return log;
    }

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    MailService mailService;

    @Operation(summary = "注册账号")
    @PostMapping("/register")
    public CommonResult search(@RequestBody @Validated UserParam param) {
        // info("Account registration interface input parameters {}",
        //     param.getUserName()); // 账号体中绝大部分信息属于敏感信息，不打印入参的其他参数的日志
        List<String> tos = new ArrayList<>();
        tos.add(param.getEmail());
        MailBasic mail = new MailBasic(tos, "测试", "我爱你");
        mailService.send(mail);
        return CommonResult.success();
    }
}
