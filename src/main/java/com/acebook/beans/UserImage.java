package com.acebook.beans;

public class UserImage {
	private String image;
	private String imageType;
	public UserImage(String image, String imageType) {
		super();
		this.image = image;
		this.imageType = imageType;
	}
	public UserImage() {
		super();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	
}
