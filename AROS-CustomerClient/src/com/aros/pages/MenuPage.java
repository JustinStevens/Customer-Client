package com.aros.pages;

import android.app.Activity;

import com.aros.abstractclasses.AbstractPage;

public class MenuPage extends AbstractPage {
	
	public ItemPage[] itemPages;

	public MenuPage (Activity a, int id, int width, int height, ItemInfo[][] iInfo)
	{
		super(a, id, width, height);
		
		itemPages = new ItemPage[iInfo.length];
		
		itemPages[0] = new ItemPage(a, 0, width, height, iInfo[0]);
		itemPages[1] = new ItemPage(a, 1, width, height, iInfo[1]);
		
		pLayout.addView(itemPages[0].Get());
		pLayout.addView(itemPages[1].Get());
		
		itemPages[0].setVisible(false);
		itemPages[1].setVisible(false);
	}
	
	public void ShowPage(int id)
	{
		for(int i = 0; i < itemPages.length; i++)
		{
			if(i == id)
				itemPages[i].setVisible(true);
			else
				itemPages[i].setVisible(false);
		}
		
	}
}
