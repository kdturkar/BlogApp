package com.App.BlogApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.BlogApplication.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
