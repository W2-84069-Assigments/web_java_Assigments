package com.sunbeam.beans;

import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.User;

public class RegistrationBean
{
User u;
String name;
String email;
String password;
String phone;
public RegistrationBean() {
}

public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}


public int addUser()
{
    try(UserDao userDao=new UserDao())
    {
        u= new User(0, name, email, password, phone, null);
        return userDao.save(u);
    }
    catch(Exception e)
    {
        e.printStackTrace();
        return 0;
    }
}

}
