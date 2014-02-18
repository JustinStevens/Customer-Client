package framework;

import main.MainActivity;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public abstract class Page {

	public final static short NO_ANIMATION = 0;
	public final static short PLAY_APPEAR_ANIMATION = 1;
	public final static short PLAY_DISAPPEAR_ANIMATION = 2;
	
	protected RelativeLayout pLayout;
	
	protected static Animation fade_in;
	protected static Animation fade_out;
	
	private boolean animSet = false;
	protected boolean animPlaying = false;
	
	// Gradients used to color and set corner radiuses for the menu buttons
	protected GradientDrawable menuButton_normal;
	protected GradientDrawable menuButton_pressed;
	
	private int id = 0;
	
	protected MainActivity a;
	
	public Page(MainActivity a, int id, int width, int height)
	{
		init(a, id, width, height);
	}
	
	private void init(MainActivity a, int id, int width, int height)
	{
		this.id = id;
		this.a = a;
		this.pLayout = new RelativeLayout(a);
		this.pLayout.setLayoutParams(new LayoutParams(width, height));
		this.pLayout.setBackgroundColor(Color.BLACK);
		this.pLayout.setId(id);
		if(!this.animSet)
			setAnimations(width, height);
		
		this.setVisible(false, false);
		
		menuButton_normal = Gradients.SetGradient(
				new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);

		menuButton_pressed = Gradients.SetGradient(
				new int[] { Color.WHITE, Color.LTGRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);
	}

	
	protected Button SetButton(Activity a, String text, int id, int x, int y, int width, int height)
	{		
	    Button button;
		button = new Button(a);
	    button.setLayoutParams(new LayoutParams(width, height));
	    button.setId(id);
	    button.setText(text);
	    button.setTextSize((float) (height / 4));
	    button.setX(x);
	    button.setY(y);
	    button.setPadding(0, 0, 0, 0);
	    button.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	    
	    return button;
	}
	
	private void setAnimations(int width, int height)
	{
		fade_in = new AlphaAnimation(0, 1);
		fade_in.setInterpolator(new AccelerateDecelerateInterpolator());
		fade_in.setDuration(650);
		fade_out = new AlphaAnimation(1, 0);
		fade_out.setInterpolator(new AccelerateDecelerateInterpolator());
		fade_out.setDuration(650);
		
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
	
	public boolean IsInAnimationPlaying()
	{
		return animPlaying;
	}

	public void setVisible(boolean b, boolean playAnim) {
		if(!b)
		{
			if(playAnim)
				PlayAnimation(PLAY_DISAPPEAR_ANIMATION);
			pLayout.setVisibility(View.GONE);
		}
		else
		{
			if(playAnim)
				PlayAnimation(PLAY_APPEAR_ANIMATION);
			pLayout.setVisibility(View.VISIBLE);
		}
	}
	
	public void setXY(int x, int y)
	{
		pLayout.setX(x);
		pLayout.setY(y);
	}
	
	public int GetId() { return id; }
	public RelativeLayout get() { return pLayout; }
	public abstract void OnClick(View v);
}
