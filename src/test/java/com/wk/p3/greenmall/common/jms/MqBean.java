package com.wk.p3.greenmall.common.jms;

import java.io.Serializable;

/**
 * Created by cc on 15-12-13.
 */
public class MqBean implements Serializable {
    private int age;
    private String name = "MqBean";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MqBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
