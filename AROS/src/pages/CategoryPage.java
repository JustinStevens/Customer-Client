package pages;

import java.util.Map;
import java.util.TreeMap;

import main.MainActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import buttons.BarButtons;
import data.CategoryData;
import data.Data;
import data.HashKey;
import data.SubCategoryData;
import framework.Page;

public class CategoryPage extends Page {

// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;
	
	private RelativeLayout cLayout;
	
	int width;
	int height;
	
	int btnR1X;
	int btnR2X;
	int btnYAdjust;
	int btnY;
	
	public CategoryPage(MainActivity a, int id, int width, int height) 
	{			
		super(a, id, width, height);
		
		this.width = width;
		this.height = height;

		this.menuButton_width 	= width / 3;
		this.menuButton_height	= (int) (height * 0.70 / 5);
		
		this.cLayout = new RelativeLayout(a);
		this.cLayout.setLayoutParams(new LayoutParams(Data.content_width, Data.content_height));
		
		btnR1X = (int)((width / 2 - menuButton_width * 1.1));
		btnR2X = (int)((width / 2 + menuButton_width * 0.1));
		btnYAdjust = (int) ((height - (height * 0.2)) / 5);
		btnY = (int)((height * 0.1) );
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);

		this.pLayout.addView(cLayout);
		this.pLayout.addView(new BarButtons(a).get());
		//this.pLayout.addView(btn_home);
		//this.pLayout.addView(btn_return);
	}
	
	public void setCategoryData(TreeMap<HashKey, CategoryData> cData)
	{
		int pos = 0;

		cLayout.removeAllViews();
		
		for(Map.Entry<HashKey, CategoryData> entry : cData.entrySet()) 
		{
			CategoryData value = entry.getValue();
		
			if(pos % 2 == 0)
		    	this.cLayout.addView(SetButton(a, value.name, Data.BTN_MENU_LIST_START + value.id, btnR1X, btnY + btnYAdjust * (pos / 2), this.menuButton_width, this.menuButton_height));
		    else
		    	this.cLayout.addView(SetButton(a, value.name, Data.BTN_MENU_LIST_START + value.id, btnR2X, btnY + btnYAdjust * (pos / 2) , this.menuButton_width, this.menuButton_height));
		    pos++;
		}
	}
	
	public void setCategoryData(TreeMap<HashKey, SubCategoryData> scData, int categoryId)
	{
		int pos = 0;

		cLayout.removeAllViews();
		
		for(Map.Entry<HashKey, SubCategoryData> entry : scData.entrySet()) 
		{
			if(((HashKey)entry.getKey()).parentId == categoryId)
			{
				SubCategoryData value = entry.getValue();
			
				if(pos % 2 == 0)
			    	this.cLayout.addView(SetButton(a, value.name, Data.BTN_SUBMENU_LIST_START + value.id, btnR1X, btnY + btnYAdjust * (pos / 2), this.menuButton_width, this.menuButton_height));
			    else
			    	this.cLayout.addView(SetButton(a, value.name, Data.BTN_SUBMENU_LIST_START + value.id, btnR2X, btnY + btnYAdjust * (pos / 2) , this.menuButton_width, this.menuButton_height));
			    pos++;
			}
		}
	}
	
	public void OnClick(View v)
	{
		a.OnClick(v);
	}
}
