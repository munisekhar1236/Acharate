package com.android.pet.model;

public class SubCatogiry {
	private String name;
	private String imageName;
	
	public SubCatogiry(String name, String imageName) {
		super();
		this.name = name;
		this.imageName = imageName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	

}
