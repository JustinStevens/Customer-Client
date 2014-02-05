package com.aros.pages;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.aros.buttons.SpecialsButton;
import com.aros.layouts.MainLayout;

public class MainPage extends Page {
	
	private MainLayout mLayout;
	private RelativeLayout cLayout;
	
	public SpecialsButton ad_1;
	public SpecialsButton ad_2;
	public SpecialsButton ad_3;
	public SpecialsButton ad_4;
	
	int width;
	int height;
	
	public final static int ID = 0;
	
	public MainPage (Activity a, MainLayout mainLayout)
	{
		in = new AlphaAnimation(0, 1);
		in.setInterpolator(new DecelerateInterpolator()); //add this
		in.setDuration(1000);
		
		out = new AlphaAnimation(0, 1);
		out.setInterpolator(new DecelerateInterpolator()); //add this
		out.setDuration(1000);
		
		
		this.mLayout = mainLayout;
		this.width = mLayout.getContent_width();
		this.height = mLayout.getContent_height();
		
		this.cLayout = new RelativeLayout(a);
		this.cLayout.setLayoutParams(new LayoutParams(width, height));
		
		int adSize = height / 2;
		int adjust = width - (adSize + adSize);
		
		ad_1 = new SpecialsButton(a, adSize, adSize, 1, width - adSize * 2, 0);
		this.cLayout.addView(ad_1.Get());
		
		ad_2 = new SpecialsButton(a, adSize, adSize, 2, width - adSize, 0);
		this.cLayout.addView(ad_2.Get());
		
		ad_3 = new SpecialsButton(a, adSize, adSize, 3, width - adSize * 2, adSize);
		this.cLayout.addView(ad_3.Get());
		
		ad_4 = new SpecialsButton(a, adSize, adSize, 4, width - adSize, adSize);
		this.cLayout.addView(ad_4.Get());
	}
	
	public RelativeLayout Get(boolean in)
	{
		if(in)
			cLayout.startAnimation(this.in);
		else
			cLayout.startAnimation(this.out);
		return cLayout;
	}
}
