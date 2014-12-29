package com.android.pet.view;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import com.android.pet.adapter.ProductAdapter;
import com.android.pet.listeners.SaveListener;
import com.android.pet.model.Product;
import com.android.pet.util.Appcontext;
import com.android.pet.util.Constants;
import com.doepiccoding.navigationdrawer.R;

public class ProductFragment extends Fragment{
	List<Product> products = new LinkedList<Product>();
	GridView productGrid;
	ProductAdapter adapter;
	SaveListener mCallBacklistener;
	private int mainCatPos,subCatPos,itemPos;
	TextView tbdTv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = null;
		try {
			rootView = inflater.inflate(R.layout.products, null);
			Bundle bundle = getArguments();
			if(bundle != null){
				mainCatPos =  bundle.getInt(Constants.MAIN_CATOGIRY_POSITION, 0);
				subCatPos = bundle.getInt(Constants.SUB_CATOGIRY_POSITION,0);
				itemPos = bundle.getInt(Constants.ITEM_POSITION,0);
			}
			tbdTv = (TextView) rootView.findViewById(R.id.tbd);
			productGrid = (GridView) rootView.findViewById(R.id.productList);
			loaddata(mainCatPos, subCatPos, itemPos);
			if(products.size()<=0){
				tbdTv.setVisibility(View.VISIBLE);
				productGrid.setVisibility(View.GONE);
			}else{
				tbdTv.setVisibility(View.GONE);
				productGrid.setVisibility(View.VISIBLE);
				adapter = new ProductAdapter(products, getActivity(), saveProductOnclickListener);
				productGrid.setAdapter(adapter);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return rootView;
	}
	private android.view.View.OnClickListener saveProductOnclickListener = new android.view.View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				boolean isChecked = ((CheckBox) v).isChecked(); 
				int i = Appcontext.getInstance().getSavePrductSize();
				int position = (Integer) v.getTag();
				products.get(position).setSaved(isChecked);
				if(isChecked){
					Appcontext.getInstance().getSaveProducts().put(products.get(position).getProductName(), products.get(position));
					i=i+1;
				}else{
					if(Appcontext.getInstance().getSaveProducts().containsKey(products.get(position).getProductName())){
						Appcontext.getInstance().getSaveProducts().remove(products.get(position).getProductName());
						i=i-1;
					}
				}
				Appcontext.getInstance().setSavePrductSize(i);
				mCallBacklistener.getCount();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private void loaddata(int mainPos,int subCat,int itemPos){
		if(mainPos == 0 && subCat == 1){
			if(itemPos == 0){
				populateFruits();
			}else if(itemPos == 1){
				populateVeg();
			}else{
				populateFruits();
				populateVeg();
				loadPulses();
			}
		}
	}
	public void populateFruits(){
		Product product = new Product();

		product.setProductName("Apple");
		product.setBrand("Simla");
		product.setImagePath("apple_image");
		product.setOfferPrice(25);
		product.setProductARCP(15.00);
		product.setProductMRP(20.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Pomegranate");
		product.setBrand("ARC");
		product.setImagePath("pomegranate");
		product.setOfferPrice(50);
		product.setProductARCP(15.00);
		product.setProductMRP(30.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);


		product = new Product();
		product.setProductName("Orange");
		product.setBrand("Orange");
		product.setImagePath("orange");
		product.setOfferPrice(50);
		product.setProductARCP(15.00);
		product.setProductMRP(30.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Guava Fruit");
		product.setBrand("guava-fruit");
		product.setImagePath("guava_fruit");
		product.setOfferPrice(25);
		product.setProductARCP(15.00);
		product.setProductMRP(20.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Grapes");
		product.setBrand("Grapes");
		product.setImagePath("redgrapes");
		product.setOfferPrice(20);
		product.setProductARCP(12.00);
		product.setProductMRP(15.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);
		if(itemPos == 0){
			product = new Product();
			product.setProductName("grapes");
			product.setBrand("grapes");
			product.setImagePath("green_greaps");
			product.setOfferPrice(25);
			product.setProductARCP(15.00);
			product.setProductMRP(20.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Banana");
			product.setBrand("Red");
			product.setImagePath("banana_red");
			product.setOfferPrice(25);
			product.setProductARCP(15.00);
			product.setProductMRP(20.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Kiwi");
			product.setBrand("Fruit");
			product.setImagePath("kiwi_fruit");
			product.setOfferPrice(50);
			product.setProductARCP(15.00);
			product.setProductMRP(30.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);


			product = new Product();
			product.setProductName("Mosambi");
			product.setBrand("");
			product.setImagePath("musambi");
			product.setOfferPrice(50);
			product.setProductARCP(15.00);
			product.setProductMRP(30.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Fig");
			product.setBrand("Fruit");
			product.setImagePath("fig");
			product.setOfferPrice(25);
			product.setProductARCP(15.00);
			product.setProductMRP(20.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Custed");
			product.setBrand("Apple");
			product.setImagePath("custed_apple");
			product.setOfferPrice(20);
			product.setProductARCP(12.00);
			product.setProductMRP(15.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Jack");
			product.setBrand("Fruit");
			product.setImagePath("jack");
			product.setOfferPrice(34);
			product.setProductARCP(10.00);
			product.setProductMRP(15.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);

			product = new Product();
			product.setProductName("Greenapple1");
			product.setBrand("Greenapple");
			product.setImagePath("greenapple");
			product.setOfferPrice(34);
			product.setProductARCP(10.00);
			product.setProductMRP(15.00);
			product.setProductDesc("");
			product.setQuantity(1);
			products.add(product);
		}


	}
	public void populateVeg(){
		Product product = new Product();
		product.setProductName("Brinjal");
		product.setBrand("Brinjal");
		product.setImagePath("brinjal");
		product.setOfferPrice(50);
		product.setProductARCP(15.00);
		product.setProductMRP(30.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Cauliflower");
		product.setBrand("Cauliflower");
		product.setImagePath("cauliflower");
		product.setOfferPrice(40);
		product.setProductARCP(18.00);
		product.setProductMRP(30.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("chilly");
		product.setBrand("chilly");
		product.setImagePath("chilly");
		product.setOfferPrice(25);
		product.setProductARCP(15.00);
		product.setProductMRP(20.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Cucumber");
		product.setBrand("Cucumber");
		product.setImagePath("cucumber");
		product.setOfferPrice(34);
		product.setProductARCP(10.00);
		product.setProductMRP(15.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Mushroom");
		product.setBrand("");
		product.setImagePath("mushroom");
		product.setOfferPrice(20);
		product.setProductARCP(12.00);
		product.setProductMRP(15.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

	}
	public void loadPulses(){
		Product product = new Product();
		product.setProductName("Massor Dal");
		product.setBrand("Dal");
		product.setImagePath("masoor_dal");
		product.setOfferPrice(13);
		product.setProductARCP(21.00);
		product.setProductMRP(24.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Peanuts - Pallilu - Moongfali");
		product.setBrand("");
		product.setImagePath("peanuts");
		product.setOfferPrice(8);
		product.setProductARCP(23.00);
		product.setProductMRP(25.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Rajma Dal");
		product.setBrand("");
		product.setImagePath("rajma_dal");
		product.setOfferPrice(5);
		product.setProductARCP(36.00);
		product.setProductMRP(38.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Sun drop Sunflower Oil");
		product.setBrand("Sun drop");
		product.setImagePath("sundrop_refund_oil");
		product.setOfferPrice(3);
		product.setProductARCP(170);
		product.setProductMRP(175);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);

		product = new Product();
		product.setProductName("Saffola Gold Losorb Oil");
		product.setBrand("Saffola");
		product.setImagePath("safola_oil");
		product.setOfferPrice(2);
		product.setProductARCP(147.00);
		product.setProductMRP(150.00);
		product.setProductDesc("");
		product.setQuantity(1);
		products.add(product);
	}
	public void mCallBack(SaveListener mCallBack){
		this.mCallBacklistener = mCallBack;
	}
	public void notifyDataSet(){
		adapter.notifyDataSetChanged();
	}
}
