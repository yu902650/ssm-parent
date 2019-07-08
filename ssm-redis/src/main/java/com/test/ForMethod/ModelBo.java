package com.test.ForMethod;

import java.util.List;

/**
 * Created by bobo on 2019/4/12 17:37
 */
public class ModelBo {

    private int id;
    private int parentId;
    private String modelName;
    private List<RoleMenuVo> roleMenuVoList;

    public List<RoleMenuVo> getRoleMenuVoList() {
        return roleMenuVoList;
    }

    public void setRoleMenuVoList(List<RoleMenuVo> roleMenuVoList) {
        this.roleMenuVoList = roleMenuVoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
