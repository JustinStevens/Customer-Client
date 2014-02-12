package com.aros.buttons;

import android.graphics.Color;
import android.view.View;

import com.aros.abstractclasses.AbstractImageButton;
import com.aros.data.ItemData;
import com.aros.data.SpecialsData;
import com.aros.main.Functions;
import com.aros.main.MainActivity;

public class SpecialsButton extends AbstractImageButton {
	private static int MARGINS = 1;
	
	public SpecialsButton(MainActivity a, int width, int height, int id, SpecialsData sData)
	{
		super(a, id, width, height, MARGINS, sData.imgResId, sData.title, sData.desc);
	}
	
	public SpecialsButton(MainActivity a, int width, int height, int id, int x, int y, SpecialsData sData)
	{
		super(a, id, width, height, MARGINS, x, y, sData.imgResId, sData.title, sData.desc);
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
	protected void setText(int height, String top_text, String btm_text) {
		lbl_top.setText(top_text);
		lbl_top.setTextSize(height / 16);
		
		lbl_btm.setText(btm_text);
		lbl_btm.setTextSize(height / 18);
	}

	@Override
	protected void setOnClick() {

	}

	@Override
	protected void OnClick(View v) {
		a.OnClick(v);
	}
}
