package com.bigshots.spabackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigshots.spabackend.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{

}
