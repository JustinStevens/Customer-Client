package com.aros.pages;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.aros.abstractclasses.AbstractPage;
import com.aros.buttons.SpecialsButton;
import com.aros.data.Ids;
import com.aros.data.SpecialsData;
import com.aros.main.MainActivity;

public class MainPage extends AbstractPage {

	public SpecialsButton special_btn_1;
	public SpecialsButton special_btn_2;
	public SpecialsButton special_btn_3;
	public SpecialsButton special_btn_4;
	
	// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;

	
	public MainPage (MainActivity a, int id, int pWidth, int pHeight, SpecialsData[] sData)
	{			
		super(a, id, pWidth, pHeight);
		
		this.menuButton_width 	= pWidth / 3;
		this.menuButton_height	= (int) (pHeight * 0.70 / 5);
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);
		
		int adSize = pHeight / 2;
		
		int btnX = (int)(((pWidth - (adSize * 2)) - menuButton_width) / 2);
		int btnY = (int)((pHeight * 0.1) );
		int btnYAdjust = (int) ((pHeight - (pHeight * 0.2)) / 5);
				
		SetButton(a, "Menu", Ids.BTN_MENU_ID, btnX, btnY, this.menuButton_width, this.menuButton_height);
		SetButton(a, "Refils", Ids.BTN_REFILS_ID, btnX, btnY + btnYAdjust, this.menuButton_width, this.menuButton_height);
		SetButton(a, "Games", Ids.BTN_GAMES_ID, btnX, btnY + btnYAdjust * 2, this.menuButton_width, this.menuButton_height);
		SetButton(a, "Call Waiter", Ids.BTN_CALL_ID, btnX, btnY + btnYAdjust * 3, this.menuButton_width, this.menuButton_height);
		SetButton(a, "Checkout", Ids.BTN_CHECKOUT_ID, btnX, btnY + btnYAdjust * 4, this.menuButton_width, this.menuButton_height);
		
		special_btn_1 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_1_ID, pWidth - adSize * 2, 0, sData[0]);
		special_btn_2 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_2_ID, pWidth - adSize, 0, sData[1]);
		special_btn_3 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_3_ID, pWidth - adSize * 2, adSize, sData[2]);
		special_btn_4 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_4_ID, pWidth - adSize, adSize, sData[3]);
		
		this.pLayout.addView(special_btn_1.Get());
		this.pLayout.addView(special_btn_2.Get());
		this.pLayout.addView(special_btn_3.Get());
		this.pLayout.addView(special_btn_4.Get());
	}
	
	/**
	 * 
	 * @param menuNames
	 */
	private Button SetButton(Activity a, String text, int id, int x, int y, int width, int height)
	{		
	    Button button;
		button = new Button(a);
	    button.setLayoutParams(new LayoutParams(width, height));
	    button.setId(id);
	    button.setText(text);
	    button.setTextSize((float) (height / 3.75));
	    button.setX(x);
	    button.setY(y);
	    button.setPadding(0, 0, 0, 0);
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
	    states.addState(new int[] { }, menuButton_normal);
	    button.setBackgroundDrawable(states);
	    
	    button.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	    
	    this.pLayout.addView(button);
	    
	    return button;
	}
	
	private void OnClick(View v)
	{
		a.OnClick(v);
	}
}
