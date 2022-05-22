package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Category;

public interface IcategoryRepo extends JpaRepository<Category, Integer> {

}
