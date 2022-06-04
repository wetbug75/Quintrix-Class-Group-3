package com.bigshots.spabackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //this might be unnecessary
public interface TheRepo extends JpaRepository<User, Long> {

}
