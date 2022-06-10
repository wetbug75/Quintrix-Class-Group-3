package com.bigshots.spabackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigshots.spabackend.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
