package com.App.BlogApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.BlogApplication.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
