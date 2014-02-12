package com.aros.pages;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aros.abstractclasses.AbstractPage;
import com.aros.buttons.ItemButton;
import com.aros.data.Ids;
import com.aros.data.ItemData;
import com.aros.main.MainActivity;

public class ItemPage extends AbstractPage {
	
	public final static short PLAY_SLIDE_IN_FROM_RIGHT_ANIMATION = 3;
	public final static short PLAY_SLIDE_IN_FROM_LEFT_ANIMATION = 4;
	public final static short PLAY_SLIDE_OUT_TO_RIGHT_ANIMATION = 5;
	public final static short PLAY_SLIDE_OUT_TO_LEFT_ANIMATION = 6;
	
	private LinearLayout leftColumn;
	private LinearLayout rightColumn;
	
	public ItemButton[] items;

	int width;
	int height;
	
	public ItemPage (MainActivity a, int id, int width, int height, ItemData[] iData, int startNum)
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
		
		this.items = new ItemButton[6];
		
		for(int i = 0; i < 6; i++)
		{
			if(!(startNum + i >= iData.length) && iData[startNum + i] != null)
			{
				items[i] = new ItemButton(a, itemWidth, itemHeight, Ids.BTN_ITEM_START + iData[startNum + i].id, iData[startNum + i]);
				
				if(i % 2 == 0)
					this.leftColumn.addView(this.items[i].Get());
				else
					this.rightColumn.addView(this.items[i].Get());
			}
			else
				items[i] = null;
		}

		this.pLayout.addView(this.leftColumn);
		this.pLayout.addView(this.rightColumn);
	}
	
	public void SetClickable(boolean clickable)
	{
		for(int i = 0; i < 6; i++)
		{
			if(items[i] != null)
				this.items[i].SetClickable(clickable);
		}
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
