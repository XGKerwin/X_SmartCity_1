package com.example.x_smartcity_1.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/19  20:39
 */
public class GetUserInfo {

    /**
     *             "id": "371402199902041133",
     *             "name": "赵a涵",
     *             "avatar": "http://192.168.101.7:8080/mobileA/images/user4.png",
     *             "phone": "33333333dddd588881",
     *             "sex": "female"
     */

    private String id,name,avatar,phone,sex;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
