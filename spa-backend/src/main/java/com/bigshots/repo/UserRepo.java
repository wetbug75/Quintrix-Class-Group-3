package com.bigshots.repo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Bean;

import com.bigshots.model.User;

@Repository
//@ComponentScan("com.bigshots.model")
public interface UserRepo extends JpaRepository<User, Long>{

}
