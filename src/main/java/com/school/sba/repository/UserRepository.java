package com.school.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sba.Enum.UserRole;
import com.school.sba.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	public boolean existsByUserRole(UserRole userRole);
}