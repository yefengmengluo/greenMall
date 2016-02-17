package com.wk.p3.greenmall.common.cache;

import java.io.Serializable;

/**
 * Created by cc on 15-12-3.
 */

public class Account implements Serializable{

    private int id;
    private String name;
    private String password;

    public Account(String name) {
        this.name = name;
    }

    public Account(String name,String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
