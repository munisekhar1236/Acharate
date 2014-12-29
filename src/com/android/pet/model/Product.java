package com.android.pet.model;

public class Product {
	private String productName;
	private String productDesc;
	private double productMRP;
	private double productARCP;
	private String brand;
	private double quantity;
	private String imagePath;
	private int offerPrice;
	private boolean isSaved;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductMRP() {
		return productMRP;
	}
	public void setProductMRP(double productMRP) {
		this.productMRP = productMRP;
	}
	public double getProductARCP() {
		return productARCP;
	}
	public void setProductARCP(double productARCP) {
		this.productARCP = productARCP;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}
	public boolean isSaved() {
		return isSaved;
	}
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	
}
