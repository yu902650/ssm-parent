package com;

/**
 * Created by bobo on 2019/4/26 9:01
 */
public class User {
    private String id;
    private String name;
    private String age;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof User&& this.id != null && this.id.equals(((User)obj).getId());
    }
}