package com.aros.abstractclasses;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.aros.main.MainActivity;

public abstract class AbstractImageButton {
	
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
	
	protected MainActivity a;
	
	protected AbstractImageButton(MainActivity a, int id, int width, int height, int margins, int imgResId, String btm_text, String top_text)
	{
		init(a, id, width, height, margins, imgResId, btm_text, top_text);
	}
	
	protected AbstractImageButton(MainActivity a, int id, int width, int height, int margins, int x, int y, int imgResId, String btm_text, String top_text)
	{
		init(a, id, width, height, margins, imgResId, btm_text, top_text);
		container.setX(x);
		container.setY(y);
	}
	
	@SuppressWarnings("deprecation")
	private void init(MainActivity a, int id, int width, int height, int margins, int imgResId, String btm_text, String top_text)
	{
		width = width - (margins * 2);
		height = height - (margins * 2);
		this.a = a;
		this.id = id;
		setGradients(width, height);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		//params.setMargins(margins, margins, margins, margins);
		
		this.container = new RelativeLayout(a);
		this.container.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(width, height);
		//params.setMargins(margins, margins, margins, margins);
		
		StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, btn_psd_gradient);
	    states.addState(new int[] { }, btn_nml_gradient);
		
		this.btn = new Button(a);
		this.btn.setBackgroundDrawable(states);
		this.btn.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//params.setMargins(margins, margins, margins, margins);
		
		this.lbl_top = new TextView(a);
		this.lbl_top.setMaxWidth(width);
		this.lbl_top.setTextColor(Color.WHITE);
		this.lbl_top.setPadding(5, 5, 15, 10);
		this.lbl_top.setBackgroundDrawable(lbl_top_gradient);
		this.lbl_top.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//params.setMargins(margins, margins, margins, margins);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		this.lbl_btm = new TextView(a);
		this.lbl_btm.setMaxWidth(width);
		this.lbl_btm.setTextColor(Color.WHITE);
		this.lbl_btm.setPadding(15, 5, 5, 5);
		this.lbl_btm.setGravity(Gravity.RIGHT);
		this.lbl_btm.setBackgroundDrawable(lbl_btm_gradient);
		this.lbl_btm.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(width, height);
		//params.setMargins(margins, margins, margins, margins);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		this.img = new ImageView(a);
		this.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
		this.img.setLayoutParams(params);
		
		setText(height, btm_text, top_text);
		setImage(imgResId);
		
		this.container.addView(img);
		this.container.addView(lbl_top);
		this.container.addView(lbl_btm);
		this.container.addView(btn);
		
		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	}
	
	protected abstract void setImage(int resId);
	protected abstract void setGradients(int width, int height);
	protected abstract void setText(int height, String top_text, String btm_text);
	protected abstract void setOnClick();
	protected abstract void OnClick(View v);
	
	public int getId() { return id; }
	
	public RelativeLayout Get() { return container; }
}
