package Model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Joke {
	@Column
	private String question;
	@Column
	private String answer;
	@Column
	private int likes;
	@Column
	private int dislikes;
	@Column
	private String rating;
	
	public Joke(String question, String answer) {
		this.question = question;
		this.answer = answer;
		this.likes = 0;
		this. dislikes = 0;
	
	}
	
	public int getLikes() {
		return this.likes;
	}
	
	public int getDislikes() {
		return this.dislikes;
	}
	
	public void userLiked() {
		this.likes += 1;
	}

	public void userDisliked() {
		this.dislikes += 1;
	}
	
	public void jokeRating() {
		if(this.likes > this.dislikes) {
			this.rating = "great joke";
		} if(this.likes == this.dislikes) {
			this.rating = "okay joke";
		} else {
			this.rating = "bad joke";
		}
	}
	
	
}
