package com.raymond.dto;

import java.io.Serializable;

public class UserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String sex;
    private String website;

    public UserDto(String status, String sex, String website) {
        this.status = status;
        this.sex = sex;
        this.website = website;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "status='" + status + '\'' +
                ", sex='" + sex + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public UserDto() {
    }
}
