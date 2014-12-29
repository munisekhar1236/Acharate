package com.android.pet.view;

import android.content.Context;
import android.widget.ExpandableListView;

 public class CustExpListview extends ExpandableListView {
	 
    int intGroupPosition, intChildPosition, intGroupid;

    public CustExpListview(Context context) {
           super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           widthMeasureSpec = MeasureSpec.makeMeasureSpec(400,
                        MeasureSpec.EXACTLY);
           heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000,
                        MeasureSpec.AT_MOST);
           super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
