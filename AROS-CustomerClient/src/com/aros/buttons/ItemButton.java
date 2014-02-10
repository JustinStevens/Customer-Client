package com.aros.buttons;

import android.graphics.Color;
import android.view.View;

import com.aros.abstractclasses.AbstractImageButton;
import com.aros.customerclient.Functions;
import com.aros.customerclient.ItemInfo;
import com.aros.customerclient.MainActivity;

public class ItemButton extends AbstractImageButton {
	private static int MARGINS = 1;
		
	public ItemButton(MainActivity a, int width, int height, int id, ItemInfo iInfo)
	{
		super(a, id, width, height, MARGINS, iInfo);
	}
	
	public ItemButton(MainActivity a, int width, int height, int id, int x, int y, ItemInfo iInfo)
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
				new float[]{ 0, 0, 0, 0, height / 6, height / 6, 0, 0 },
				Color.DKGRAY, 1);
		
		lbl_btm_gradient = Functions.SetGradient(
				new int[] { Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50)}, 
				new float[]{ height / 6, height / 6, 0, 0, 0, 0, 0, 0 },
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
		 lbl_top.setTextSize(height / 10);
		 
		 lbl_btm.setText("$" + iInfo.price);
		 lbl_btm.setTextSize(height / 10);
	}

	@Override
	protected void setOnClick() {

	}
	
	public void SetClickable(boolean clickable)
	{
		btn.setClickable(clickable);
		btn.setEnabled(clickable);
	}

	@Override
	protected void OnClick(View v) {
		a.OnClick(v);
	}
}
