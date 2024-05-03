package vip.vinyoung.account.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.tools.annotation.validation.Email;
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
     * 邮箱
     */
    @Email(groups = RegisterGroup.class)
    @JsonProperty("email")
    private String email;

    /**
     * 用户等于用的微信账号id
     */
    @JsonProperty("wechat_id")
    private String wechatId;

    /**
     * 状态
     */
    @JsonProperty("status")
    private String status;

    /**
     * 有效时间
     */
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
