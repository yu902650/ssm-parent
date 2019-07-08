package com.test.ForMethod;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2019/4/12 21:15
 */
public class ForMethod1 {

    public static void main(String[] args) {
        List<ModelBo> modelList = new ArrayList<>();
        ModelBo model = new ModelBo();
        model.setId(4);
        model.setParentId(0);

        modelList.add(model);

        ModelBo m = new ModelBo();
        m.setId(12);
        m.setParentId(0);
        modelList.add(m);


        List<RoleMenuVo> menuVos = new ArrayList<>();
//      {"desc":"user:list","modelId":12,"modelName":"用户管理","pName":"用户列表","parentId":2,"pid":46,"sort":0,"url":"bakend/user/list"}
        RoleMenuVo vo = new RoleMenuVo();
        vo.setDesc("user:list");
        vo.setModelId(12);
        vo.setModelName("用户管理");
        vo.setpName("用户列表");
        vo.setParentId(2);
        vo.setPid(46);
        vo.setUrl("bakend/user/list");
        menuVos.add(vo);

//      {"desc":"user:add","modelId":12,"modelName":"用户管理","pName":"用户添加","parentId":2,"pid":47,"sort":0,"url":"bakend/user/add"}
        RoleMenuVo v = new RoleMenuVo();
        v.setDesc("user:add");
        v.setModelId(12);
        v.setModelName("用户管理");
        v.setpName("用户添加");
        v.setParentId(2);
        v.setPid(47);
        v.setUrl("bakend/user/add");
        menuVos.add(v);

//      {"desc":"customer:list","modelId":14,"modelName":"客户管理","pName":"客户列表","parentId":4,"pid":54,"sort":0,"url":"customer/list"}
        RoleMenuVo v1 = new RoleMenuVo();
        v1.setDesc("customer:list");
        v1.setModelId(4);
        v1.setModelName("客户管理");
        v1.setpName("客户列表");
        v1.setParentId(4);
        v1.setPid(54);
        v1.setUrl("customer/list");
        menuVos.add(v1);


        List<ModelBo> menus = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {
            ModelBo record = modelList.get(i);
            List<RoleMenuVo> voList = new ArrayList<>();
            for (int j = 0; j < menuVos.size(); j++) {
                RoleMenuVo roleMenuVo = menuVos.get(j);
                if (record.getId() == roleMenuVo.getModelId()) {
                    voList.add(roleMenuVo);
                }
            }
            record.setRoleMenuVoList(voList);
            menus.add(record);
        }
        System.out.println(JSON.toJSONString(menus));
    }


}
