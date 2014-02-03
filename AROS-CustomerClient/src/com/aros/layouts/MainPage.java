package com.aros.layouts;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aros.customerclient.R;

public class MainPage {
	private MainLayout mLayout;
	private RelativeLayout cLayout;
	
	public AdLayout ad_1;
	public AdLayout ad_2;
	public AdLayout ad_3;
	public AdLayout ad_4;
	
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
		
		ad_1 = new AdLayout(a, adSize, adjust, 0);
		this.cLayout.addView(ad_1.Get());
		
		ad_2 = new AdLayout(a, adSize, adSize + adjust, 0);
		this.cLayout.addView(ad_2.Get());
		
		ad_3 = new AdLayout(a, adSize, adjust, adSize);
		this.cLayout.addView(ad_3.Get());
		
		ad_4 = new AdLayout(a, adSize, adSize + adjust, adSize);
		this.cLayout.addView(ad_4.Get());
	}
}
