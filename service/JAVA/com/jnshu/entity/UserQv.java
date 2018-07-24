package com.jnshu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

//一定要实现序列话 serializable
public class UserQv implements Serializable {
    private static final long serialVersionUID = 123L;

    private Long id;
    private String name;
    private String QQ;
    private String major;
    private Long startTime;
    private String school;
    private Long student_Id;
    private String daily_cone;
    private String desire;
    private String bre;
    private String know_from;
    private String create_by;
    private String update_by;
    private Long create_at;
    private Long update_at;

    @Override
    public String toString(){
        return "UserQv{" +
                ",id='" + id +
                ",name='" + name + '\'' +
                ",QQ='"+ QQ +
                ",major='" + major + '\'' +
                ",startTime='" + startTime +
                ",school='" + school + '\'' +
                ",student_Id='" + student_Id + '\'' +
                ",daily_cone='" + daily_cone + '\'' +
                ",desire='" + desire + '\'' +
                ",bre='" + bre + '\'' +
                ",know_from='" + know_from + '\'' +
                ",create_by='" + create_by + '\'' +
                ",update_by='" + update_by + '\'' +
                ",create_at='" + create_at +
                ",update_at='" + update_at +
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

    public String  getQQ() {
        return QQ;
    }
    public void setQQ(String  QQ){
        this.QQ = QQ;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Long  getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(Long  student_Id) {
        this.student_Id = student_Id;
    }

    public String getDaily_cone() {
        return daily_cone;
    }

    public void setDaily_cone(String daily_cone) {
        this.daily_cone = daily_cone;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }

    public String getBre() {
        return bre;
    }

    public void setBre(String bre) {
        this.bre = bre;
    }

    public String getKnow_from() {
        return know_from;
    }

    public void setKnow_from(String know_from) {
        this.know_from = know_from;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getUpdatte_by() {
        return update_by;
    }

    public void setUpdatte_by(String updatte_by) {
        this.update_by = updatte_by;
    }

    public Long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Long create_at) {
        this.create_at = create_at;
    }

    public Long getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Long update_at) {
        this.update_at = update_at;
    }
}

