package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aros.layouts.MainLayout;
import com.aros.pages.MainPage;
import com.aros.pages.MenuPage;

public class MainActivity extends Activity {
	
	MainLayout main;
	MainPage mainPage;
	MenuPage menuPage;
	RelativeLayout cLayout;
	RelativeLayout currentContent;
	int pageId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		
		setContentView(main.Get());
		
		currentContent = mainPage.Get(true);
		cLayout.addView(currentContent);
		
		SetOnClick();
	}
	
	// For loading and preparing the application
	public void Load()
	{
		Functions.Setup(this);
		main = new MainLayout(this, Functions.display);
		mainPage = new MainPage(this, main);
		menuPage = new MenuPage(this, main);
		cLayout = main.GetContent();
	}
	
	public void SetOnClick()
	{		
		Button btn = (Button)findViewById(100);

		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	
		    	cLayout.removeAllViews();
		    	currentContent = menuPage.Get(true);
		    	cLayout.addView(currentContent);
		    	pageId = MainPage.ID;
		    	main.showHideBtn(false);
		    }
		});
	}

}
