package com.App.BlogApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.BlogApplication.Entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
