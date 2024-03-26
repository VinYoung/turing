package vip.vinyoung.account.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {
    String checkUserName(String account);
}
