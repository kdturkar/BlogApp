package com.App.BlogApplication.Services;

import java.util.List;

import com.App.BlogApplication.DTO.PostDto;
import com.App.BlogApplication.DTO.PostResponse;
import com.App.BlogApplication.Entities.Post;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);
}
