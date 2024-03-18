package vip.vinyoung.account.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import vip.vinyoung.tools.annotation.validation.Password;
import vip.vinyoung.tools.config.ValicationConstants;

@Data
@ApiModel("用户基本入参")
public class UserParam {
    /**
     * 用户ID
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 用户名
     */
    @NotNull(message = ValicationConstants.VALIDATE_NOTNULL)
    @JsonProperty("user_name")
    private String userName;

    /**
     * 用户等于用的微信账号id
     */
    @JsonProperty("wechat_id")
    private String wechatId;

    /**
     * 密码
     */
    @NotNull(message = ValicationConstants.VALIDATE_NOTNULL)
    @Password
    @JsonProperty("password")
    private String password;

    /**
     * 状态
     */
    @JsonProperty("status")
    private String status;

    /**
     * 有效时间
     */
    @NotNull(message = ValicationConstants.VALIDATE_NOTNULL)
    @JsonProperty("effective_time")
    private String effectiveTime;

    /**
     * 邮箱
     */
    @NotNull(message = ValicationConstants.VALIDATE_NOTNULL)
    @JsonProperty("email")
    private String email;

    /**
     * 国际区号
     */
    @JsonProperty("area_code")
    private String areaCode;

    /**
     * 电话号码
     */
    @JsonProperty("phone")
    private String phone;
}
