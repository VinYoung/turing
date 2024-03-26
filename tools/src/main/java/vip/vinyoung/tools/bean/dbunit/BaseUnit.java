package vip.vinyoung.tools.bean.dbunit;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 数据库数据基础表
 *
 * @author Vin.Young
 * @since 2024-03-24
 */
@Data
public class BaseUnit {
    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
