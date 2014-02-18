package pages;

import main.MainActivity;
import android.view.View;
import buttons.SpecialsButton;
import data.Data;
import data.SpecialsData;
import framework.Page;

public class MainPage extends Page {

	public SpecialsButton special_btn_1;
	public SpecialsButton special_btn_2;
	public SpecialsButton special_btn_3;
	public SpecialsButton special_btn_4;
	
	// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;

	public MainPage(MainActivity a, int id, int pWidth, int pHeight, SpecialsData[] sData)
	{			
		super(a, id, pWidth, pHeight);
		
		this.menuButton_width 	= pWidth / 3;
		this.menuButton_height	= (int) (pHeight * 0.70 / 5);
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);
		
		int adSize = pHeight / 2;
		
		int btnX = (int)(((pWidth - (adSize * 2)) - menuButton_width) / 2);
		int btnY = (int)((pHeight * 0.1) );
		int btnYAdjust = (int) ((pHeight - (pHeight * 0.2)) / 5);
				
		this.pLayout.addView(SetButton(a, "Menu", Data.BTN_MENU_ID, btnX, btnY, this.menuButton_width, this.menuButton_height));
		this.pLayout.addView(SetButton(a, "Refils", Data.BTN_REFILS_ID, btnX, btnY + btnYAdjust, this.menuButton_width, this.menuButton_height));
		this.pLayout.addView(SetButton(a, "Games", Data.BTN_GAMES_ID, btnX, btnY + btnYAdjust * 2, this.menuButton_width, this.menuButton_height));
		this.pLayout.addView(SetButton(a, "Call Waiter", Data.BTN_CALL_ID, btnX, btnY + btnYAdjust * 3, this.menuButton_width, this.menuButton_height));
		this.pLayout.addView(SetButton(a, "Checkout", Data.BTN_CHECKOUT_ID, btnX, btnY + btnYAdjust * 4, this.menuButton_width, this.menuButton_height));
		
		special_btn_1 = new SpecialsButton(a, adSize, adSize, Data.BTN_SPECIAL_1_ID, pWidth - adSize * 2, 0);
		special_btn_2 = new SpecialsButton(a, adSize, adSize, Data.BTN_SPECIAL_2_ID, pWidth - adSize, 0);
		special_btn_3 = new SpecialsButton(a, adSize, adSize, Data.BTN_SPECIAL_3_ID, pWidth - adSize * 2, adSize);
		special_btn_4 = new SpecialsButton(a, adSize, adSize, Data.BTN_SPECIAL_4_ID, pWidth - adSize, adSize);
		
		special_btn_1.setData(sData[0]);
		special_btn_2.setData(sData[1]);
		special_btn_3.setData(sData[2]);
		special_btn_4.setData(sData[3]);
		
		this.pLayout.addView(special_btn_1.Get());
		this.pLayout.addView(special_btn_2.Get());
		this.pLayout.addView(special_btn_3.Get());
		this.pLayout.addView(special_btn_4.Get());
	}
	
	
	public void OnClick(View v)
	{
		a.OnClick(v);
	}
}
