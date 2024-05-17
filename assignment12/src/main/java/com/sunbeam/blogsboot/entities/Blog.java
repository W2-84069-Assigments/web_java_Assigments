package com.sunbeam.blogsboot.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="blogs")
public class Blog 
    {
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Id
        private int id;
        private String title;
        private String contents;
        @Column(name="created_on")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdOn;
        @ManyToOne
        @JoinColumn(name="user_id")
        private User user;
        @ManyToOne //(fetch = FetchType.LAZY)
        @JoinColumn(name="category_id")
        private Category category;
        @Override
        public String toString() {
            return "Blog [id=" + id + ", title=" + title + ", contents=" + contents + ", createdOn=" + createdOn + "]";
        }
        
    }
