package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pages.CategoryPage;
import pages.MainPage;
import pages.MenuPage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import data.DBHelper;
import data.Data;
import data.MenuData;
import data.SpecialsData;
import framework.Page;

public class MainActivity extends Activity {
	
	private static final int MAIN_PAGE = 1;
	private static final int CATEGORY_PAGE = 2;
	private static final int SUBCATEGORY_PAGE = 3;
	private static final int MENU_PAGE = 4;
	//private static final int ITEM_PAGE = 5;
	
	private Map<Integer, Page> pages;
	private int cPage_index = -1;
	private int cCategoryId = 0;
	private int cSubCategoryId = 0;
	MenuData mData;

	private static Activity a;
	private Point display;
	private RelativeLayout container;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		a = this;
		Load();
		setContentView(container);
		setPage(MAIN_PAGE);
	}
	
	@SuppressLint("UseSparseArrays")
	private void Load()
	{
		GetDisplaySize(this);
		Data.Set(getResources(), getApplicationContext().getPackageName(), display);
		
		DBHelper dbHelper = new DBHelper(this);
		try { dbHelper.createDatabase(); } catch (IOException ioe) {}
		try { dbHelper.openDataBase();   } catch (Exception e) {}
		
		mData = new MenuData(dbHelper);
		
		dbHelper.close();
		mData.LoadBitmaps();
		
		pages = new HashMap<Integer, Page>();
		
		container = new RelativeLayout(this);
		SpecialsData[] sdata = new SpecialsData[4];
		sdata[0] = new SpecialsData(0, getResources().getIdentifier("drawable/special1", null, Data.packageName), "Cheescake", "1% Off until February 31st!");
		sdata[1] = new SpecialsData(1, getResources().getIdentifier("drawable/special2", null, Data.packageName), "Birthday Cake", "2% Off until tomorrow!");
		sdata[2] = new SpecialsData(2, getResources().getIdentifier("drawable/special3", null, Data.packageName), "Wine", "Now Available!");
		sdata[3] = new SpecialsData(3, getResources().getIdentifier("drawable/special4", null, Data.packageName), "Icecream", "Now with strawberries! Get them before we run out!");
		
		pages.put(MAIN_PAGE, new MainPage(this, MAIN_PAGE, display.x, display.y, sdata));
		pages.put(CATEGORY_PAGE, new CategoryPage(this, CATEGORY_PAGE, display.x, display.y));
		pages.put(SUBCATEGORY_PAGE, new CategoryPage(this, SUBCATEGORY_PAGE, display.x, display.y));
		pages.put(MENU_PAGE, new MenuPage(this, MENU_PAGE, display.x, display.y));
		
		((CategoryPage)pages.get(CATEGORY_PAGE)).setCategoryData(mData.cData);
		
		container.addView(pages.get(MAIN_PAGE).get());
		container.addView(pages.get(CATEGORY_PAGE).get());
		container.addView(pages.get(SUBCATEGORY_PAGE).get());
		container.addView(pages.get(MENU_PAGE).get());
	}
	
	public static void displayMessage(String text)
	{
		Toast toast = Toast.makeText(a, text, Toast.LENGTH_LONG);
		toast.show();
	}
	
	public void GetDisplaySize(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getSize(display = new Point());
		
		if(display.y > display.x) {
			int temp = display.x; 
			display.x = display.y;
			display.y = temp;
		}
	}
	
	public void OnClick(View v)
	{
		switch(v.getId())
		{
		case Data.BTN_MENU_RETURN: OnClickReturn();  return;
		case Data.BTN_MENU_HOME: setPage(MAIN_PAGE); return;
		case Data.BTN_MENU_ID: setPage(CATEGORY_PAGE); return;
		default: 
			if(v.getId() >= Data.BTN_MENU_LIST_START && v.getId() <= Data.BTN_MENU_LIST_MAX)
			{
				cCategoryId = v.getId() - Data.BTN_MENU_LIST_START;
				((CategoryPage)pages.get(SUBCATEGORY_PAGE)).setCategoryData(mData.scData, cCategoryId);
				setPage(SUBCATEGORY_PAGE); 
			}
			else if(v.getId() >= Data.BTN_SUBMENU_LIST_START && v.getId() <= Data.BTN_SUBMENU_LIST_MAX)
			{
				cSubCategoryId = v.getId() - Data.BTN_SUBMENU_LIST_START;
				setPage(MENU_PAGE); 
				((MenuPage)pages.get(MENU_PAGE)).SetMenuData(mData.iData, cSubCategoryId);
			}
			
		return;
		}
		
	}
	
	private void OnClickReturn()
	{
		switch(cPage_index)
		{
		case MAIN_PAGE:										
			break;
		case CATEGORY_PAGE: 
			setPage(MAIN_PAGE); 		
			break;
		case SUBCATEGORY_PAGE:
			setPage(CATEGORY_PAGE);		
			break;
		case MENU_PAGE:	
			setPage(SUBCATEGORY_PAGE);	
			((CategoryPage)pages.get(SUBCATEGORY_PAGE)).setCategoryData(mData.scData, cCategoryId);
		break;
		}
	}

	private void setPage(int index)
	{
		if(pages.containsKey(cPage_index))
			pages.get(cPage_index).setVisible(false, true);
		
		if(pages.containsKey(index))
			pages.get(index).setVisible(true, true);

		cPage_index = index;
	}
}