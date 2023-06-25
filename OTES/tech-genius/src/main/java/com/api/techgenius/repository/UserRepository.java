package com.api.techgenius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.techgenius.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
