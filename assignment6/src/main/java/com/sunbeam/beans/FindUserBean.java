package com.sunbeam.beans;

import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.User;

public class FindUserBean {
private User user;
private int id;


public FindUserBean() {
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

public void fetchName()
{
    try(UserDao userDao=new UserDao())
    {
        user= userDao.findById(id);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}
}
