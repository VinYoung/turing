package vip.vinyoung.account.bean.dbunit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.vinyoung.tools.bean.dbunit.BaseUnit;
import java.time.LocalDateTime;

/**
 * 用户主表;
 *
 * @author vinyoung
 * @since 2024-3-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TUser extends BaseUnit {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户对于的微信账号id
     */
    private String wechatId;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 有效时间
     */
    private LocalDateTime effectiveTime;

    /**
     * 评论、备注
     */
    private String comment;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

}
