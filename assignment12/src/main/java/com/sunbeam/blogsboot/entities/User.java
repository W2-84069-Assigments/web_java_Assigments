package com.sunbeam.blogsboot.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")

public class User 
{
            
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    String name;
    String email;
    String password;
    String phone;
    @Column(name="created_on")
    @Basic
    Timestamp createdOn;
    @OneToMany(mappedBy = "user", fetch= FetchType.EAGER)
    private  List<Blog> blogList;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", createdOn="
                + createdOn + "]";
    }



    

    
}
