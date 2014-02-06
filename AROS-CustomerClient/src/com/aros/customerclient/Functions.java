package com.aros.customerclient;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.view.WindowManager;

public class Functions {
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
	
	public static GradientDrawable SetGradient(int[] colors, float[] conerRadii, int stroke, int strokeWidth)
	{
		GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors);
		drawable.setGradientType(GradientDrawable.RECTANGLE);
		drawable.setCornerRadii(conerRadii);
		drawable.setStroke(strokeWidth, stroke);
		return drawable;
	}
	
	public static int GetResId(String variableName, Class<?> c) {

	    try {
	        Field idField = c.getDeclaredField(variableName);
	        return idField.getInt(idField);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    } 
	}
}
