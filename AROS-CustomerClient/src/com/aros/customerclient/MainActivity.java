package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aros.abstractclasses.AbstractPage;
import com.aros.pages.ItemPage;
import com.aros.pages.MainPage;
import com.aros.pages.MenuPage;

public class MainActivity extends Activity {
	
	MainPage mainPage;
	MenuPage menuPage;
	RelativeLayout container;
	AbstractPage currentPage;
	AbstractPage previousPage;
	
	int height;
	int width;
	
	private String packageName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		container = new RelativeLayout(this);
		setContentView(container);
		
		container.addView(mainPage.Get());
		container.addView(menuPage.Get());
		
		
		currentPage = mainPage;
		currentPage.PlayAnimation(ItemPage.PLAY_APPEAR_ANIMATION);
		currentPage.setVisible(true);

		SetOnClick();
	}
	
	// For loading and preparing the application
	public void Load()
	{
		Functions.Setup(this);
		this.width = Functions.display.x;
		this.height = Functions.display.y;
		
		packageName = getApplicationContext().getPackageName();
		
		ItemInfo[][] iInfo = new ItemInfo[2][6];
		iInfo[0][0] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Leftover Fruit", 10.99f);
		iInfo[0][1] = new ItemInfo(getResources().getIdentifier("drawable/food2", null, packageName), "Pizza off the Floor", 12.99f);
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
		
		ItemInfo[] iInfo2 = new ItemInfo[4];
		iInfo2[0] = new ItemInfo(getResources().getIdentifier("drawable/special1", null, packageName), "Cheescake", "1% Off until February 31st!");
		iInfo2[1] = new ItemInfo(getResources().getIdentifier("drawable/special2", null, packageName), "Birthday Cake", "2% Off until tomorrow!");
		iInfo2[2] = new ItemInfo(getResources().getIdentifier("drawable/special3", null, packageName), "Wine", "Now Available!");
		iInfo2[3] = new ItemInfo(getResources().getIdentifier("drawable/special4", null, packageName), "Icecream", "Now with strawberries! Get them before we run out!");
		
		mainPage = new MainPage(this, 0, width, height, iInfo2);
		menuPage = new MenuPage(this, 1, width, height, iInfo);
	}
	
	public void SetOnClick()
	{		
		Button btn = (Button)findViewById(Ids.BTN_MENU_ID);

		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	}
	
	int prev = -1;
		
	public void OnClick(View v)
	{
		previousPage = currentPage;
		currentPage.setVisible(false);
		
		switch(v.getId())
		{
		case Ids.BTN_MENU_ID:
	    	currentPage = menuPage;
	    	((MenuPage)currentPage).ShowPage(0);
			break;
		}
		
		currentPage.setVisible(true);
		previousPage.PlayAnimation(ItemPage.PLAY_DISAPPEAR_ANIMATION);
		previousPage.setVisible(false);
    	currentPage.PlayAnimation(ItemPage.PLAY_APPEAR_ANIMATION);
	}
}
