package com.crud.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.example.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByName(String name);
}
