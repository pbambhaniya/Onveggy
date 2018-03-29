package com.multipz.onveggy.Model;

/**
 * Created by Admin on 01-09-2017.
 */

public class SigninModel {
    String customer_master_id,username,password,email,mobile,profile_pic,notify_token,social_token,social_type,device_type;

    public String getCustomer_master_id() {
        return customer_master_id;
    }

    public void setCustomer_master_id(String customer_master_id) {
        this.customer_master_id = customer_master_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getNotify_token() {
        return notify_token;
    }

    public void setNotify_token(String notify_token) {
        this.notify_token = notify_token;
    }

    public String getSocial_token() {
        return social_token;
    }

    public void setSocial_token(String social_token) {
        this.social_token = social_token;
    }

    public String getSocial_type() {
        return social_type;
    }

    public void setSocial_type(String social_type) {
        this.social_type = social_type;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }
}
