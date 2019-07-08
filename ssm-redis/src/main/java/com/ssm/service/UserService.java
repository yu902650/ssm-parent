package com.ssm.service;

import com.ssm.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 项目名:      parent
 * 包名:        com.ssm.service
 * 创建时间:    2019/3/21 20:41
 *
 * @author: Bobo_Yu
 * 描述:
 */
public interface UserService {

    Map<String, Object> findById(String id);

    void insertOne(Map<String, Object> params);

    Map<String, Object> findMore(Map<String, Object> map);

    void batchList(List<User> userList);

    List<User> findAll();
}
