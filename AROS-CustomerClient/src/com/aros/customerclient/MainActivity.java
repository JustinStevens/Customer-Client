package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.aros.pages.MainPage;

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
		Settings.Setup(this);
		main = new MainLayout(this, Settings.display);
		mainPage = new MainPage(this, main);
	}

}
