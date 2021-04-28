package ru.polis.toasters.util;

public class UserData {

    public UserData(String user, String userName, String password, String url) {
        this.user = user;
        this.userName = userName;
        this.password = password;
        this.url = url;
    }

    public UserData(String user, String userName, String password) {
        this.user = user;
        this.userName = userName;
        this.password = password;
    }

    public String user;
    public String userName;
    public String password;
    public String url;
}
