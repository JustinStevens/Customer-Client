package buttons;

import main.MainActivity;
import android.graphics.Color;
import android.view.View;
import data.Data;
import data.ItemData;
import framework.Gradients;
import framework.ImageButton;

public class ItemButton extends ImageButton {
	private static int MARGINS = 1;
	private float fontScale = 1;
		
	public ItemButton(MainActivity a, int width, int height, int id, boolean addButton)
	{
		super(a, id, width, height, MARGINS, addButton);
	}
	
	public ItemButton(MainActivity a, int width, int height, int id, boolean addButton, boolean border)
	{
		super(a, id, width, height, MARGINS, addButton, border);
	}
	
	public ItemButton(MainActivity a, int width, int height, int id, int x, int y, boolean addButton)
	{
		super(a, id, width, height, MARGINS, x, y, addButton);
	}
	
	public ItemButton(MainActivity a, int width, int height, int id, int x, int y, boolean addButton, boolean border)
	{
		super(a, id, width, height, MARGINS, x, y, addButton, border);
	}
	
	@Override
	protected void setImage(int resId) {
		img.setImageResource(resId);
	}

	@Override
	protected void setGradients(int width, int height) {
		
		lbl_top_gradient = Gradients.SetGradient(
				new int[] { Color.argb(180, 180, 0, 0), Color.argb(180, 180, 0, 0), Color.argb(180, 180, 0, 0)}, 
				new float[]{ 0, 0, 0, 0, height / 6, height / 6, 0, 0 },
				Color.DKGRAY, 1);
		
		lbl_btm_gradient = Gradients.SetGradient(
				new int[] { Color.argb(180, 50, 50, 50), Color.argb(180, 50, 50, 50), Color.argb(180, 50, 50, 50)}, 
				new float[]{ height / 6, height / 6, 0, 0, 0, 0, 0, 0 },
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
	
	public void setData(ItemData iData)
	{
		setText(Data.item_btn_height, iData.name, "$" + iData.price / 100);
		lbl_top.setText(iData.name);
		lbl_btm.setText("$" + iData.price / 100);
		setImage(iData.smallImgResId);
	}
	
	public void setFontScale(float fontScale)
	{
		this.fontScale = fontScale;
	}
	
	@SuppressWarnings("deprecation")
	public void setData(String top_lbl, String btm_lbl, int imgRes, int sizeAdjust)
	{
		setText(sizeAdjust, top_lbl, btm_lbl);
		setGradients(sizeAdjust, sizeAdjust);
		this.lbl_top.setBackgroundDrawable(lbl_top_gradient);
		this.lbl_btm.setBackgroundDrawable(lbl_btm_gradient);
		setImage(imgRes);
	}

	@Override
	protected void setText(int height, String top_text, String btm_text) {
		if(top_text != null)
		{
			 lbl_top.setText(top_text);
			 if(height > 0)
				 lbl_top.setTextSize(height / 8 * fontScale);
			 lbl_top.setVisibility(View.VISIBLE);
		}
		else
			lbl_top.setVisibility(View.GONE);
		 
		if(btm_text != null)
		{
			 lbl_btm.setText(btm_text);
			 if(height > 0)
				 lbl_btm.setTextSize(height / 8 * fontScale);
			 lbl_btm.setVisibility(View.VISIBLE);
		}
		else
			lbl_btm.setVisibility(View.GONE);
	}

	@Override
	protected void setOnClick() {
		
	}
	
	public void setId(int id)
	{
		btn.setId(id);
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
