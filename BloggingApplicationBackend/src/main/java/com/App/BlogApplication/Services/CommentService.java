package com.App.BlogApplication.Services;

import com.App.BlogApplication.DTO.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

	void deleteComment(Integer commentId);
}
