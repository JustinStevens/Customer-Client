package buttons;

import main.MainActivity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import data.SpecialsData;
import framework.Gradients;
import framework.ImageButton;

public class SpecialsButton extends ImageButton {
	private static int MARGINS = 1;
	int width;
	int height;
	
	public SpecialsButton(MainActivity a, int width, int height, int id)
	{
		super(a, id, width, height, MARGINS);
	}
	
	public SpecialsButton(MainActivity a, int width, int height, int id, int x, int y)
	{
		super(a, id, width, height, MARGINS, x, y);
	}

	@Override
	protected void setImage(int resId) {
		img.setImageResource(resId);
	}

	@Override
	protected void setGradients(int width, int height) {
		lbl_top_gradient = Gradients.SetGradient(
				new int[] { Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0), Color.argb(200, 180, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, height / 10, height / 10, 0, 0 },
				Color.DKGRAY, 1);
		
		lbl_btm_gradient = Gradients.SetGradient(
				new int[] { Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50), Color.argb(200, 50, 50, 50)}, 
				new float[]{ height / 10, height / 10, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		btn_psd_gradient = Gradients.SetGradient(
				new int[] { Color.argb(120, 255, 255, 255), Color.argb(120, 255, 255, 255), Color.argb(120, 255, 255, 255)}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		btn_nml_gradient = Gradients.SetGradient(
				new int[] { Color.argb(0, 0, 0, 0), Color.argb(0, 0, 0, 0), Color.argb(0, 0, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
	}

	@Override
	protected void setText(int height, String top_text, String btm_text) {
		lbl_top.setText(top_text);
		lbl_top.setTextSize(height / 15);
		
		lbl_btm.setText(btm_text);
		lbl_btm.setTextSize(height / 19);
	}

	@Override
	protected void setOnClick() {

	}

	@Override
	protected void OnClick(View v) {
		a.OnClick(v);
	}
	
	public void setData(SpecialsData sData)
	{
		lbl_top.setText(sData.title);
		lbl_btm.setText(sData.desc);
		setImage(sData.imgResId);
	}
}
