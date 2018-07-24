package com.jnshu.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 123L;

    public Long id;
    private String name;
    private String password;
    private String  salt;
    private String despwd;
    private long landtime;
    private String userIphone;
    private String head_tx;
    private String emali;
    private String code;
    private Integer emailState;


    public User(){}

    public User(Long id, String head_tx) {
        this.id= id;
        this.head_tx= head_tx;
    }


    @Override
    public String toString() {
        return "User{" +
                ",id=" + id +
                ",name='" + name + '\'' +
                ",password='" + password + '\'' +
                ",salt='" + salt + '\'' +
                ",despwd='" + despwd + '\'' +
                ",landtime='" + landtime +
                ",iphone='"+ userIphone + '\'' +
                ",head_tx='"+ head_tx + '\'' +
                ",emali='"+ emali + '\'' +
                ",code='" + code + '\'' +
                "}";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getDespwd() {
        return despwd;
    }

    public void setDespwd(String despwd) {
        this.despwd = despwd;
    }

    public long getLandtime() {
        return landtime;
    }

    public void setLandtime(long landtime) {
        this.landtime = landtime;
    }

    public String getUserIphone() {
        return userIphone;
    }

    public void setUserIphone(String userIphone) {
        this.userIphone = userIphone;
    }
    public void setEmali(String emali) {
        this.emali = emali;
    }

    public String getEmali() {
        return emali;
    }

    public void setHead_tx(String head_tx) {
        this.head_tx = head_tx;
    }

    public String getHead_tx() {
        return head_tx;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Integer getEmailState() {
        return emailState;
    }

    public void setEmailState(Integer emailState) {
        this.emailState = emailState;
    }


}
