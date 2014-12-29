package com.android.pet.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.pet.model.Catogiry;
import com.android.pet.model.Product;
import com.android.pet.model.SubCatogiry;

public class Appcontext {
	private static Appcontext instance = null;
	private List<Catogiry> catogiryList= null;
	private Map<String, Product> saveProducts = null;
	private int savePrductSize;
	private List<Product> cartProducts = null;
	private Appcontext() {
		catogiryList= new ArrayList<Catogiry>();
		saveProducts = new HashMap<String, Product>();
		cartProducts = new ArrayList<Product>();
		prepareListData();
	}
	public static Appcontext getInstance(){
		if(instance == null)
			instance = new Appcontext();
		return instance;
	}
	
	public List<Product> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<Product> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public Map<String, Product> getSaveProducts() {
		return saveProducts;
	}
	public void setSaveProducts(Map<String, Product> saveProducts) {
		this.saveProducts = saveProducts;
	}
	public List<Catogiry> getCatogiryList() {
		return catogiryList;
	}
	public void setCatogiryList(List<Catogiry> catogiryList) {
		this.catogiryList = catogiryList;
	}
	
	public int getSavePrductSize() {
		return savePrductSize;
	}
	public void setSavePrductSize(int savePrductSize) {
		this.savePrductSize = savePrductSize;
	}
	private void prepareListData() {
		//Kitchen
		Catogiry cat = new Catogiry();
		cat.setTitle("Kitchen");
		cat.setImage("kichen");
		cat.setImage1("fruits");
		cat.setImage2("vegtables");
		cat.setImage3("oils");
		cat.setImage4("refregiration");
		
		List<String> list = new ArrayList<String>();
		list.add("Grocery(359)");
		list.add("Fruits & Vegetables(96)");
		list.add("Refregiration(149)");

		List<String> subList = new ArrayList<String>();
		subList.add("Pulses & Beans(18)");
		subList.add("Flours & Revva(18)");
		subList.add("Oils & Gee(25)");
		subList.add("Rice & Grains(20)");
		subList.add("Spices & Masalas(59)");

		Map<String, List<String>> products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);

		subList = new ArrayList<String>();
		subList.add("Fruits(33)");
		subList.add("Vegetables(2)");
		subList.add("Root vegetables(2)");
		subList.add("Leafy vegetables(20)");
		subList.add("Flowers(59)");

		products.put(list.get(1), subList);

		subList = new ArrayList<String>();
		subList.add("Beverages(18)");
		subList.add("Dairy products(18)");
		subList.add("Ice creams(25)");
		subList.add("Mineral Water(20)");


		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);

		catogiryList.add(cat);

		//Personal care
		cat = new Catogiry();
		cat.setTitle("Personal Care");
		cat.setImage("personal_care");
		cat.setImage1("bodycare");
		cat.setImage2("haircare");
		cat.setImage3("toothpaste");
		cat.setImage4("facecreem");
		
		list = new ArrayList<String>();
		list.add("Hair care(359)");
		list.add("Body Care(96)");
		list.add("Dental Care(149)");

		subList = new ArrayList<String>();
		subList.add("Shampoo(18)");
		subList.add("Hair conditioner(18)");
		subList.add("Hair Oils(25)");
		subList.add("Hair Brush(20)");
		subList.add("Hair Color(59)");

		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);

		subList = new ArrayList<String>();
		subList.add("Soap(18)");
		subList.add("Hand wash(18)");
		subList.add("Shower Gel(25)");
		subList.add("Perfumes & Deos(20)");
		subList.add("Talcum Powder(59)");

		products.put(list.get(1), subList);

		subList = new ArrayList<String>();
		subList.add("Tooth paste(18)");
		subList.add("Tooth brush(18)");
		subList.add("Mouth wash(25)");
		subList.add("Tooth powder (20)");


		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);

		//Infants & Babies
		cat = new Catogiry();
		cat.setTitle("Infants & Babies");
		cat.setImage("baby");
		cat.setImage1("babysoaps");
		cat.setImage2("grooming");
		cat.setImage3("wipes");
		cat.setImage4("bottles");
		list = new ArrayList<String>();
		list.add("Bath,skin & Health care(359)");
		list.add("Diapering(96)");
		list.add("Bottles & Accessories(149)");

		subList = new ArrayList<String>();
		subList.add("Baby soap(18)");
		subList.add("Baby Powder(18)");
		subList.add("Baby Oils(25)");
		subList.add("Baby lotion(20)");
		subList.add("Baby cream & Ointments(59)");

		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);

		subList = new ArrayList<String>();
		subList.add("Baby Diapers (18)");
		subList.add("Hand wipes(18)");
		subList.add("Diper Changing pads(25)");
		subList.add("Diaper Rash cream(20)");
		subList.add("Diaper Bags(59)");

		products.put(list.get(1), subList);

		subList = new ArrayList<String>();
		subList.add("Baby essentials(18)");
		subList.add("Bathing accessories(18)");
		subList.add("Grooming(25)");
		subList.add("Bottle(20)");


		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);

		//Confectionary
		cat = new Catogiry();
		cat.setTitle("Confectionary");
		cat.setImage("confectionary");
		cat.setImage1("cakes");
		cat.setImage2("chocolate");
		cat.setImage3("icecream");
		cat.setImage4("biscuits");
		
		list = new ArrayList<String>();
		list.add("Sweet and Chocolate(359)");
		list.add("Biscuits and Cookies(96)");
		list.add("Craem and Sweet Mix(149)");

		subList = new ArrayList<String>();
		subList.add("Chocolate(18)");
		subList.add("Cake(18)");
		subList.add("Candy(25)");
		subList.add("Pudding Mix(20)");
		subList.add("Indian Sweets(59)");

		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);

		subList = new ArrayList<String>();
		subList.add("Biscuits(18)");
		subList.add("Cookies(18)");
		subList.add("Cream Biscuits(25)");
		subList.add("wafers(20)");
		subList.add("Crackers(59)");

		products.put(list.get(1), subList);

		subList = new ArrayList<String>();
		subList.add("Ice Cream Mix (18)");
		subList.add("Sweet Mix(18)");

		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);

		//Cleaning
		cat = new Catogiry();
		cat.setTitle("Cleaning");
		cat.setImage("Cleaning1");
		cat.setImage1("air_fresher");
		cat.setImage2("detergent");
		cat.setImage3("good_knight");
		cat.setImage4("comfort");
		
		list = new ArrayList<String>();
		list.add("Home Care(359)");
		list.add("Insect Repellent(96)");
		list.add("Lanudry(149)");
		
		subList = new ArrayList<String>();
		subList.add("Air Freshener(18)");
		subList.add("Toilet Cleaner(18)");
		subList.add("Tissue Paper(25)");
		subList.add("Surface Cleaner(20)");
		subList.add("Scrub Pad(59)");
		
		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);
		
		subList = new ArrayList<String>();
		subList.add("Good Knight(18)");
		subList.add("All Out (18)");
		subList.add("Hit(25)");
		subList.add("Mortein(20)");
		subList.add("Laxman Rekha (59)");
		
		products.put(list.get(1), subList);
		
		subList = new ArrayList<String>();
		subList.add("Detergent Powder(18)");
		subList.add("Detergent Bar(18)");

		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);
		
		//House Hold
		cat = new Catogiry();
		cat.setTitle("House Hold");
		cat.setImage("house_hold");
		cat.setImage1("food_container");
		cat.setImage2("kitchen_utensils");
		cat.setImage3("toilet");
		cat.setImage4("dust_bin");
		
		list = new ArrayList<String>();
		list.add("Plastic (359)");
		list.add("Stainless Steel(96)");
		list.add("Brass(149)");
		
		subList = new ArrayList<String>();
		
		subList.add("Toilet Brush(18)");
		subList.add("Hanger(25)");
		subList.add("Dust Pan (20)");
		subList.add("Dust Bin(59)");
		subList.add("Cloth Clips(59)");
		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);
		
		subList = new ArrayList<String>();
		subList.add("Food Container (18)");
		subList.add("Spoons and Froks  (18)");
		subList.add("Plates glass and Mugs(25)");
		subList.add("Kitchen Knives(20)");
		subList.add("Kitchen Tools(59)");
		
		products.put(list.get(1), subList);
		
		subList = new ArrayList<String>();
		subList.add("Kitchen Utensils(18)");
		subList.add("DEcorative Items(18)");

		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);
		//Stationery
		cat = new Catogiry();
		cat.setTitle("Stationery");
		cat.setImage("stationary");
		cat.setImage1("cutters");
		cat.setImage2("paper");
		cat.setImage3("pens");
		cat.setImage4("crayons");
		
		list = new ArrayList<String>();
		list.add("Writing Supplies(359)");
		list.add("Office Stationary(96)");
		list.add("Other Stationary(149)");
		
		subList = new ArrayList<String>();
		
		subList.add("Crayons and Water Colour (18)");
		subList.add("Scale & Measuring tape(25)");
		subList.add("Pens (20)");
		subList.add("Pencils (59)");
		subList.add("Erasers(59)");
		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);
		
		subList = new ArrayList<String>();
		subList.add("Desk Accessories(18)");
		subList.add("Office Files & Folders (18)");
		subList.add("Paper Products (25)");
		
		products.put(list.get(1), subList);
		
		subList = new ArrayList<String>();
		subList.add("Cutters ");
		subList.add("Chalks(18)");

		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);
		
		//Others 
		cat = new Catogiry();
		cat.setTitle("Others");
		cat.setImage("others");
		cat.setImage1("health_drinks");
		cat.setImage2("health_snakes");
		cat.setImage3("pet_food");
		cat.setImage4("wave_food");
		
		list = new ArrayList<String>();
		list.add("Health and Wellness(359)");
		list.add("Pantry Supplies (96)");
		list.add("Pet(149)");
		
		subList = new ArrayList<String>();
		
		subList.add("Health Drink");
		subList.add("Pain relefe (25)");
		subList.add("Pens (20)");
		subList.add("stomach Care(59)");
	
		products = new HashMap<String, List<String>>();
		products.put(list.get(0), subList);
		
		subList = new ArrayList<String>();
		subList.add("Healthy snaks (18)");
		subList.add("wicrowave Snaks(18)");
		
		
		products.put(list.get(1), subList);
		
		subList = new ArrayList<String>();
		subList.add("Peet Food ");
		
		products.put(list.get(2), subList);
		cat.setSucCatogiry(list);
		cat.setProducts(products);
		catogiryList.add(cat);
		
		cat = new Catogiry();
		cat.setTitle("Offers");
		cat.setImage("offers");
		cat.setImage1("offers");
		cat.setImage2("offers");
		cat.setImage3("offers");
		cat.setImage4("offers");
		cat.setSucCatogiry(new ArrayList<String>());
		cat.setProducts(new HashMap<String, List<String>>());
		catogiryList.add(cat);}
}
