package com.bigshots.spabackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Joke {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String question;
	@Column
	private String answer;
	@Column
	private Integer upvotes;
	@Column
	private Integer downvotes;
	@Column
	private String created_at;//time stamp   "yyyy.MM.dd hh:mm:ss"
	// https://www.youtube.com/watch?v=CyWQVYLiupc
	@ManyToOne
	private Users author;
	
	public Joke() {
	}
	public Joke(String question, String answer) {
		this(question, answer, null);
	}
	public Joke(String question, String answer, Users author) {
		this.question = question;
		this.answer = answer;
		this.upvotes = 0;
		this.downvotes = 0;
		this.author = author;
		this.created_at = null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}
	public Integer getDownvotes() {
		return downvotes;
	}
	public void setDislikes(Integer downvotes) {
		this.downvotes = downvotes;
	}
	public String getCreated_at() {
		return this.created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Users getAuthor() {
		return author;
	}
	public void setAuthor(Users author) {
		this.author = author;
	}
	public void setDownvotes(Integer downvotes) {
		this.downvotes = downvotes;
	}
}
