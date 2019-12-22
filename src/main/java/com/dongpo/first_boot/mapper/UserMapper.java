package com.dongpo.first_boot.mapper;

import com.dongpo.first_boot.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user(account_id,avatar_url,name,token,gmt_create,gmt_modified) values(#{accountId},#{avatarUrl},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User selectByToken(@Param("token") String token);

    @Select(("select * from user where id=#{id}"))
    User selectById(@Param("id") Integer id);
}
