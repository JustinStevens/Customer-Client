package com.aros.buttons;

import android.app.Activity;
import android.graphics.Color;

import com.aros.abstractclasses.AbstractImageButton;
import com.aros.customerclient.Functions;
import com.aros.customerclient.ItemInfo;
import com.aros.customerclient.R;

public class SpecialsButton extends AbstractImageButton {
	private static int MARGINS = 1;
	
	public SpecialsButton(Activity a, int width, int height, int id, ItemInfo iInfo)
	{
		super(a, id, width, height, MARGINS, iInfo);
	}
	
	public SpecialsButton(Activity a, int width, int height, int id, int x, int y, ItemInfo iInfo)
	{
		super(a, id, width, height, MARGINS, x, y, iInfo);
	}

	@Override
	protected void setImage(int resId) {
		img.setImageResource(resId);
	}

	@Override
	protected void setGradients(int width, int height) {
		lbl_top_gradient = Functions.SetGradient(
				new int[] { Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, height / 10, height / 10, 0, 0 },
				Color.DKGRAY, 1);
		
		lbl_btm_gradient = Functions.SetGradient(
				new int[] { Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50)}, 
				new float[]{ height / 10, height / 10, 0, 0, 0, 0, 0, 0 },
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
	protected void setText(int height, ItemInfo iInfo) {
		lbl_top.setText(iInfo.name);
		lbl_top.setTextSize(height / 16);
		
		lbl_btm.setText(iInfo.desc);
		lbl_btm.setTextSize(height / 18);
	}

	@Override
	protected void setOnClick() {

	}
}
