package com.aros.main;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aros.abstractclasses.AbstractPage;
import com.aros.data.Ids;
import com.aros.data.ItemData;
import com.aros.data.MenuData;
import com.aros.data.MenuList;
import com.aros.data.SpecialsData;
import com.aros.pages.ItemPage;
import com.aros.pages.MainPage;
import com.aros.pages.MenuListPage;
import com.aros.pages.MenuPage;
import com.aros.pages.SubMenuListPage;

public class MainActivity extends Activity {
	
	MainPage mainPage;
	MenuPage[][] menuPage;
	MenuListPage menuListPage;
	
	RelativeLayout container;
	AbstractPage currentPage;
	AbstractPage previousPage;
	
	MenuData mData[];
	
	int height;
	int width;
	
	private DatabaseHelper dbHelper;
	
	private String packageName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		container = new RelativeLayout(this);
		setContentView(container);
		
		container.addView(mainPage.Get());
		container.addView(menuListPage.Get());
		
		
		try {
			for(int i = 0; i < mData.length; i++)
			{
				if(mData[i].menuPages != null)
				{
					for(int j = 0; j < mData[i].menuPages.length; j++)
					{
						if(mData[i].menuPages[j] != null)
						{
							//for(int k = 0; k < mData[i].menuPages[j].length; k++)
								container.addView(mData[i].menuPages[j].Get());
						}
					}
				}
					
				
				container.addView(mData[i].subMenuPage.Get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			displayMessage(e.toString());
		}
			
		/***	
		/*for(int i = 0; i < menuPage.length; i++)
			for(int j = 0; j < menuPage.length; j++)
				container.addView(menuPage[i][].Get());
		
		for(int i = 0; i < sMenuListPage.length; i++)
			for(int j = 0; j < menuPage.length; j++)
				container.addView(sMenuListPage[i][].Get());*/
		
		
		currentPage = mainPage;
		currentPage.PlayAnimation(ItemPage.PLAY_APPEAR_ANIMATION);
		currentPage.setVisible(true);
	}
	
	// For loading and preparing the application
	public void Load()
	{
		a = this;
		Functions.Setup(this);
		this.width = Functions.display.x;
		this.height = Functions.display.y;
		
		packageName = getApplicationContext().getPackageName();
		
		dbHelper = new DatabaseHelper(this);	
		
		try { 
        	dbHelper.createDatabase(); 
        } catch (IOException ioe) {}
		
		try {
			dbHelper.openDataBase();
	 
	 	}catch(Exception e){//SQLException sqle){
	 
	 		//throw sqle;
	 
	 	}
				
		MenuList[] menu = dbHelper.getMenuData();
		
		mData = new MenuData[menu.length];
		
		for(int i = 0; i < menu.length; i++)
		{
			mData[i] = new MenuData();
			mData[i].subMenuList = dbHelper.getSubMenuData(menu[i].id);
			
			mData[i].itemData = new ItemData[mData[i].subMenuList.length][];
			
			
			for(int j = 0; j < mData[i].subMenuList.length; j++)
			{
				
				mData[i].itemData[j] = dbHelper.getItemData(this, packageName, mData[i].subMenuList[j].id);
			}
			
			mData[i].Create(this, width, height);
		}
		//SubMenuData[][] subMenu = dbHelper.getSubMenuData(menu[i].id);
		//ItemData[] items = dbHelper.getItemData(this, packageName);
		
		//this.sMenuListPage = new SubMenuListPage[menu.length];

		//ItemData[] iInfo = new ItemData[1];
		//iInfo[0] = test[0][0];//new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Leftover Fruit", 10.99f);
		/*iInfo[0][1] = new ItemInfo(getResources().getIdentifier("drawable/food2", null, packageName), "Pizza off the Floor", 12.99f);
		iInfo[0][2] = new ItemInfo(getResources().getIdentifier("drawable/food3", null, packageName), "Soggy Hamburger", 4.49f);
		iInfo[0][3] = new ItemInfo(getResources().getIdentifier("drawable/food4", null, packageName), "Undercooked Pancakes", 11.49f);
		iInfo[0][4] = new ItemInfo(getResources().getIdentifier("drawable/food5", null, packageName), "Overripe Fruit", 11.99f);
		iInfo[0][5] = new ItemInfo(getResources().getIdentifier("drawable/food6", null, packageName), "Stale Sushi", 12.49f);
		
		iInfo[1][0] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Leftover Fruit", 10.99f);
		iInfo[1][1] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Pizza off the Floor", 12.99f);
		iInfo[1][2] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Soggy Hamburger", 4.49f);
		iInfo[1][3] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Undercooked Pancakes", 11.49f);
		iInfo[1][4] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Overripe Fruit", 11.99f);
		iInfo[1][5] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Stale Sushi", 12.49f);
		*/
		/*ItemData[] iInfo2 = new ItemData[4];
		iInfo2[0] = new ItemData(getResources().getIdentifier("drawable/special1", null, packageName), "Cheescake", "1% Off until February 31st!");
		iInfo2[1] = new ItemData(getResources().getIdentifier("drawable/special2", null, packageName), "Birthday Cake", "2% Off until tomorrow!");
		iInfo2[2] = new ItemData(getResources().getIdentifier("drawable/special3", null, packageName), "Wine", "Now Available!");
		iInfo2[3] = new ItemData(getResources().getIdentifier("drawable/special4", null, packageName), "Icecream", "Now with strawberries! Get them before we run out!");
		*/
		
		SpecialsData[] sdata = new SpecialsData[4];
		sdata[0] = new SpecialsData(0, getResources().getIdentifier("drawable/special1", null, packageName), "Cheescake", "1% Off until February 31st!");
		sdata[1] = new SpecialsData(1, getResources().getIdentifier("drawable/special2", null, packageName), "Birthday Cake", "2% Off until tomorrow!");
		sdata[2] = new SpecialsData(2, getResources().getIdentifier("drawable/special3", null, packageName), "Wine", "Now Available!");
		sdata[3] = new SpecialsData(3, getResources().getIdentifier("drawable/special4", null, packageName), "Icecream", "Now with strawberries! Get them before we run out!");
		
		mainPage = new MainPage(this, 0, width, height, sdata);
		//menuPage = new MenuPage[][];
		//(this, 1, width, height, items);
		menuListPage = new MenuListPage(this, 2, width, height, menu);
	}
	
	int prev = -1;
		
	public void OnClick(View v)
	{
		switch(v.getId())
		{
		case Ids.BTN_MENU_ID:
			ChangePage(menuListPage);
			return;
		//case (Ids.BTN_MENU_LIST_START + 0):
		//	ChangePage(sMenuListPage[0]);
			
	    	//break;
		case Ids.BTN_MENU_HOME:
	    	ChangePage(mainPage);
	    	return;
		}
		
		if(v.getId() >= Ids.BTN_MENU_LIST_START && v.getId() <= Ids.BTN_MENU_LIST_MAX)
		{
			int subNum = v.getId() - Ids.BTN_MENU_LIST_START;
			ChangePage(mData[subNum].subMenuPage);
			cSubMenu = subNum;
		}
		else if(v.getId() >= Ids.BTN_SUBMENU_LIST_START && v.getId() <= Ids.BTN_SUBMENU_LIST_MAX)
		{
			int subNum = v.getId() - Ids.BTN_SUBMENU_LIST_START;
			ChangePage(mData[cSubMenu].menuPages[subNum]);
	    	((MenuPage)currentPage).ShowPage(0);
		}
		else
			displayMessage("This feature is not yet implemented.");
	}
	int cSubMenu = 0;
	
	private void ChangePage(AbstractPage page)
	{
		previousPage = currentPage;
		currentPage.setVisible(false);
		
		currentPage = page;

		currentPage.setVisible(true);
		previousPage.PlayAnimation(ItemPage.PLAY_DISAPPEAR_ANIMATION);
		previousPage.setVisible(false);
    	currentPage.PlayAnimation(ItemPage.PLAY_APPEAR_ANIMATION);
	}
	
	public static void displayMessage(String text)
	{
		Toast toast = Toast.makeText(a, text, Toast.LENGTH_LONG);
		toast.show();
	}
	
	public static Activity a;
}
