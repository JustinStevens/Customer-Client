package com.aros.customerclient;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aros.abstractclasses.AbstractPage;
import com.aros.layouts.MainLayout;
import com.aros.pages.ItemInfo;
import com.aros.pages.MainPage;
import com.aros.pages.MenuPage;

public class MainActivity extends Activity {
	
	MainLayout main;
	MainPage mainPage;
	MenuPage menuPage;
	RelativeLayout cLayout;
	AbstractPage currentPage;
	AbstractPage previousPage;
	
	private String packageName;
	
	Button btn_home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		
		setContentView(main.Get());
		
		cLayout.addView(mainPage.Get());
		cLayout.addView(menuPage.Get());
		
		
		currentPage = mainPage;
		currentPage.PlayAnimation(MenuPage.PLAY_APPEAR_ANIMATION);
		currentPage.setVisible(true);

		btn_home = (Button)findViewById(1000);
		
		btn_home.setVisibility(View.GONE);
		
		SetOnClick();
	}
	
	// For loading and preparing the application
	public void Load()
	{
		Functions.Setup(this);
		main = new MainLayout(this, Functions.display);
		packageName = getApplicationContext().getPackageName();
		
		ItemInfo[] iInfo = new ItemInfo[6];
		iInfo[0] = new ItemInfo(getResources().getIdentifier("drawable/food1", null, packageName), "Leftover Fruit", 10.99f);
		iInfo[1] = new ItemInfo(getResources().getIdentifier("drawable/food2", null, packageName), "Pizza off the Floor", 12.99f);
		iInfo[2] = new ItemInfo(getResources().getIdentifier("drawable/food3", null, packageName), "Soggy Hamburger", 4.49f);
		iInfo[3] = new ItemInfo(getResources().getIdentifier("drawable/food4", null, packageName), "Undercooked Pancakes", 11.49f);
		iInfo[4] = new ItemInfo(getResources().getIdentifier("drawable/food5", null, packageName), "Overripe Fruit", 11.99f);
		iInfo[5] = new ItemInfo(getResources().getIdentifier("drawable/food6", null, packageName), "Stale Sushi", 12.49f);
		
		ItemInfo[] iInfo2 = new ItemInfo[4];
		iInfo2[0] = new ItemInfo(getResources().getIdentifier("drawable/special1", null, packageName), "Cheescake", "1% Off until February 31st!");
		iInfo2[1] = new ItemInfo(getResources().getIdentifier("drawable/special2", null, packageName), "Birthday Cake", "2% Off until tomorrow!");
		iInfo2[2] = new ItemInfo(getResources().getIdentifier("drawable/special3", null, packageName), "Wine", "Now Available!");
		iInfo2[3] = new ItemInfo(getResources().getIdentifier("drawable/special4", null, packageName), "Icecream", "Now with strawberries! Get them before we run out!");
		
		mainPage = new MainPage(this, 0, main.getContent_width(), main.getContent_height(), iInfo2);
		menuPage = new MenuPage(this, 1, main.getContent_width(), main.getContent_height(), iInfo);
		cLayout = main.GetContent();
	}
	
	public void SetOnClick()
	{		
		Button btn = (Button)findViewById(1000);

		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
		
		btn = (Button)findViewById(1001);
		
		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	}
	
	int prev = -1;
		
	public void OnClick(View v)
	{
		if(!AbstractPage.IsInAnimationPlaying() && !AbstractPage.IsOutAnimationPlaying() && (v.getId() - 1000) != currentPage.GetId())
		{
			previousPage = currentPage;
			//currentPage.setVisible(false);
			
			if(v.getId() == 1000)
				btn_home.setVisibility(View.GONE);
			else
			{
				btn_home.setVisibility(View.VISIBLE);
			}
			
			if(prev != 1000)
				main.SetMenuButtonSelected(prev, false);
			if(v.getId() != 1000)
				main.SetMenuButtonSelected(v.getId(), true);
	    	prev = v.getId();
			
			switch(v.getId())
			{
			case 1000:
		    	currentPage = mainPage;
				break;
			case 1001:
		    	currentPage = menuPage;
				break;
			}
			
			currentPage.setVisible(true);
			previousPage.PlayAnimation(MenuPage.PLAY_DISAPPEAR_ANIMATION);
			previousPage.setVisible(false);
	    	currentPage.PlayAnimation(MenuPage.PLAY_APPEAR_ANIMATION);
		}
	}
}
