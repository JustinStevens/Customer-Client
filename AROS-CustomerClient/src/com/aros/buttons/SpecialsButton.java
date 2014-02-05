package com.aros.buttons;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aros.customerclient.Functions;
import com.aros.customerclient.R;

public class SpecialsButton extends IButton {
	private static int MARGINS = 1;
	
	public SpecialsButton(Activity a, int width, int height, int id)
	{
		super(a, id, width, height, MARGINS);
	}
	
	public SpecialsButton(Activity a, int width, int height, int id, int x, int y)
	{
		super(a, id, width, height, MARGINS, x, y);
	}

	@Override
	protected void setImage() {
		img.setImageResource(R.drawable.ad);
	}

	@Override
	protected void setGradients(int width, int height) {
		lbl_top_gradient = Functions.SetGradient(
				new int[] { Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, width / 10, height / 10, 0, 0 },
				Color.DKGRAY, 1);
		
		lbl_btm_gradient = Functions.SetGradient(
				new int[] { Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50)}, 
				new float[]{ width / 10, height / 10, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		btn_psd_gradient = Functions.SetGradient(
				new int[] { Color.argb(120, 255, 255, 255), Color.argb(120, 255, 255, 255), Color.argb(120, 255, 255, 255)}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		btn_nml_gradient = Functions.SetGradient(
				new int[] { Color.argb(0, 0, 0, 0), Color.argb(0, 0, 0, 0), Color.argb(0, 0, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
	}

	@Override
	protected void setText(int height) {
		lbl_top.setText("Some kind of special goes here");
		lbl_top.setTextSize(height / 18);
		
		lbl_btm.setText("Some kind of description");
		lbl_btm.setTextSize(height / 20);
	}

	@Override
	protected void setOnClick() {

	}
}
