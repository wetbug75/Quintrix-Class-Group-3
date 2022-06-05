package Service;

import Model.User;
import Repo.UserRepo;

public class UserService {
	private UserRepo userRepo;
	
	public void addUser(String userName, String email, String password) {
		User newUser = new User(userName, email, password);
		userRepo.save(newUser);
	}

}
