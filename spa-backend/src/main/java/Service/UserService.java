package Service;

import org.springframework.stereotype.Service;

import Model.User;
import Repo.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;
	
	public void addUser(String userName, String email, String password) {
		User newUser = new User(userName, email, password);
		userRepo.save(newUser);
	}

}
