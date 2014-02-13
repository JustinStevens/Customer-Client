package com.aros.pages;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.aros.abstractclasses.AbstractPage;
import com.aros.data.Ids;
import com.aros.data.MenuData;
import com.aros.data.SubMenuData;
import com.aros.main.MainActivity;

public class SubMenuListPage extends AbstractPage {
	
	// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;
	private SubMenuData[] menuData;
	
	int content_width;
	int content_height;
	int bar_width;
	int bar_height;
	
	private Button btn_home;

	
	public SubMenuListPage (MainActivity a, int id, int pWidth, int pHeight, SubMenuData[] menuData, int menuId)
	{			
		super(a, id, pWidth, pHeight);

		this.menuData = menuData;
		
		this.menuButton_width 	= pWidth / 3;
		this.menuButton_height	= (int) (pHeight * 0.70 / 5);
		
		this.content_width = (int) (pWidth);
		this.content_height = (int) (pHeight * 0.88);
		this.bar_width = content_width;
		this.bar_height = (int) (pHeight * 0.12);
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);
		
		int btnR1X = (int)((pWidth / 2 - menuButton_width * 1.1));
		int btnR2X = (int)((pWidth / 2 + menuButton_width * 0.1));
		int btnYAdjust = (int) ((pHeight - (pHeight * 0.2)) / 5);
		int btnY = (int)((pHeight * 0.1) );
	
		for(int i = 0; i < menuData.length; i+=2)
		{
			if(menuId == menuData[i].parentCateId)
				SetButton(a, menuData[i].name, Ids.BTN_SUBMENU_LIST_START + i, btnR1X, btnY + btnYAdjust * (i / 2), this.menuButton_width, this.menuButton_height);
		}
		
		for(int i = 1; i < menuData.length; i+=2)
		{
			if(menuId == menuData[i].parentCateId)
				SetButton(a, menuData[i].name, Ids.BTN_SUBMENU_LIST_START + i, btnR2X, btnY + btnYAdjust * (i / 2) , this.menuButton_width, this.menuButton_height);
		}
		
		btn_home = SetButton(a, "Home", Ids.BTN_MENU_HOME, (this.content_width / 2 - (int)(this.bar_height * 2.5 / 2)), this.content_height, (int)(this.bar_height * 2.5), this.bar_height);
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
