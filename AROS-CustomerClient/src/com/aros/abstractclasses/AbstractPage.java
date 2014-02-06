package com.aros.abstractclasses;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public abstract class AbstractPage {
	
	public final static short NO_ANIMATION = 0;
	public final static short PLAY_APPEAR_ANIMATION = 1;
	public final static short PLAY_DISAPPEAR_ANIMATION = 2;
	
	protected RelativeLayout pLayout;
	
	protected static Animation in;
	protected static Animation out;
	
	private boolean animSet = false;
	private static boolean in_animPlaying = false;
	private static boolean out_animPlaying = false;
	
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
		if(!this.animSet)
			setAnimations();
		
		this.setVisible(false);
	}
	
	private void setAnimations()
	{
		in = new AlphaAnimation(0, 1);
		in.setInterpolator(new AccelerateDecelerateInterpolator());
		in.setDuration(1000);
		out = new AlphaAnimation(1, 0);
		out.setInterpolator(new AccelerateDecelerateInterpolator());
		out.setDuration(1000);
		
		in.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) { in_animPlaying = true; }

            @Override
            public void onAnimationEnd(Animation animation) { in_animPlaying = false;}

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
		
		out.setAnimationListener(new Animation.AnimationListener() 
		{
            @Override
            public void onAnimationStart(Animation animation) { out_animPlaying = true; }

            @Override
            public void onAnimationEnd(Animation animation) { out_animPlaying = false;}

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
		
		this.animSet = true;
	}
	
	public void PlayAnimation(short animation)
	{
		switch(animation)
		{
		case PLAY_APPEAR_ANIMATION: 	pLayout.startAnimation(in);	 break;
		case PLAY_DISAPPEAR_ANIMATION: 	pLayout.startAnimation(out); break;
		}
	}
	
	public static boolean IsInAnimationPlaying()
	{
		return in_animPlaying;
	}
	
	public static boolean IsOutAnimationPlaying()
	{
		return out_animPlaying;
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
