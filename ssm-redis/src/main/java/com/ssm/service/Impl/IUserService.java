package com.ssm.service.Impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ssm.dao.IUserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名:      parent
 * 包名:        com.ssm.service.Impl
 * 创建时间:    2019/3/21 20:41
 *
 * @author: Bobo_Yu
 * 描述:
 */
@Transactional
@Service
public class IUserService implements UserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public Map<String, Object> findById(String id) {
        Map<String, Object> respMap = new HashMap<>(16);
        User user = userMapper.findById(id);
        respMap.put("user", user);
        return respMap;
    }

    @Override
    public void insertOne(Map<String, Object> params) {
        userMapper.insertOne(params);
    }

    @Override
    public Map<String, Object> findMore(Map<String, Object> map) {
        Map<String, Object> respMap = new HashMap<>(16);
        List<User> userList = userMapper.findMore(map);
        respMap.put("userList", userList);
        return respMap;
    }

    @Override
    public void batchList(List<User> userList) {
        userMapper.insertBatchList(userList);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        if (userList.size() > 0) {
        }
        return null;
    }
}
