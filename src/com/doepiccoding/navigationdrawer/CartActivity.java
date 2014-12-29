package com.doepiccoding.navigationdrawer;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.pet.model.Product;
import com.android.pet.util.Appcontext;
import com.android.pet.util.Utility;

public class CartActivity extends Activity implements OnClickListener{
	double subTotal,mrpPrice;
	ImageView back,logo;
	TextView noOfProducts,totalpriceTv,totalMrpTv,totalArcpTv,totalTv,saveTv;
	List<Product> prodList ;
	ListView listView;
	TextView nodata;
	LinearLayout dataLL;
	CustomAdapter custAdapter ;
	PopupWindow pwindo;
	String[] selectedDropList={"Remove"};
	ListView dropDownList;
	ArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_cart);
			back = (ImageView) findViewById(R.id.back);
			logo = (ImageView) findViewById(R.id.logo);
			logo.setOnClickListener(this);
			back.setOnClickListener(this);
			noOfProducts = (TextView) findViewById(R.id.noOfProducts);
			totalpriceTv = (TextView) findViewById(R.id.totalprice);
			totalMrpTv = (TextView) findViewById(R.id.totalmrp);
			totalArcpTv = (TextView) findViewById(R.id.totalArcp);
//			taxesTv = (TextView) findViewById(R.id.taxesTv);
			totalTv = (TextView) findViewById(R.id.totalTv);
			saveTv = (TextView) findViewById(R.id.saveTv);
			listView = (ListView) findViewById(R.id.list);
			dataLL = (LinearLayout) findViewById(R.id.dataLL);
			nodata = (TextView) findViewById(R.id.nodata);
			prodList = Appcontext.getInstance().getCartProducts();
			if(prodList.size()>0){
				nodata.setVisibility(View.GONE);
				dataLL.setVisibility(View.VISIBLE);
				custAdapter = new CustomAdapter(this, R.layout.save_list_item);
				listView.setAdapter(custAdapter);
				
				for (Product cartProd : prodList) {
					mrpPrice = mrpPrice+cartProd.getProductMRP();
					subTotal = subTotal+cartProd.getProductARCP();
				}
				populateData();
			}else{
				nodata.setVisibility(View.VISIBLE);
				dataLL.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void populateData(){
		noOfProducts.setText(prodList.size()+" Products.");
		totalpriceTv.setText("Rs "+subTotal);
		totalMrpTv.setText("Rs "+mrpPrice);
		totalArcpTv.setText("Rs "+subTotal);
//		taxesTv.setText("Rs "+taxes);
		totalTv.setText("Rs "+subTotal);
		saveTv.setText("Rs "+(mrpPrice-subTotal));
	}
	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {
			case R.id.logo:
			case R.id.back:
				CartActivity.this.finish();
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
					pwindo.setHeight((int)(65*i));
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
							mrpPrice = mrpPrice-prodList.get(pos).getProductMRP();
							subTotal = subTotal-prodList.get(pos).getProductARCP();
							prodList.remove(pos);
							custAdapter.notifyDataSetChanged();
							if(prodList.size()<=0){
								dataLL.setVisibility(View.GONE);
								nodata.setVisibility(View.VISIBLE);
							}else{
								dataLL.setVisibility(View.VISIBLE);
								nodata.setVisibility(View.GONE);
								populateData();
							}
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
