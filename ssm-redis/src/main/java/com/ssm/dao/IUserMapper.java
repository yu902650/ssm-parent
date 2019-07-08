package com.ssm.dao;

import com.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 项目名:      parent
 * 包名:        com.ssm.dao
 * 创建时间:    2019/3/20 11:31
 *
 * @author: Bobo_Yu
 * 描述:
 */
@Repository
public interface IUserMapper {

    User findById(@Param("id") String id);

    void insertOne(@Param("params") Map<String, Object> params);

    List<User> findMore(Map<String, Object> map);

    void save(User user);

    void insertBatchList(@Param("userList") List<User> userList);

    void insert(User user);

    List<User> findAll();
}
