package com.doepiccoding.navigationdrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.pet.model.Product;
import com.android.pet.util.Appcontext;
import com.android.pet.util.Utility;

public class SaveActivity extends Activity implements OnClickListener{
	ListView listView;
	List<Product> prodList = new ArrayList<Product>();
	ImageView back,logo;
	PopupWindow pwindo;
	String[] selectedDropList={"Remove","Move To Cart"};
	ListView dropDownList;
	ArrayAdapter adapter;
	CustomAdapter custAdapter ;
	TextView nodata;
	TextView cartCount;
	FrameLayout cartItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.save_activity);
			listView = (ListView) findViewById(R.id.list);
			back = (ImageView) findViewById(R.id.back);
			logo = (ImageView) findViewById(R.id.logo);
			nodata = (TextView) findViewById(R.id.nodata);
			cartCount = (TextView) findViewById(R.id.cartCount);
			cartItem = (FrameLayout) findViewById(R.id.cartItems);

			back.setOnClickListener(this);
			logo.setOnClickListener(this);
			cartItem.setOnClickListener(this);
			Map<String, Product> map = Appcontext.getInstance().getSaveProducts();
			if(map.size()<=0){
				listView.setVisibility(View.GONE);
				nodata.setVisibility(View.VISIBLE);
			}else{
				listView.setVisibility(View.VISIBLE);
				nodata.setVisibility(View.GONE);
			}
			for(Map.Entry<String, Product> obj : map.entrySet()){
				Product product = obj.getValue();
				prodList.add(product);
			}
			if(Appcontext.getInstance().getCartProducts().size() <=0){
				cartCount.setVisibility(View.GONE);
			}else{
				cartCount.setVisibility(View.VISIBLE);
				cartCount.setText(Appcontext.getInstance().getCartProducts().size()+"");
			}
			custAdapter = new CustomAdapter(this, R.layout.save_list_item);
			listView.setAdapter(custAdapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {
			case R.id.cartItems:
				Intent cartIntent = new Intent(this, CartActivity.class);
				startActivity(cartIntent);
				break;
			case R.id.logo:
			case R.id.back:
				SaveActivity.this.finish();
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(Appcontext.getInstance().getCartProducts().size() <=0){
			cartCount.setVisibility(View.GONE);
		}else{
			cartCount.setVisibility(View.VISIBLE);
			cartCount.setText(Appcontext.getInstance().getCartProducts().size()+"");
		}
	}
	public class CustomAdapter extends ArrayAdapter{

		private LayoutInflater vi;

		public CustomAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
			vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return prodList.size();
		}

		@Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			try {
				convertView = vi.inflate(R.layout.save_list_item,	null);
				ImageView productImage = (ImageView) convertView.findViewById(R.id.productImage);
				TextView prodName = (TextView) convertView.findViewById(R.id.prodName);
				TextView prodDesc = (TextView) convertView.findViewById(R.id.prodDesc);
				TextView brandName = (TextView) convertView.findViewById(R.id.brandName);
				ImageButton itemOptions = (ImageButton) convertView.findViewById(R.id.itemOptions);
				TextView offer = (TextView) convertView.findViewById(R.id.offer);
				TextView mrpPrice = (TextView) convertView.findViewById(R.id.mrpPrice);
				TextView arcpPrice = (TextView) convertView.findViewById(R.id.arcpPrice);
				itemOptions.setTag(position);
				productImage.setBackgroundResource(android.R.color.transparent);
				int id = getResources().getIdentifier(prodList.get(position).getImagePath(), "drawable",getPackageName());
				productImage.setImageResource(id);
				prodName.setText(prodList.get(position).getProductName()+"  "+prodList.get(position).getBrand());
				mrpPrice.setText("Rs "+prodList.get(position).getProductMRP()+"");
				mrpPrice.setPaintFlags(mrpPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
				arcpPrice.setText("Rs "+prodList.get(position).getProductARCP()+"");
				offer.setText(prodList.get(position).getOfferPrice()+"%");
				prodDesc.setText(prodList.get(position).getProductDesc());
				brandName.setText(prodList.get(position).getBrand());

				itemOptions.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						int pos = (Integer) v.getTag();
						inflateSpinnerLayout(v, pos);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

	}
	public void inflateSpinnerLayout(View v,final int pos){
		if (pwindo == null || !pwindo.isShowing()) {
			try { 
				LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				View layout = inflater.inflate(R.layout.custom_dropdown,null);
				dropDownList = (ListView) layout.findViewById(R.id.dropDownList);
				//adapter = new CustomListAdapter(getActivity(),R.layout.patient_profile_row,selectedDropList);
				adapter = new ArrayAdapter(this, R.layout.patient_profile_row, R.id.tv, selectedDropList);
				dropDownList.setAdapter(adapter);
				dropDownList.setSelector(R.drawable.list_item_selector);

				DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				int height = displaymetrics.heightPixels;
				int width = displaymetrics.widthPixels;

				DisplayMetrics metrics = getResources().getDisplayMetrics();
				double i = metrics.densityDpi/320.0;
				int X = (int) (178*i);
				int Y = (int) (5*i);
				//pwindo = new PopupWindow(layout,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, true);
				pwindo = new PopupWindow(this);
				pwindo.setContentView(layout);
				pwindo.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				if(selectedDropList.length>2){
					pwindo.setHeight((int)(570*i));
				}else{
					pwindo.setHeight((int)(130*i));
				}
				pwindo.setWidth(v.getWidth()+(int)(250*i));
				pwindo.setFocusable(true);


				Rect location = Utility.locateView(v);
				pwindo.showAtLocation(v, Gravity.TOP|Gravity.CENTER, location.left-X , location.bottom-Y);
				/*int pos = -1;
				for (int j = 0; j < list.length; j++) {
					if(list[j].equalsIgnoreCase(txt)){
						pos = j;
					}
				}*/
				//				dropDownList.setItemChecked(pos, true);
				dropDownList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						try {
							pwindo.dismiss();

							if(position == 1){
								Appcontext.getInstance().getCartProducts().add(prodList.get(pos));
								cartCount.setVisibility(View.VISIBLE);
								cartCount.setText(Appcontext.getInstance().getCartProducts().size()+"");
							}
							custAdapter.notifyDataSetChanged();
							Appcontext.getInstance().getSaveProducts().remove(prodList.get(pos).getProductName());
							prodList.remove(pos);
							if(prodList.size()<=0){
								listView.setVisibility(View.GONE);
								nodata.setVisibility(View.VISIBLE);
							}else{
								listView.setVisibility(View.VISIBLE);
								nodata.setVisibility(View.GONE);
							}
							Appcontext.getInstance().setSavePrductSize(Appcontext.getInstance().getSavePrductSize()-1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			} catch (Exception e) { 
				e.printStackTrace(); 
			}
		}else {
			if (pwindo!=null) {
				pwindo.dismiss();
			}
		}
	}
	

}
