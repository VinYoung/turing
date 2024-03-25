package vip.vinyoung.account.params.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import vip.vinyoung.tools.annotation.validation.Password;
import vip.vinyoung.tools.annotation.validation.UserName;
import vip.vinyoung.tools.config.valication.group.RegisterGroup;

@Data
@ApiModel("用户基本入参")
public class UserParam {
    /**
     * 用户名，注册时校验，登录时不校验
     */
    @UserName(groups = RegisterGroup.class)
    @JsonProperty("user_name")
    private String userName;

    /**
     * 密码
     */
    @Password
    @JsonProperty("password")
    private String password;
}
