package com.aros.pages;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aros.abstractclasses.AbstractPage;
import com.aros.buttons.ItemButton;
import com.aros.customerclient.ItemInfo;

public class ItemPage extends AbstractPage {
	
	public final static short PLAY_SLIDE_IN_FROM_RIGHT_ANIMATION = 3;
	public final static short PLAY_SLIDE_IN_FROM_LEFT_ANIMATION = 4;
	public final static short PLAY_SLIDE_OUT_TO_RIGHT_ANIMATION = 5;
	public final static short PLAY_SLIDE_OUT_TO_LEFT_ANIMATION = 6;
	
	private LinearLayout leftColumn;
	private LinearLayout rightColumn;
	
	public ItemButton item_1;
	public ItemButton item_2;
	public ItemButton item_3;
	public ItemButton item_4;
	public ItemButton item_5;
	public ItemButton item_6;

	int width;
	int height;
	
	public ItemPage (Activity a, int id, int width, int height, ItemInfo[] iInfo)
	{
		super(a, id, width, height);
		this.width = width;
		this.height = height;
				
		int itemWidth = (width) / 2 + 1;
		int itemHeight = (int) (height / 3) + 1;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(itemWidth, height);
		params.setMargins(0, 0, 0, 0);
		
		this.leftColumn = new LinearLayout(a);
		this.leftColumn.setOrientation(LinearLayout.VERTICAL);
		this.leftColumn.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(itemWidth, height);
		params.setMargins(itemWidth, 0, 0, 0);
		this.rightColumn = new LinearLayout(a);
		this.rightColumn.setOrientation(LinearLayout.VERTICAL);
		this.rightColumn.setLayoutParams(params);
		
		this.item_1 = new ItemButton(a, itemWidth, itemHeight, 1, iInfo[0]);
		this.item_2 = new ItemButton(a, itemWidth, itemHeight, 2, iInfo[1]);
		this.item_3 = new ItemButton(a, itemWidth, itemHeight, 3, iInfo[2]);
		this.item_4 = new ItemButton(a, itemWidth, itemHeight, 4, iInfo[3]);
		this.item_5 = new ItemButton(a, itemWidth, itemHeight, 5, iInfo[4]);
		this.item_6 = new ItemButton(a, itemWidth, itemHeight, 6, iInfo[5]);
		
		this.leftColumn.addView(this.item_1.Get());
		this.leftColumn.addView(this.item_2.Get());
		this.leftColumn.addView(this.item_3.Get());
		this.rightColumn.addView(this.item_4.Get());
		this.rightColumn.addView(this.item_5.Get());
		this.rightColumn.addView(this.item_6.Get());
		
		this.pLayout.addView(this.leftColumn);
		this.pLayout.addView(this.rightColumn);
	}
	
	public void SetClickable(boolean clickable)
	{
		this.item_1.Get().setClickable(clickable);
		this.item_2.Get().setClickable(clickable);
		this.item_3.Get().setClickable(clickable);
		this.item_4.Get().setClickable(clickable);
		this.item_5.Get().setClickable(clickable);
		this.item_6.Get().setClickable(clickable);
	}
	
	public void PlayAnimation(int startX, int endX)
	{
		SetAnimation(startX, endX);	
		pLayout.startAnimation(slideAnimation);
	}
	
	private Animation slideAnimation;
	
	public void SetAnimation(int startX, int endX)
	{
		slideAnimation = new TranslateAnimation(startX, endX, 0, 0);
		slideAnimation.setDuration(300);
		slideAnimation.setFillAfter(false);
		slideAnimation.setInterpolator(new DecelerateInterpolator());
		slideAnimation.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) { animPlaying = true; }

            @Override
            public void onAnimationEnd(Animation animation) { animPlaying = false;}

            
            public void onAnimationRepeat(Animation animation) { }
        });
	}
}
