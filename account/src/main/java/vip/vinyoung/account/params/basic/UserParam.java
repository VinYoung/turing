package vip.vinyoung.account.params.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import vip.vinyoung.tools.annotation.validation.Password;
import vip.vinyoung.tools.annotation.validation.UserName;
import vip.vinyoung.tools.config.valication.ValicationConstants;

@Data
@ApiModel("用户基本入参")
public class UserParam {
    /**
     * 用户名
     */
    @UserName
    @JsonProperty("user_name")
    private String userName;

    /**
     * 账号，用于既可以使用邮箱，又可以使用用户名的场景
     */
    @NotEmpty(message = ValicationConstants.VALIDATE_NOTNULL)
    private String account;

    /**
     * 密码
     */
    @Password
    @JsonIgnore
    @JsonProperty("password")
    private transient String password;
}
