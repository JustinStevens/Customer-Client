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
import com.aros.data.SpecialsData;
import com.aros.data.SubMenuData;
import com.aros.pages.ItemPage;
import com.aros.pages.MainPage;
import com.aros.pages.MenuListPage;
import com.aros.pages.MenuPage;
import com.aros.pages.SubMenuListPage;

public class MainActivity extends Activity {
	
	MainPage mainPage;
	MenuPage menuPage;
	MenuListPage menuListPage;
	
	RelativeLayout container;
	AbstractPage currentPage;
	AbstractPage previousPage;
	
	private SubMenuListPage[] sMenuListPage;
	
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
		container.addView(menuPage.Get());
		container.addView(menuListPage.Get());
		
		for(int i = 0; i < sMenuListPage.length; i++)
			container.addView(sMenuListPage[i].Get());
		
		
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
		
		MenuData[] menu = dbHelper.getMenuData();
		SubMenuData[] subMenu = dbHelper.getSubMenuData();
		ItemData[] items = dbHelper.getItemData(this, packageName);
		
		this.sMenuListPage = new SubMenuListPage[menu.length];

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
		menuPage = new MenuPage(this, 1, width, height, items);
		menuListPage = new MenuListPage(this, 2, width, height, menu);
		
		for(int i = 0; i < menu.length; i++)
			sMenuListPage[i] = new SubMenuListPage(this, 2, width, height, subMenu, menu[i].id);
	}
	
	int prev = -1;
		
	public void OnClick(View v)
	{
		switch(v.getId())
		{
		case Ids.BTN_MENU_ID:
			ChangePage(menuListPage);
			break;
		case (Ids.BTN_MENU_LIST_START + 0):
			ChangePage(sMenuListPage[0]);
			
	    	break;
		case (Ids.BTN_SUBMENU_LIST_START + 0):
			ChangePage(menuPage);
	    	((MenuPage)currentPage).ShowPage(0);
	    	break;	
		case Ids.BTN_MENU_HOME:
	    	ChangePage(mainPage);
			break;
		default: displayMessage("This feature is not yet implemented.");
		}
	}
	
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
