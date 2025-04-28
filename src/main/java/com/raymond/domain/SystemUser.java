package com.raymond.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 */
public class SystemUser extends BaseEntity implements Serializable {
    private Long userId;
    private String nickName;
    private String userName;
    private String password;
    private String tel;
    private String email;
    private String loginIp;
    private Date registerTime;
    private String avatar;
    private String bio;
    private String website;
    private String status;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Date createTime;
    private Date updateTime;

    public static final String SYSTEM_NICK_NAME = "nick_name";

    @Override
    public String toString() {
        return "SystemUser{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", registerTime=" + registerTime +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", status='" + status + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public SystemUser() {
    }

    public SystemUser(Long userId, String nickName, String userName, String password, String tel, String email, String loginIp, Date registerTime, String avatar, String bio, String website, String status, Date lastLoginTime, String lastLoginIp, Date createTime, Date updateTime) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.loginIp = loginIp;
        this.registerTime = registerTime;
        this.avatar = avatar;
        this.bio = bio;
        this.website = website;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
