package com.magnus.managee.main.business.mappers;

import com.magnus.managee.main.business.entity.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.ehcache.EhcacheCache;

import java.util.List;
import java.util.Map;

@Mapper
@CacheNamespaceRef(name = "cache")
public interface UserMapper {

    @Select("select * from magnus")
    public List<Map> getUser();

    @Insert("insert into magnus(id, name,description) values(#{id}, #{name}, #{description})")
    public void insertUser(Map user);

    @Select("select * from magnus where id=#{id}")
    @Options(useCache = true)
    public User getUserById(Integer id);
}
