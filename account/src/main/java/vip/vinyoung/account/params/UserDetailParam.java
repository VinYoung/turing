package vip.vinyoung.account.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.tools.annotation.validation.Password;
import vip.vinyoung.tools.annotation.validation.UserName;
import vip.vinyoung.tools.config.valication.ValicationConstants;
import vip.vinyoung.tools.config.valication.group.RegisterGroup;

@Data
@ApiModel("用户基本入参")
public class UserDetailParam extends UserParam {
    /**
     * 用户ID
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 用户名
     */
    @UserName(groups = RegisterGroup.class)
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
    @NotNull(message = ValicationConstants.VALIDATE_NOTNULL, groups = RegisterGroup.class)
    @JsonProperty("effective_time")
    private String effectiveTime;

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
