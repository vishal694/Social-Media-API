package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Role;

public interface IRoleRepo extends JpaRepository<Role, Integer> {

}
