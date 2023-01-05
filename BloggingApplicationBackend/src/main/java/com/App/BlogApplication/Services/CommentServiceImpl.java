package com.App.BlogApplication.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.BlogApplication.DTO.CommentDto;
import com.App.BlogApplication.Entities.Comment;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.CommentRepo;
import com.App.BlogApplication.Repositories.PostRepo;
import com.App.BlogApplication.Repositories.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepo.save(comment);
		return this.mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepo.delete(comment);
	}

}
