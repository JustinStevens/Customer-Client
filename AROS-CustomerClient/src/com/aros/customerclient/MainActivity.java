package com.aros.customerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Load();
		
		//RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainlayout);
		setContentView(main.Get());
		
		
		
		//MainLayout.Create(this, Settings.display);
		
		//layout.addView(MainLayout.Create(this, Settings.display));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	MainLayout main;
	
	public void Load()
	{
		Settings.Setup(this);
		main = new MainLayout(this, Settings.display);
	}

}
