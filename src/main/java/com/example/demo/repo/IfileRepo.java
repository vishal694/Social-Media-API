package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.File;

@Repository
public interface IfileRepo extends JpaRepository<File, String> {

}
