package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aros.layouts.MainLayout;
import com.aros.layouts.MainPage;

public class MainActivity extends Activity {
	
	MainLayout main;
	MainPage mainPage;
	RelativeLayout cLayout;
	
	int pageId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		
		setContentView(main.Get());
		SetOnClick();
	}
	
	// For loading and preparing the application
	public void Load()
	{
		Functions.Setup(this);
		main = new MainLayout(this, Functions.display);
		mainPage = new MainPage(this, main);
		cLayout = main.GetContent();
	}
	
	public void SetOnClick()
	{
		Button btn = (Button)findViewById(100);

		btn.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	//if(cLayout.getChildAt(0) != null)
		    	cLayout.removeAllViews();
		    	cLayout.addView(mainPage.Get());
		    	pageId = MainPage.ID;
		    	main.showHideBtn(false);
		    }
		});
	}

}
