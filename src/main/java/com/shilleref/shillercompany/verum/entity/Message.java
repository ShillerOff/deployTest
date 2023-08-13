package com.shilleref.shillercompany.verum.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Please fill the message")
	@Length(max = 2048, message = "Message to long (more than 2kB)")
	private String text;
	@Length(max = 30, message = "Message to long (more than 30)")
	private String tag;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")//Это нужно для того чтобы в БД это поле называлось "user_id", 
								//а не author_id, как это было бы по умолчанию
	private User author;
	
	private String filename;
	
	public String getAuthorName() {
		return author != null ? author.getUsername() : "<none>";
	}

	public Message() {
		
	}
	
	public Message(String text, String tag) {
		this.text = text;
		this.tag = tag;
	}
	
	public Message(String text, String tag, User author) {
		this.text = text;
		this.tag = tag;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
