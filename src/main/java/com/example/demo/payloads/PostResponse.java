package com.example.demo.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	private int pageNuber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastPage;
}
