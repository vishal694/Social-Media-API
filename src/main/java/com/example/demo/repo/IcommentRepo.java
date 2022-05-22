package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Comment;

public interface IcommentRepo extends JpaRepository<Comment, Integer> {

}
