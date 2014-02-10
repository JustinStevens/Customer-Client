package com.aros.abstractclasses;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.aros.customerclient.Functions;

public abstract class AbstractPage {
	
	public final static short NO_ANIMATION = 0;
	public final static short PLAY_APPEAR_ANIMATION = 1;
	public final static short PLAY_DISAPPEAR_ANIMATION = 2;
	
	protected RelativeLayout pLayout;
	
	protected static Animation fade_in;
	protected static Animation fade_out;
	
	private boolean animSet = false;
	protected static boolean animPlaying = false;
	
	// Gradients used to color and set corner radiuses for the menu buttons
	protected GradientDrawable menuButton_normal;
	protected GradientDrawable menuButton_pressed;
	
	private int id = 0;
	
	public AbstractPage(Activity a, int id, int width, int height)
	{
		init(a, id, width, height);
	}
	
	private void init(Activity a, int id, int width, int height)
	{
		this.id = id;
		this.pLayout = new RelativeLayout(a);
		this.pLayout.setLayoutParams(new LayoutParams(width, height));
		this.pLayout.setBackgroundColor(Color.DKGRAY);
		this.pLayout.setId(id);
		if(!this.animSet)
			setAnimations(width, height);
		
		this.setVisible(false);
		
		menuButton_normal = Functions.SetGradient(
				new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);

		menuButton_pressed = Functions.SetGradient(
				new int[] { Color.WHITE, Color.LTGRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);
	}
	
	private void setAnimations(int width, int height)
	{
		fade_in = new AlphaAnimation(0, 1);
		fade_in.setInterpolator(new AccelerateDecelerateInterpolator());
		fade_in.setDuration(1000);
		fade_out = new AlphaAnimation(1, 0);
		fade_out.setInterpolator(new AccelerateDecelerateInterpolator());
		fade_out.setDuration(1000);
		
		/*in = new TranslateAnimation(start_x, start_y, end_x, end_y);
		animation.setDuration(1000); // duartion in ms
		animation.setFillAfter(false);*/
		
		fade_in.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) { animPlaying = true; }

            @Override
            public void onAnimationEnd(Animation animation) { animPlaying = false;}

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
		
		fade_out.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) { animPlaying = true; }

            @Override
            public void onAnimationEnd(Animation animation) { animPlaying = false;}

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
		
		
		this.animSet = true;
	}
	
	public void PlayAnimation(short animation)
	{
		switch(animation)
		{
		case PLAY_APPEAR_ANIMATION: 		pLayout.startAnimation(fade_in);  break;
		case PLAY_DISAPPEAR_ANIMATION: 		pLayout.startAnimation(fade_out); break;
		}
	}
	
	public static boolean IsInAnimationPlaying()
	{
		return animPlaying;
	}

	public void setVisible(boolean b) {
		if(!b)
			pLayout.setVisibility(View.GONE);
		else
			pLayout.setVisibility(View.VISIBLE);
	}
	
	public int GetId() { return id; }
	public RelativeLayout Get() { return pLayout; }
		
}
