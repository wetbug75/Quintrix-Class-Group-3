package Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Model.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
