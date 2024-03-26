package vip.vinyoung.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserContactInfoDao {
    String checkEmail(@Param("email") String email);
}
