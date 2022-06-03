package Model;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String userName;
	@Column
	private String email;
	@Column
	private String password;
	
	//use user model in the restcontroller 
	
	//create post method where only users can increment like and dislike functions 
	//sub feature where they can only like or dislike the joke once 
	//may have to add an instance to (eventual) joke model that verifies if a specific user 
	//liked the joke already? (maybe)
	
	public User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	

}
