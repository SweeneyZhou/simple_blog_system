package com.example.demo.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ChangePasswordVO {

    @NotBlank
    @Length(min = 2, max = 16, message = "长度限制为：2~16")
    private String newPassword;
    @NotBlank
    @Length(min = 2, max = 16, message = "长度限制为：2~16")
    private String oldPassword;
    private Long userId;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
