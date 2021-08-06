package com.example.digitalimage.model.entity;

import lombok.ToString;

import java.util.Date;

@ToString
public class User {
    private Long userId;

    private String userName;

    private Integer status;

    private Integer gender;

    private Date birthdate;

    private String email;

    private String phone;
    private String nick;

    private Integer graduated;

    private Integer enroYear;



    private String userProvince;

    private String userCity;

    private Integer userPoint;

    private Integer remainingViewNum;

    private Integer userExp;

    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getGraduated() {
        return graduated;
    }

    public void setGraduated(Integer graduated) {
        this.graduated = graduated;
    }

    public Integer getEnroYear() {
        return enroYear;
    }

    public void setEnroYear(Integer enroYear) {
        this.enroYear = enroYear;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince == null ? null : userProvince.trim();
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity == null ? null : userCity.trim();
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getRemainingViewNum() {
        return remainingViewNum;
    }

    public void setRemainingViewNum(Integer remainingViewNum) {
        this.remainingViewNum = remainingViewNum;
    }

    public Integer getUserExp() {
        return userExp;
    }

    public void setUserExp(Integer userExp) {
        this.userExp = userExp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}