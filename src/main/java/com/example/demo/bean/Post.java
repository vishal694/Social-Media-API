package com.example.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	private String postTitle;

	private String content;
	
	private String imagName;
	
	@OneToMany(mappedBy="image",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<File> posts = new ArrayList<>();

	private Date addDate;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>();

}
