package data;

import android.content.res.Resources;
import android.graphics.Point;

public class Data {
	public static Resources res;
	public static String packageName;
	
	public static int content_width;
	public static int content_height;
	
	public static int bar_width;
	public static int bar_height;
	public static int bar_btn_width;
	
	public static int item_btn_width;
	public static int item_btn_height;
	public final static int MENU_MAIN_ID = 10;
	
	public final static int BTN_MENU_ID = 1000;
	public final static int BTN_REFILS_ID = 1001;
	public final static int BTN_GAMES_ID = 1002;
	public final static int BTN_CALL_ID = 1003;
	public final static int BTN_CHECKOUT_ID = 1004;
	
	public final static int BTN_SPECIAL_1_ID = 2000;
	public final static int BTN_SPECIAL_2_ID = 2001;
	public final static int BTN_SPECIAL_3_ID = 2002;
	public final static int BTN_SPECIAL_4_ID = 2003;
	
	public final static int BTN_MENU_PREV = 3000;
	public final static int BTN_MENU_NEXT = 3001;
	public final static int BTN_MENU_RETURN = 3002;
	public final static int BTN_MENU_HOME = 3003;
	
	public final static int BTN_MENU_LIST_START = 4000;
	public final static int BTN_MENU_LIST_MAX = 4999;
	
	public final static int BTN_SUBMENU_LIST_START = 5000;
	public final static int BTN_SUBMENU_LIST_MAX = 5999;
	
	public final static int MENU_PAGE_START = 6000;
	
	public final static int BTN_ITEM_START = 7000;
	public final static int BTN_ITEM_MAX = 7999;
	
	public final static int PAGE_MAIN = 8000;
	public final static int PAGE_MENU_LIST = 8001;
	public final static int PAGE_SUB_MENU_LIST = 8002;
	public final static int PAGE_ITEM_LIST_ONE = 8003;
	public final static int PAGE_ITEM_LIST_TWO = 8004;
	public final static int PAGE_ITEM_LIST_THREE = 8005;
	
	public final static int PAGE_ITEM_LARGE_IMAGE = 8006;
	public final static int BTN_CUST_NUTRI_SWAP = 8007;
	public static void Set(Resources resource, String package_name, Point display)
	{
		res = resource;
		packageName = package_name;
		calculateSizes(display.x, display.y);
	}
	
	public static void calculateSizes(int width, int height)
	{
		content_width = (int) (width);
		content_height = (int) (height * 0.88);
		
		bar_width = content_width;
		bar_height = (int) (height * 0.12);
		bar_btn_width = (int) (bar_height * 2.5);
		
		item_btn_width = (content_width) / 2 + 1;
		item_btn_height = (int) (content_height / 3) + 1;
	}
	
}
