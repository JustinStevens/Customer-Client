package com.aros.data;

import com.aros.main.MainActivity;
import com.aros.pages.MenuListPage;
import com.aros.pages.MenuPage;
import com.aros.pages.SubMenuListPage;

public class MenuData {
	public int menuId;
	
	public SubMenuList[] subMenuList;
	public ItemData[][] itemData;
	
	public MenuPage[] menuPages;
	public SubMenuListPage subMenuPage;
	
	public void Create(MainActivity a, int width, int height) {
		
		subMenuPage = new SubMenuListPage(a, 2, width, height, subMenuList);
		menuPages = new MenuPage[subMenuList.length];
		
		for(int i = 0; i < itemData.length; i++)
			menuPages[i] = new MenuPage(a, Ids.MENU_PAGE_START + i, width, height, itemData[i]);
	}
}
