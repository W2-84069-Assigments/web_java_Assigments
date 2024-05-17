package com.sunbeam.beans;

import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.User;

public class LoginBean {
private String email;
private String passwd;
private User user;
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getPasswd() {
    return passwd;
}
public void setPasswd(String passwd) {
    this.passwd = passwd;
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
public LoginBean() {
}
public void authenticate(){
    try(UserDao userDao=new UserDao())
    {
        user=userDao.findByEmailAndPassword(email, passwd);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

   

}
