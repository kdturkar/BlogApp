package com.App.BlogApplication.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.BlogApplication.Entities.Category;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
