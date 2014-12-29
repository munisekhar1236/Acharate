package com.android.pet.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.pet.listeners.CatogiryClickListener;
import com.android.pet.listeners.SaveListener;
import com.android.pet.model.Catogiry;
import com.android.pet.util.Appcontext;
import com.android.pet.util.Constants;
import com.android.pet.util.Utility;
import com.doepiccoding.navigationdrawer.CartActivity;
import com.doepiccoding.navigationdrawer.R;
import com.doepiccoding.navigationdrawer.SaveActivity;

public class NavigationActivity extends FragmentActivity implements OnClickListener,SaveListener,CatogiryClickListener{

	private DrawerLayout mDrawerLayout;
	ImageView home,logo;
	ProductFragment productFragment = null;
	CatogiryFragment catogiryFragment = null;
	LinearLayout expListView;
	HashMap<String, List<String>> listDataChild;
	ExpandableListAdapter listAdapter;
	List<String> listDataHeader;
	private LinearLayout drawerContentLayout,homeLink;
	List<Catogiry> catogiryList= new ArrayList<Catogiry>();
	static int selectedPosition =-1,childSelPos = -1,itemSelPos = -1;
	PopupWindow pwindo;
	String[] selectedDropList;
	ListView dropDownList;
	ArrayAdapter adapter;
	static String[] cityList = {"Hydrabad","Tirupathi","Nellore","Vijayawada","Visakapatnam","Chennai","Kadapa","Banglore","Delhi","Mumbai","Kolkata","Rameswaram","Kochin"};
	ImageView actionMenu;
	ChildExpandableListAdapter childAdapter;
	TextView saveCount,cartCount;
	FrameLayout saveItems,cartItem;
	CustExpListview custExpListView;
	ImageView searchView = null, cancelsearch;
	CustExpListview childExpList = null;
	EditText mEdittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_navigation);
			home = (ImageView)findViewById(R.id.home);
			logo = (ImageView) findViewById(R.id.logo);
			actionMenu = (ImageView) findViewById(R.id.actionMenu);
			saveCount = (TextView) findViewById(R.id.saveCount);
			saveItems = (FrameLayout) findViewById(R.id.saveItems);
			cartCount = (TextView) findViewById(R.id.cartCount);
			cartItem = (FrameLayout) findViewById(R.id.cartItems);
			homeLink = (LinearLayout) findViewById(R.id.homeLink);
			searchView = (ImageView) findViewById(R.id.search);
			mEdittext = (EditText) findViewById(R.id.search_edit);
			cancelsearch = (ImageView) findViewById(R.id.cancelsearch);
			homeLink.setOnClickListener(this);
			cartItem.setOnClickListener(this);
			saveItems.setOnClickListener(this);
			actionMenu.setOnClickListener(this);
			home.setOnClickListener(homeOnclickListener);
			logo.setOnClickListener(homeOnclickListener);
			if(Appcontext.getInstance().getSavePrductSize() <=0){
				saveCount.setVisibility(View.GONE);
			}else{
				saveCount.setVisibility(View.VISIBLE);
				saveCount.setText(Appcontext.getInstance().getSavePrductSize()+"");
			}
			if(Appcontext.getInstance().getCartProducts().size() <=0){
				cartCount.setVisibility(View.GONE);
			}else{
				cartCount.setVisibility(View.VISIBLE);
				cartCount.setText(Appcontext.getInstance().getSavePrductSize()+"");
			}
			setUpDrawer();
			
			cancelsearch.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub

				String str = mEdittext.getText().toString();

				if (str != null && !"".equals(str)) {


				mEdittext.setText("");

				} else {

				mEdittext.setVisibility(View.INVISIBLE);

				cancelsearch.setVisibility(View.INVISIBLE);

				searchView.setVisibility(View.VISIBLE);

				Utility.hideVirtualKeyboard(NavigationActivity.this);

				}

				}
				});

				searchView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub

				mEdittext.setVisibility(View.VISIBLE);

				mEdittext.requestFocus();

				Utility.showVirtualKeyboard(NavigationActivity.this);

				cancelsearch.setVisibility(View.VISIBLE);

				searchView.setVisibility(View.INVISIBLE);

				}
				});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {
			case R.id.actionMenu:
				inflateSpinnerLayout(actionMenu,cityList);
				break;
			case R.id.saveItems:
				Intent in = new Intent(this, SaveActivity.class);
				startActivity(in);
				break;
			case R.id.cartItems:
				Intent cartIntent = new Intent(this, CartActivity.class);
				startActivity(cartIntent);
				break;
			case R.id.homeLink:
				if(selectedPosition > -1){
					custExpListView.collapseGroup(selectedPosition);
					selectedPosition = -1;
					listAdapter.notifyDataSetChanged();
				}
				homeClicked();
				mDrawerLayout.closeDrawer(drawerContentLayout);
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
		super.onRestart();
		try {
			if(Appcontext.getInstance().getSaveProducts().size() <=0){
				saveCount.setVisibility(View.GONE);
				if(productFragment != null){
					productFragment.notifyDataSet();
				}
			}else{
				saveCount.setVisibility(View.VISIBLE);
				saveCount.setText(Appcontext.getInstance().getSaveProducts().size()+"");
			}
			if(Appcontext.getInstance().getCartProducts().size() <=0){
				cartCount.setVisibility(View.GONE);
			}else{
				cartCount.setVisibility(View.VISIBLE);
				cartCount.setText(Appcontext.getInstance().getCartProducts().size()+"");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void getCount() {
		try {
			if(Appcontext.getInstance().getSavePrductSize() <=0){
				saveCount.setVisibility(View.GONE);
			}else{
				saveCount.setVisibility(View.VISIBLE);
				saveCount.setText(Appcontext.getInstance().getSavePrductSize()+"");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClickProduct(int productId) {
		try {
			productFragment = new ProductFragment();
			productFragment.mCallBack(this);
			Bundle b = new Bundle();
			b.putInt(Constants.MAIN_CATOGIRY_POSITION, productId);
			b.putInt(Constants.SUB_CATOGIRY_POSITION, 1);
			b.putInt(Constants.ITEM_POSITION, 2);
			productFragment.setArguments(b);
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, productFragment).commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void homeClicked(){
		try {
			catogiryFragment = new CatogiryFragment();
			catogiryFragment.setCallBack(this);
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, catogiryFragment).commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 
	 * Get the names and icons references to build the drawer menu...
	 */
	private void setUpDrawer() {
		try {
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerContentLayout = (LinearLayout) findViewById(R.id.drawerContentLayout);
			mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
			mDrawerLayout.setDrawerListener(mDrawerListener);
			expListView = (LinearLayout) findViewById(R.id.lvExp);
			catogiryList = Appcontext.getInstance().getCatogiryList();
			listAdapter = new ExpandableListAdapter(this);
			childAdapter = new ChildExpandableListAdapter(this);
			custExpListView = new CustExpListview(this);
			custExpListView.setGroupIndicator(null);
			custExpListView.setDivider(null);
			// setting list adapter
			custExpListView.setAdapter(listAdapter);
			expListView.addView(custExpListView);
			homeClicked();
			mDrawerLayout.closeDrawer(drawerContentLayout);
			custExpListView.setOnGroupClickListener(new OnGroupClickListener() {

				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					try {
						if(selectedPosition == groupPosition){
							custExpListView.collapseGroup(groupPosition);
							selectedPosition = -1;
						}else if(selectedPosition != -1){
							custExpListView.collapseGroup(selectedPosition);
							custExpListView.expandGroup(groupPosition);
							selectedPosition = groupPosition;
						}else{
							custExpListView.expandGroup(groupPosition);
							selectedPosition = groupPosition;
						}
						childSelPos = -1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	View.OnClickListener homeOnclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				if(mDrawerLayout.isDrawerOpen(drawerContentLayout)){
					mDrawerLayout.closeDrawer(drawerContentLayout);
				}else{
					mDrawerLayout.openDrawer(drawerContentLayout);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	// Catch the events related to the drawer to arrange views according to this
	// action if necessary...
	private DrawerListener mDrawerListener = new DrawerListener() {

		@Override
		public void onDrawerStateChanged(int status) {

		}

		@Override
		public void onDrawerSlide(View view, float slideArg) {

		}

		@Override
		public void onDrawerOpened(View view) {
		}

		@Override
		public void onDrawerClosed(View view) {
		}
	};



	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context context;
		LayoutInflater infalInflater = null;
		// child data in format of header title, child title

		public ExpandableListAdapter(Context context) {
			this.context = context;
			infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			
			try {
				childExpList = new CustExpListview(NavigationActivity.this);
				childExpList.setAdapter(childAdapter);
				childExpList.setGroupIndicator(null);
				childExpList.setDivider(null);
				//			txtListChild.setAdapter(adapter);
				childExpList.setOnGroupClickListener(new OnGroupClickListener() {
					
					@Override
					public boolean onGroupClick(ExpandableListView parent, View v,
							int groupPosition, long id) {
						if(childSelPos == groupPosition){
							childExpList.collapseGroup(groupPosition);
							childSelPos = -1;
						}else if(childSelPos != -1){
							childExpList.collapseGroup(childSelPos);
							childExpList.expandGroup(groupPosition);
							childSelPos = groupPosition;
						}else{
							childExpList.expandGroup(groupPosition);
							childSelPos = groupPosition;
						}
						itemSelPos = -1;
						return true;
					}
				});
				childExpList.setOnChildClickListener(new OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent, View v,
							int groupPosition, int childPosition, long id) {
						//					Toast.makeText(NavigationActivity.this, "groupPosition  "+groupPosition+"   childPosition   "+childPosition, Toast.LENGTH_LONG).show();
						productFragment = new ProductFragment();
						productFragment.mCallBack(NavigationActivity.this);
						Bundle b = new Bundle();
						b.putInt(Constants.MAIN_CATOGIRY_POSITION, selectedPosition);
						b.putInt(Constants.SUB_CATOGIRY_POSITION, groupPosition);
						b.putInt(Constants.ITEM_POSITION, childPosition);
						itemSelPos = childPosition;
						productFragment.setArguments(b);
						getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, productFragment).commit();

						mDrawerLayout.closeDrawer(drawerContentLayout);
						childAdapter.notifyDataSetChanged();
						//v.setTextColor(Color.parseColor("#FFA500"));
						//lblListHeader.setTypeface(null, Typeface.BOLD);
						return false;
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return childExpList;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			int size = catogiryList.get(groupPosition).getSucCatogiry().size();
			Log.i(NavigationActivity.class.getSimpleName(),"****************************  child size  :  "+size);
			return 1;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return null;
		}

		@Override
		public int getGroupCount() {
			return catogiryList.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			try {
				String headerTitle = catogiryList.get(groupPosition).getTitle();
				if (convertView == null) {

					convertView = infalInflater.inflate(R.layout.list_group, null);
				}
				LinearLayout mainBg = (LinearLayout) convertView.findViewById(R.id.mainBg);
				TextView lblListHeader = (TextView) convertView.findViewById(R.id.itemTv);
				lblListHeader.setText(headerTitle);
				ImageView prodIcon = (ImageView) convertView.findViewById(R.id.itemImage);
				ImageView indicator = (ImageView) convertView.findViewById(R.id.indicator);
				if(isExpanded){
					indicator.setImageResource(R.drawable.up_arrow1);
				}else{
					indicator.setImageResource(R.drawable.down_arrow);
				}
				if(selectedPosition == groupPosition){
					lblListHeader.setTextColor(Color.parseColor("#731789"));
					lblListHeader.setTypeface(null, Typeface.BOLD);
				}else{
					lblListHeader.setTextColor(Color.parseColor("#000000"));
					lblListHeader.setTypeface(null, Typeface.NORMAL);
				}
				int id = getResources().getIdentifier(catogiryList.get(groupPosition).getImage(), "drawable",getPackageName());
				prodIcon.setImageResource(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
	public class ChildExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		LayoutInflater infalInflater = null;
		// child data in format of header title, child title

		public ChildExpandableListAdapter(Context context) {
			this._context = context;
			infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			try {
				final String childText = catogiryList.get(selectedPosition).getProducts().get(catogiryList.get(selectedPosition).getSucCatogiry().get(groupPosition)).get(childPosition);

				if (convertView == null) {
					LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = infalInflater.inflate(R.layout.list_item, null);
				}
				TextView tv = (TextView) convertView.findViewById(R.id.lblListItem);
				tv.setText(childText);
				if(childPosition == itemSelPos){
					tv.setTextColor(Color.parseColor("#731789"));
					tv.setTypeface(null, Typeface.BOLD);
				}else{
					tv.setTextColor(Color.parseColor("#000000"));
					tv.setTypeface(null, Typeface.NORMAL);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			int size =  catogiryList.get(selectedPosition).getProducts().get(catogiryList.get(selectedPosition).getSucCatogiry().get(groupPosition)).size();
			return size;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return null;
		}

		@Override
		public int getGroupCount() {
			return catogiryList.get(selectedPosition).getSucCatogiry().size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
			try {
				String headerTitle = catogiryList.get(selectedPosition).getSucCatogiry().get(groupPosition);
				Holder holder = null;
				Log.i("","******************    Group Position ************   "+groupPosition);
				if (convertView == null) {

					convertView = infalInflater.inflate(R.layout.list_group, null);
					holder = new Holder();
					holder.lblListHeader = (TextView) convertView.findViewById(R.id.itemTv);
					holder.lblListHeader.setPadding(30, 0, 0, 0);
					holder.prodIcon = (ImageView) convertView.findViewById(R.id.itemImage);
					holder.indicator = (ImageView) convertView.findViewById(R.id.indicator);
					holder.mainBg = (LinearLayout) convertView.findViewById(R.id.mainBg);
					convertView.setTag(holder);
				}else{
					holder = (Holder) convertView.getTag();
				}

				holder.lblListHeader.setText(headerTitle);
				holder.prodIcon.setImageResource(android.R.color.transparent);
				holder.prodIcon.setVisibility(View.GONE);
				if(isExpanded){
					holder.indicator.setImageResource(R.drawable.up_arrow1);
				}else{
					holder.indicator.setImageResource(R.drawable.down_arrow);
				}
				if(childSelPos == groupPosition){
					holder.lblListHeader.setTextColor(Color.parseColor("#731789"));
					holder.lblListHeader.setTypeface(null, Typeface.BOLD);
				}else{
					holder.lblListHeader.setTextColor(Color.parseColor("#000000"));
					holder.lblListHeader.setTypeface(null, Typeface.NORMAL);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		public class Holder{
			TextView lblListHeader ;
			ImageView prodIcon;
			ImageView indicator;
			LinearLayout mainBg;
		}
	}
	public void inflateSpinnerLayout(View v,String[] list){
		if (pwindo == null || !pwindo.isShowing()) {
			try { 
				selectedDropList = list;
				LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				View layout = inflater.inflate(R.layout.custom_dropdown,null);
				dropDownList = (ListView) layout.findViewById(R.id.dropDownList);
				//adapter = new CustomListAdapter(getActivity(),R.layout.patient_profile_row,selectedDropList);
				adapter = new ArrayAdapter(this, R.layout.patient_profile_row, R.id.tv, list);
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
					pwindo.setHeight((int)(175*i));
				}
				pwindo.setWidth(v.getWidth()+(int)(250*i));
				pwindo.setFocusable(true);


				Rect location = Utility.locateView(v);
				pwindo.showAtLocation(v, Gravity.TOP|Gravity.CENTER, location.left-X , location.bottom-Y);
				dropDownList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						pwindo.dismiss();
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
