package com.android.pet.model;

import java.util.List;
import java.util.Map;

public class Catogiry{private String title;
private String image;
private List<String> sucCatogiry;
private String image1;
private String image2;
private String image3;
private String image4;
private Map<String, List<String>> products;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

public List<String> getSucCatogiry() {
	return sucCatogiry;
}
public void setSucCatogiry(List<String> sucCatogiry) {
	this.sucCatogiry = sucCatogiry;
}
public Map<String, List<String>> getProducts() {
	return products;
}
public void setProducts(Map<String, List<String>> products) {
	this.products = products;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getImage1() {
	return image1;
}
public void setImage1(String image1) {
	this.image1 = image1;
}
public String getImage2() {
	return image2;
}
public void setImage2(String image2) {
	this.image2 = image2;
}
public String getImage3() {
	return image3;
}
public void setImage3(String image3) {
	this.image3 = image3;
}
public String getImage4() {
	return image4;
}
public void setImage4(String image4) {
	this.image4 = image4;
}

}