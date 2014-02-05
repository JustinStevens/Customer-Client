package com.aros.buttons;

import com.aros.customerclient.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public abstract class IButton {
	
	protected GradientDrawable lbl_top_gradient;
	protected GradientDrawable lbl_btm_gradient;
	protected GradientDrawable btn_psd_gradient;
	protected GradientDrawable btn_nml_gradient;
	
	protected TextView lbl_top;
	protected TextView lbl_btm;
	
	protected ImageView img;
	
	protected RelativeLayout container;
	protected Button btn;
	
	private int id;
	
	protected IButton(Activity a, int id, int width, int height, int margins)
	{
		init(a, id, width, height, margins);
	}
	
	protected IButton(Activity a, int id, int width, int height, int margins, int x, int y)
	{
		init(a, id, width, height, margins);
		container.setX(x);
		container.setY(y);
	}
	
	private void init(Activity a, int id, int width, int height, int margins)
	{
		width = width - (margins * 2);
		height = height - (margins * 2);
		
		this.id = id;
		setGradients(width, height);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.setMargins(margins, margins, margins, margins);
		
		this.container = new RelativeLayout(a);
		this.container.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(width, height);
		params.setMargins(margins, margins, margins, margins);
		
		StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, btn_psd_gradient);
	    states.addState(new int[] { }, btn_nml_gradient);
		
		this.btn = new Button(a);
		this.btn.setBackgroundDrawable(states);
		this.btn.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(margins, margins, margins, margins);
		
		this.lbl_top = new TextView(a);
		this.lbl_top.setMaxWidth(width);
		this.lbl_top.setTextColor(Color.WHITE);
		this.lbl_top.setPadding(5, 5, 5, 10);
		this.lbl_top.setBackgroundDrawable(lbl_top_gradient);
		this.lbl_top.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(margins, margins, margins, margins);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		this.lbl_btm = new TextView(a);
		this.lbl_btm.setMaxWidth(width);
		this.lbl_btm.setTextColor(Color.WHITE);
		this.lbl_btm.setPadding(10, 5, 5, 5);
		this.lbl_btm.setGravity(Gravity.RIGHT);
		this.lbl_btm.setBackgroundDrawable(lbl_btm_gradient);
		this.lbl_btm.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(width, height);
		params.setMargins(margins, margins, margins, margins);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		this.img = new ImageView(a);
		this.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
		this.img.setLayoutParams(params);
		
		setText(height);
		setImage();
		
		this.container.addView(img);
		this.container.addView(lbl_top);
		this.container.addView(lbl_btm);
		this.container.addView(btn);
	}
	
	protected abstract void setImage();
	protected abstract void setGradients(int width, int height);
	protected abstract void setText(int height);
	protected abstract void setOnClick();
	
	public int getId() { return id; }
	
	public RelativeLayout Get() { return container; }
}
