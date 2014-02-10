package com.aros.pages;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.aros.abstractclasses.AbstractPage;
import com.aros.buttons.SpecialsButton;
import com.aros.customerclient.Functions;
import com.aros.customerclient.Ids;
import com.aros.customerclient.ItemInfo;

public class MainPage extends AbstractPage {

	public SpecialsButton special_btn_1;
	public SpecialsButton special_btn_2;
	public SpecialsButton special_btn_3;
	public SpecialsButton special_btn_4;
	
	// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;
	
	// Temporary menu names until database is implemented
	private String[] menuNames = new String[]{"Breakfast", "Lunch", "Dinner", "Appetizers", "Desert", "Beverages", "Bar"};
	
	public MainPage (Activity a, int id, int pWidth, int pHeight, ItemInfo[] iInfo)
	{			
		super(a, id, pWidth, pHeight);
		
		this.menuButton_width 	= pWidth / 3;
		this.menuButton_height	= pHeight / this.menuNames.length;
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);
		
		int adSize = pHeight / 2;
		
		int btnX = (int)(((pWidth - (adSize * 2)) - menuButton_width) / 2);
		int btnY = (int)((pHeight * 0.1) );
		int btnYAdjust = (int) ((pHeight - (pHeight * 0.2)) / 5);
				
		SetButton(a, "Menu", Ids.BTN_MENU_ID, btnX, btnY);
		SetButton(a, "Refils", Ids.BTN_REFILS_ID, btnX, btnY + btnYAdjust);
		SetButton(a, "Games", Ids.BTN_GAMES_ID, btnX, btnY + btnYAdjust * 2);
		SetButton(a, "Call Waiter", Ids.BTN_CALL_ID, btnX, btnY + btnYAdjust * 3);
		SetButton(a, "Checkout", Ids.BTN_CHECKOUT_ID, btnX, btnY + btnYAdjust * 4);
		
		special_btn_1 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_1_ID, pWidth - adSize * 2, 0, iInfo[0]);
		special_btn_2 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_2_ID, pWidth - adSize, 0, iInfo[1]);
		special_btn_3 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_3_ID, pWidth - adSize * 2, adSize, iInfo[2]);
		special_btn_4 = new SpecialsButton(a, adSize, adSize, Ids.BTN_SPECIAL_4_ID, pWidth - adSize, adSize, iInfo[3]);
		
		this.pLayout.addView(special_btn_1.Get());
		this.pLayout.addView(special_btn_2.Get());
		this.pLayout.addView(special_btn_3.Get());
		this.pLayout.addView(special_btn_4.Get());
	}
	
	/**
	 * 
	 * @param menuNames
	 */
	private void SetButton(Activity a, String text, int id, int x, int y)
	{		
	    Button button;
		button = new Button(a);
	    button.setText(text);
	    button.setLayoutParams(new LayoutParams(this.menuButton_width, this.menuButton_height));
	    button.setId(id);
	    button.setTextSize((float) (this.menuButton_height / 3.75));
	    button.setPadding(8, 0, 0, 20);
	    button.setX(x);
	    button.setY(y);
	    
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
	    states.addState(new int[] { }, menuButton_normal);
	    
	    button.setBackgroundDrawable(states);
	    this.pLayout.addView(button);
	}
	
	public void SetMenuButtonSelected(Activity a, int id, boolean selected)
	{
		Button btn = (Button)a.findViewById(id);
		if(btn != null)
		{
			if(selected)
				btn.setBackgroundDrawable(menuButton_pressed);
			else
			{
			    StateListDrawable states = new StateListDrawable();
			    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
			    states.addState(new int[] { }, menuButton_normal);
			    
			    btn.setBackgroundDrawable(states);
			}
				
		}
	}
}
