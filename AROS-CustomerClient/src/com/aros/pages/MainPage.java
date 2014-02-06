package com.aros.pages;

import android.app.Activity;

import com.aros.abstractclasses.AbstractPage;
import com.aros.buttons.SpecialsButton;

public class MainPage extends AbstractPage {

	public SpecialsButton special_btn_1;
	public SpecialsButton special_btn_2;
	public SpecialsButton special_btn_3;
	public SpecialsButton special_btn_4;
	
	public MainPage (Activity a, int id, int pWidth, int pHeight, ItemInfo[] iInfo)
	{			
		super(a, id, pWidth, pHeight);
		
		int adSize = pHeight / 2;
		
		special_btn_1 = new SpecialsButton(a, adSize, adSize, 1, pWidth - adSize * 2, 0, iInfo[0]);
		special_btn_2 = new SpecialsButton(a, adSize, adSize, 2, pWidth - adSize, 0, iInfo[1]);
		special_btn_3 = new SpecialsButton(a, adSize, adSize, 3, pWidth - adSize * 2, adSize, iInfo[2]);
		special_btn_4 = new SpecialsButton(a, adSize, adSize, 4, pWidth - adSize, adSize, iInfo[3]);
		
		this.pLayout.addView(special_btn_1.Get());
		this.pLayout.addView(special_btn_2.Get());
		this.pLayout.addView(special_btn_3.Get());
		this.pLayout.addView(special_btn_4.Get());
	}
}
