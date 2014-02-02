package com.aros.pages;

import com.aros.customerclient.R;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainPage {
	private RelativeLayout cLayout;
	
	public MainPage (Activity a, RelativeLayout contentLayout)
	{
		this.cLayout = contentLayout;
		ImageView ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(400, 400));
		this.cLayout.addView(ad);
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(400, 400));
		ad.setX(400);
		this.cLayout.addView(ad);
		
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(400, 400));
		ad.setY(400);
		this.cLayout.addView(ad);
		
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(400, 400));
		ad.setX(400);
		ad.setY(400);
		this.cLayout.addView(ad);
	}
}
