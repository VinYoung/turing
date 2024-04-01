package vip.vinyoung.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.vinyoung.account.bean.dbunit.TUser;
import vip.vinyoung.tools.bean.basic.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserInfoDao {
    String checkUserName(String account);

    /**
     * 通过用户id查询数据
     *
     * @param userId 用户id
     * @return 查询到的用户数据
     */
    TUser queryById(@Param("userId") String userId);

    /**
     * 通过用户名查询数据
     *
     * @param userName 用户名
     * @return 查询到的用户数据
     */
    TUser queryByUserName(@Param("userName") String userName);

    /**
     * 校验密码是否相同
     *
     * @param userName 用户名
     * @param password 密码
     * @return 查询回来的用户id
     */
    String checkPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 分页查询指定行数据
     *
     * @param tUser    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(TUser tUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tUser 查询条件
     * @return 总行数
     */
    long count(TUser tUser);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(TUser tUser);

    /**
     * 批量新增数据
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TUser> entities);

    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TUser> entities);

    /**
     * 更新数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

    void unlockUser(@Param("userId") String userId);

    void lockUser(@Param("userId") String userId);

    /**
     * 更新最后登录时间
     *
     * @param userId 用户id
     * @param lastLoginTime 最后登录时间
     */
    void updateLoginTime(@Param("userId") String userId, @Param("lastLoginTime") LocalDateTime lastLoginTime);
}
