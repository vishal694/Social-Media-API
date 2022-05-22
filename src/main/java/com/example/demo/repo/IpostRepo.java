package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.Category;
import com.example.demo.bean.Post;
import com.example.demo.bean.User;

public interface IpostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	@Query("select p from Post p where p.postTitle like :key")
	List<Post> findByTitleContaining(@Param("key")String title,Pageable pageable);

}
