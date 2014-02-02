package com.aros.layouts;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aros.customerclient.R;

public class MainPage {
	private MainLayout mLayout;
	private RelativeLayout cLayout;
	
	int width;
	int height;
	
	public MainPage (Activity a, MainLayout mainLayout)
	{
		this.mLayout = mainLayout;
		this.cLayout = mLayout.GetContent();
		this.width = mLayout.getContent_width();
		this.height = mLayout.getContent_height();
		
		int adSize = height / 2;
		int adjust = width - (adSize + adSize);
		
		ImageView ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(adSize, adSize));
		ad.setX(adjust);
		this.cLayout.addView(ad);
		
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(adSize, adSize));
		ad.setX(adSize + adjust);
		this.cLayout.addView(ad);
		
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(adSize, adSize));
		ad.setX(adjust);
		ad.setY(adSize);
		this.cLayout.addView(ad);
		
		ad = new ImageView(a);
		ad.setBackgroundResource(R.drawable.ad);
		ad.setLayoutParams(new LayoutParams(adSize, adSize));
		ad.setX(adSize + adjust);
		ad.setY(adSize);
		this.cLayout.addView(ad);
	}
}
