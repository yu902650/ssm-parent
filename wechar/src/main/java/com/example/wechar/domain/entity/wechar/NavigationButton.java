
package com.example.wechar.domain.entity.wechar;

import java.util.List;

public class NavigationButton {

    private String name;
    private String type;
    private String key;
    private String url;
    private String appid;
    private String pagepath;

    private List<NavigationButton> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;

    }

    public void setUrl(String url) {
        this.url = url;

    }

    public List<NavigationButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<NavigationButton> sub_button) {
        this.sub_button = sub_button;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}

