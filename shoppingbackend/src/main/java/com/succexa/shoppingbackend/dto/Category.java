package com.succexa.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//creating an entity 
@Entity
public class Category {

	/*
	 * private fields
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name="image_url")
	private String imageURL;
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL
				+ ", avtive=" + avtive + "]";
	}
	
	@Column(name="is_active")
	private boolean avtive=true;
	
	/*
	 * relative fields
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public boolean isAvtive() {
		return avtive;
	}
	public void setAvtive(boolean avtive) {
		this.avtive = avtive;
	}

}
