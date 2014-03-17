package pages;

import java.util.ArrayList;

import main.MainActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import buttons.ItemButton;
import data.Data;
import data.ItemData;
import framework.Page;

public class MenuSubPage extends Page{

	public final static short PLAY_SLIDE_IN_FROM_RIGHT_ANIMATION = 3;
	public final static short PLAY_SLIDE_IN_FROM_LEFT_ANIMATION = 4;
	public final static short PLAY_SLIDE_OUT_TO_RIGHT_ANIMATION = 5;
	public final static short PLAY_SLIDE_OUT_TO_LEFT_ANIMATION = 6;
	public final static short PLAY_FADE_OUT_ANIMATION = 7;
	public final static short PLAY_FADE_IN_ANIMATION = 8;
	
	
	private LinearLayout leftColumn;
	private LinearLayout rightColumn;
	
	public ItemButton[] items;

	int width;
	int height;
	
	private static final float FADE_AMOUNT = 0.35f;
	
	Animation fadeOut;
	Animation fadeIn;
	
	MenuPage mPage;
	
	public MenuSubPage (MainActivity a, int id, int width, int height, MenuPage mPage)
	{
		super(a, id, width, height);
		this.width = width;
		this.height = height;
		this.mPage = mPage;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Data.item_btn_width, height);
		params.setMargins(0, 0, 0, 0);
		
		this.leftColumn = new LinearLayout(a);
		this.leftColumn.setOrientation(LinearLayout.VERTICAL);
		this.leftColumn.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(Data.item_btn_width, height);
		params.setMargins(Data.item_btn_width, 0, 0, 0);
		this.rightColumn = new LinearLayout(a);
		this.rightColumn.setOrientation(LinearLayout.VERTICAL);
		this.rightColumn.setLayoutParams(params);
		
		fadeOut = new AlphaAnimation(1, FADE_AMOUNT);
		fadeOut.setDuration(300);
		fadeOut.setFillAfter(true);
		
		fadeIn = new AlphaAnimation(FADE_AMOUNT, 1);
		fadeIn.setDuration(300);
		fadeIn.setFillAfter(true);
		
		this.items = new ItemButton[6];
		
		for(int i = 0; i < 6; i++)
		{
			items[i] = new ItemButton(a, Data.item_btn_width, Data.item_btn_height, i, true);
			
			if(i % 2 == 0)
				this.leftColumn.addView(this.items[i].Get());
			else
				this.rightColumn.addView(this.items[i].Get());
		}

		this.pLayout.addView(this.leftColumn);
		this.pLayout.addView(this.rightColumn);
	}
	
	public void SetButtonData(ArrayList<ItemData> iData, int startPos)
	{
		if(iData != null)
		{
			for(int i = 0; i < 6; i++)
			{
				if(iData.size() > i + startPos && iData.get(i + startPos) != null)
				{
					this.items[i].setData(iData.get(i + startPos));
					this.items[i].Get().setAlpha(1);
					this.items[i].SetClickable(true);
					this.items[i].setId(iData.get(i + startPos).id + Data.BTN_ITEM_START);
				}
				else
				{
					this.items[i].Get().setAlpha(0);
					this.items[i].SetClickable(false);
				}
			}
		}
		else
			for(int i = 0; i < 6; i++)
				this.items[i].Get().setAlpha(0);
	}
	
	public void SetClickable(boolean clickable)
	{
		for(int i = 0; i < 6; i++)
		{
			if(items[i] != null)
				this.items[i].SetClickable(clickable);
		}
	}
	
	public void PlayAnimation(boolean in)
	{
		if(in)
			pLayout.startAnimation(fadeIn);
		else
			pLayout.startAnimation(fadeOut);
	}
	
	public void PlayAnimation(int startX, int endX, int direction, int duration)
	{
		SetAnimation(startX, endX, direction, duration);	
		pLayout.startAnimation(slideAnimation);
	}
	
	private Animation slideAnimation;
	
	public void SetAnimation(int startX, int endX, final int direction, int duration)
	{
		slideAnimation = new TranslateAnimation(startX, endX, 0, 0);
		slideAnimation.setDuration(duration);
		slideAnimation.setInterpolator(new DecelerateInterpolator());
		slideAnimation.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) 
            { 
            	animPlaying = true; 
            }

            @Override
            public void onAnimationEnd(Animation animation) 
            { 
            	animPlaying = false;
            	if(direction == 1)
            		mPage.setNextDefaultX();
            	else if(direction == -1)
            		mPage.setPrevDefaultX();
            }

            
            public void onAnimationRepeat(Animation animation) { }
        });
	}

	@Override
	public void OnClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
