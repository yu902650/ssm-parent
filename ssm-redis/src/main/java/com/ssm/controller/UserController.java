package com.ssm.controller;

import com.ssm.common.Constants;
import com.ssm.common.Result;
import com.ssm.dao.IUserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名:      parent
 * 包名:        com.ssm.controller
 * 创建时间:    2019/3/20 11:25
 *
 * @author: Bobo_Yu
 * 描述:
 * <p>
 * 数组.
 * 集合
 * map
 * 实体
 * 单个参数
 * 多个不同类型参数
 */
@RestController
@RequestMapping(value = "/user", produces = "text/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private IUserMapper iUserMapper;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        return "in this program!";
    }

    //新增User   OK
    @RequestMapping(value = "/insertOne", method = RequestMethod.POST)
    public String insertOne(@RequestBody Map<String, Object> params) {
        service.insertOne(params);
        return Result.ok(200, "success", "新增user成功");
    }

    //入参一个
    @RequestMapping(value = "/getOneParam")
    @ResponseBody
    public String getOneParam(@RequestParam(required = true) String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(Constants.UNKNOW_ERROR, "ERROR", "缺少必填参数");
        }
        Map<String, Object> respMap = service.findById(id);
        return Result.ok("200", respMap);
    }

    /**
     * 入参两个或者多个
     *
     * @param name
     * @param phoneNo
     * @return
     */
    @RequestMapping("/findMore")
    @ResponseBody
    public String findMore(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String phoneNo) {
        Map<String, Object> map = new HashMap<>(16);
        if (!StringUtils.isEmpty(name)) {
            map.put("name", name);
        }
        if (!StringUtils.isEmpty(phoneNo)) {
            map.put("phoneNo", phoneNo);
        }
        Map<String, Object> respMap = service.findMore(map);
        return Result.ok("200", respMap);
    }

    /**
     * save for 循环一个个来
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String requestList(@RequestParam("listParam[]") List<String> param) {
        if(param.size()>0){
            for (String id : param){
                System.out.println("id : " + id);
            }
        }
        return "Request successful. Post param : List<String> - " + param.toString();
    }

    /**
     * SpringMVC 入参 List 对象
     * mybatis 批量插入
     *
     * @param userList
     * @return
     */
    @RequestMapping(value = "/batchList", method = RequestMethod.POST)
    @ResponseBody
    public String batchList(@RequestBody List<User> userList) {
        service.batchList(userList);
        return Result.ok("200", "success");
    }

    /**
     * 傻吊方法,for 循环插入
     * for循环
     */
    @RequestMapping(value = "/insertForMethod", method = RequestMethod.POST)
    @ResponseBody
    public String insertForMethod(@RequestBody List<User> userList) {
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                iUserMapper.insert(userList.get(i));
            }
            return Result.ok("200", "success");
        } else {
            return Result.ok("200", "参数为空,请检查参数");
        }
    }

    /**
     * findAll
     */
    @RequestMapping(value = "/findAllInfo")
    @ResponseBody
    public String findAllInfo() {
        List<User> userList = service.findAll();
        return Result.ok("200", userList.size());
    }

}










