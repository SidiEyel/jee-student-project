package com.rimbestprice.rimbestprice;

import com.rimbestprice.rimbestprice.dao.UserDao;
import com.rimbestprice.rimbestprice.models.User;


public class Main {
    public static void main(String[] args) {
        //UserDao userDao = new UserDao();
        //User newUser = new User("username12", "email12@example.com", "password123");
        //userDao.addUser(newUser);
        //System.out.println("User added: " + newUser);
        UserDao userDao = new UserDao();
        //User newUser = new User();
        //newUser.setUsername("admin");
        //newUser.setEmail("admin@example.com");
        //newUser.setPassword("password");
        //newUser.setAdmin(true);
        User newUser = new User("test", "test@example.com", "test1234");
        userDao.addUser(newUser);
        System.out.println("User added: " + newUser);
    }
}
