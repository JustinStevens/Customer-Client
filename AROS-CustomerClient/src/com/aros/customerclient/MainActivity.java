package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.aros.layouts.MainLayout;
import com.aros.layouts.MainPage;

public class MainActivity extends Activity {
	
	MainLayout main;
	MainPage mainPage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Load();
		
		setContentView(main.Get());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	} 
	
	// For loading and preparing the application
	public void Load()
	{
		Functions.Setup(this);
		main = new MainLayout(this, Functions.display);
		mainPage = new MainPage(this, main);
	}

}
