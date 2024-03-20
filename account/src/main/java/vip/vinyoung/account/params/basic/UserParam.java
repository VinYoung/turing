package vip.vinyoung.account.params.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import vip.vinyoung.tools.annotation.validation.Email;
import vip.vinyoung.tools.annotation.validation.Password;
import vip.vinyoung.tools.annotation.validation.UserName;
import vip.vinyoung.tools.config.valication.ValicationConstants;
import vip.vinyoung.tools.config.valication.group.RegisterGroup;

@Data
@ApiModel("用户基本入参")
public class UserParam {
    /**
     * 邮箱
     */
    @Email(groups = RegisterGroup.class)
    @JsonProperty("email")
    private String email;
}
