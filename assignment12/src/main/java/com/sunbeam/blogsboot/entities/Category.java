package com.sunbeam.blogsboot.entities;

import java.util.List;

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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="categories")
public class Category 
    {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private int id;
        private String title;
        private String description;
        @OneToMany(mappedBy="category" , fetch=FetchType.EAGER)
        private List<Blog> blogList;
        
        @Override
        public String toString() {
            return "Category [id=" + id + ", title=" + title + ", description=" + description + "]";
        }

        
    }
