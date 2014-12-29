package com.android.pet.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.android.pet.listeners.CatogiryClickListener;
import com.android.pet.util.Appcontext;
import com.android.pet.util.Utility;
import com.doepiccoding.navigationdrawer.R;

public class CatogiryFragment extends Fragment{
	GridView gridView;
	CatogiryClickListener mCallBack;
	Adapter customAdapter;
	private ImageSwitcher imgSwitcher;
	private int[] imageIds = {R.drawable.slider_1,R.drawable.slider_2,R.drawable.slider_3,R.drawable.slider_4,R.drawable.slider_5};
	int imgSwitchCounter;
	long timerChangeTime = 3000;
	Timer timer;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		try {
			v = inflater.inflate(R.layout.catogiry, null);
			gridView = (GridView) v.findViewById(R.id.catogiryList);
			customAdapter = new Adapter(getActivity(), R.layout.catogiry_item);
			imgSwitcher = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
			imgSwitcher.setFactory((new ViewFactory() {

				   @Override
				   public View makeView() {

				    ImageView imageView = new ImageView(getActivity());
				    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

				    LayoutParams params = new ImageSwitcher.LayoutParams(
				      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

				    imageView.setLayoutParams(params);
				    return imageView;

				   }
				  }));
//			imgSwitcher.setFactory(container);
			Animation fadein = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
			Animation fadeout = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
			fadein.setDuration(1500);
			fadeout.setDuration(1500);
			imgSwitcher.setInAnimation(fadein);
			imgSwitcher.setOutAnimation(fadeout);
			gridView.setAdapter(customAdapter);
			timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					advHandler.sendEmptyMessage(0);
				}
			}, 0, timerChangeTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	Handler advHandler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			setAdvImage();
		};
	};
	public void setAdvImage(){
		try {
			if(imgSwitchCounter>=imageIds.length){
				imgSwitchCounter = 0;
			}
			imgSwitcher.setImageResource(imageIds[imgSwitchCounter]);
			imgSwitchCounter++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class Adapter extends ArrayAdapter {

		private LayoutInflater vi;

		public Adapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
			vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return Appcontext.getInstance().getCatogiryList().size();
		}

		@Override
		public View getView(final int position, View convertView,ViewGroup parent) {
			View v = convertView;
			try {
				Holder holder = null;
				
				if (v == null) {
					holder = new Holder();
					v = vi.inflate(R.layout.catogiry_item,	null);
					holder.image1 = (ImageView) v.findViewById(R.id.image1);
					holder.image2 = (ImageView) v.findViewById(R.id.image2);
					holder.image3 = (ImageView) v.findViewById(R.id.image3);
					holder.image4 = (ImageView) v.findViewById(R.id.image4);
					holder. productName = (TextView) v.findViewById(R.id.catogiryName);
					holder.mainll = (LinearLayout) v.findViewById(R.id.mailLL);
					v.setTag(holder);
				}else{
					holder = (Holder) v.getTag();
				}
				int id = getResources().getIdentifier(Appcontext.getInstance().getCatogiryList().get(position).getImage1(), "drawable",getActivity().getPackageName());
				holder.image1.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), id, 50, 50));
				
				id = getResources().getIdentifier(Appcontext.getInstance().getCatogiryList().get(position).getImage2(), "drawable",getActivity().getPackageName());
				holder.image2.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), id, 50, 50));
				
				id = getResources().getIdentifier(Appcontext.getInstance().getCatogiryList().get(position).getImage3(), "drawable",getActivity().getPackageName());
				holder.image3.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), id, 50, 50));
				
				id = getResources().getIdentifier(Appcontext.getInstance().getCatogiryList().get(position).getImage4(), "drawable",getActivity().getPackageName());
				holder.image4.setImageBitmap(Utility.decodeSampledBitmapFromResource(getResources(), id,50,50));
				
				holder.productName.setText(Appcontext.getInstance().getCatogiryList().get(position).getTitle());
				holder.mainll.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						mCallBack.onClickProduct(position);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return v;
		}
		public class Holder{
			ImageView image1,image2,image3,image4 ;
			TextView productName;
			LinearLayout mainll;
		}
	}
	public void setCallBack(CatogiryClickListener mCallBack){
		this.mCallBack = mCallBack;
	}
}
