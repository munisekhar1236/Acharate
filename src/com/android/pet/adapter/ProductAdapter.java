package com.android.pet.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pet.model.Product;
import com.android.pet.util.Utility;
import com.doepiccoding.navigationdrawer.R;

public class ProductAdapter extends ArrayAdapter<Product> {

	private List<Product> productList;
	private Context context;

	private android.view.View.OnClickListener onClickListener;
 
	public ProductAdapter(List<Product> personList, Context ctx,
			OnClickListener onClickListener) {
		super(ctx, R.layout.product_item, personList);
		this.productList = personList;
		this.context = ctx;

		this.onClickListener = onClickListener;
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Product getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return productList.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		try {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// First let's verify the convertView is not null
			if (convertView == null) {
				
				 holder =new Holder();
				 
				 convertView = inflater.inflate(R.layout.product_item, null);
				holder.productImage = (ImageView) convertView.findViewById(R.id.productImage);
				holder.offerPrice = (TextView) convertView.findViewById(R.id.offerPrice);
				holder.productName = (TextView) convertView.findViewById(R.id.productName);
				holder.mrpPrice = (TextView) convertView.findViewById(R.id.mrpPrice);
				holder.arpPrice = (TextView) convertView.findViewById(R.id.arpPrice);
				holder.saveProdBtn = (CheckBox) convertView.findViewById(R.id.saveProdBtn);

				holder.saveProdBtn.setOnClickListener(onClickListener);


				convertView.setTag(holder);
				
			} else
				holder = (Holder) convertView.getTag();

			Product product = getItem(position);

			holder.productImage.setBackgroundResource(android.R.color.transparent);
			int id = context.getResources().getIdentifier(product.getImagePath(),
					"drawable", context.getPackageName());
			holder.productImage.setImageBitmap(Utility.decodeSampledBitmapFromResource(context.getResources(),id, 100, 100));
			holder.productName.setText(product.getProductName());
			holder.mrpPrice.setText(product.getProductMRP() + "");
			holder.arpPrice.setText(product.getProductARCP() + "");
			holder.offerPrice.setText(product.getOfferPrice() + "% \n Offer");

			holder.saveProdBtn.setTag(position);
			
			if(product.isSaved()){
				
				holder.saveProdBtn.setChecked(true);
			}else{
				
				holder.saveProdBtn.setChecked(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//holder.saveProdBtn.setChecked(false);
		return convertView;
	}

	private static class Holder {

		ImageView productImage;
		TextView offerPrice;
		TextView productName;
		TextView mrpPrice;
		TextView arpPrice;
		CheckBox saveProdBtn;

	}

}
