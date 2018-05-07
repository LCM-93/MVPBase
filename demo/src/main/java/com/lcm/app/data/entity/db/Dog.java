package com.lcm.app.data.entity.db;

import io.realm.RealmObject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/7 17:21
 * Desc:
 * *****************************************************************
 */
public class Dog extends RealmObject{

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
