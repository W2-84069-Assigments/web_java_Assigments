package com.sunbeam.blogsboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.blogsboot.daos.UserDao;
import com.sunbeam.blogsboot.entities.User;
import com.sunbeam.blogsboot.models.Credentials;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

        public User authenticate(Credentials cr)
        {
           User dbUser = userDao.findByEmail(cr.getEmail()) ;
           if(dbUser!=null && dbUser.getPassword().equals(cr.getPasswd()))
                return dbUser;
            return null;
        }

        public Map<Integer,String> findUserNameMap()
        {
            List<User> list = userDao.findAll();
            Map<Integer,String> userMap=new HashMap<>();
            for(User u : list)
                userMap.put(u.getId(),u.getName());
            return userMap;
        }
        public int saveUser(User u)
        {
            u =userDao.save(u);
            return 1;
        }

}
