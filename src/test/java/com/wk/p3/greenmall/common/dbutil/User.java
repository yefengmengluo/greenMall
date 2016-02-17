package com.wk.p3.greenmall.common.dbutil;

import java.io.Serializable;

/**
 * Created by cc on 15-12-2.
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private java.sql.Date birthday;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public java.sql.Date getBirthday() {
        return birthday;
    }
    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
