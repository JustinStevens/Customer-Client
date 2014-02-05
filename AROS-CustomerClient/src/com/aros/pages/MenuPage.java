package com.aros.pages;

import com.aros.buttons.ItemButton;
import com.aros.layouts.MainLayout;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MenuPage extends Page {
	
	private MainLayout mLayout;
	private RelativeLayout cLayout;
	private LinearLayout leftColumn;
	
	public ItemButton ad_1;
	public ItemButton ad_2;
	public ItemButton ad_3;
	public ItemButton ad_4;
	
	int width;
	int height;
	
	public final static int ID = 0;
	
	public MenuPage (Activity a, MainLayout mainLayout)
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
		
		int itemWidth = width / 2;
		int itemHeight = (int) (itemWidth / 2.5);
		
		this.leftColumn = new LinearLayout(a);
		this.leftColumn.setLayoutParams(new LayoutParams(itemWidth, height));
		this.leftColumn.setOrientation(LinearLayout.VERTICAL);
		ad_1 = new ItemButton(a, itemWidth, itemHeight, 1);
		this.leftColumn.addView(ad_1.Get());
		
		ad_2 = new ItemButton(a, itemWidth, itemHeight, 2);
		this.leftColumn.addView(ad_2.Get());
		
		ad_3 = new ItemButton(a, itemWidth, itemHeight, 3);
		this.leftColumn.addView(ad_3.Get());
		
		ad_4 = new ItemButton(a, itemWidth, itemHeight, 4);
		this.leftColumn.addView(ad_4.Get());
		
		this.cLayout.addView(leftColumn);
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
