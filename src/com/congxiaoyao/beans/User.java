package com.congxiaoyao.beans;

import com.google.gson.annotations.Expose;
import com.my.dbprocessor.BaseBean;

/**
 * Created by congxiaoyao on 2016/5/13.
 */
public class User extends BaseBean {

    @Expose
    private String name;
    @Expose
    private String password;
    @Expose
    private String tagid;
    @Expose
    private float balance;

    public User() {
        super();
    }

    public User(String name, String password, String tagid, float balance) {
        super();
        this.name = name;
        this.password = password;
        this.tagid = tagid;
        this.balance = balance;
    }

    public User(String name, String password, String tagid) {
        this(name, password, tagid, 0);
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

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void rechargeMoney(float money) {
        balance += money;
    }

    public void costMoney(float money) {
        balance -= money;
    }

    public boolean hasMoney() {
        return balance > 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tagid=" + tagid +
                ", balance=" + balance +
                '}';
    }
}
