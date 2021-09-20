package com.example.a1mobile.ui.login;


import java.util.ArrayList;
import java.util.List;

public class User {
    private List<UserObserver> observers = new ArrayList<UserObserver>();

    private static User userInstance = null;
    private String userid;
    private String password;
    private String token;

    private User(){}

    private User(String userid, String password, String token) {
        this.userid = userid;
        this.password = password;
        this.token = token;
    }

    public static User getInstance() {
        if(userInstance == null){
            userInstance = new User();
        }
            return userInstance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyAllObservers();
    }

    public void observe(UserObserver userObserver){
        observers.add(userObserver);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        notifyAllObservers();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
        notifyAllObservers();
    }

    private void notifyAllObservers(){
        for (UserObserver observer : observers) {
            observer.update();
        }
    }

    public void clearAll(){
        User user = User.getInstance();
        user.setToken(null);
        user.setPassword(null);
        user.setUserid(null);
    }
}
