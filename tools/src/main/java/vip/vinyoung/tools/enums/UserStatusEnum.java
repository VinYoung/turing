package vip.vinyoung.tools.enums;

import lombok.Getter;

/**
 * 用户账号状态枚举
 *
 * @author Vin.Young
 * @since 2024-03-24
 */
@Getter
public enum UserStatusEnum {
    NORMAL(0, "普通用户"),
    DISABLE(-1, "用户被禁用"),
    LOCK(-2, "用户被锁定"),
    UNDONE(1, "用户注册流程未完成"),
    SUPER_ADMIN(9, "超级账号");

    private int code;

    private String explain;

    UserStatusEnum(int code, String explain) {
        this.code = code;
        this.explain = explain;
    }
}
