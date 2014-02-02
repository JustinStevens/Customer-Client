package com.aros.customerclient;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class Settings {
	public static Point display;
	
	public static void Setup(Context context)
	{
		GetDisplaySize(context);
	}
	
	public static void GetDisplaySize(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getSize(display = new Point());
		
		if(display.y > display.x) {
			int temp = display.x;
			display.x = display.y;
			display.y = temp;
		}
	}
}
