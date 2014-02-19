package pages;

import main.MainActivity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import buttons.BarButtons;
import buttons.ItemButton;
import data.Data;
import data.ItemData;
import framework.Page;

public class ItemPage extends Page {

	private BarButtons barBtns;
	private RelativeLayout content;
	//private ImageView img;
	private TextView desctiption;
	private TextView customizations;
	private ImageView nutrition;
	
	private Button btn_cnSwap;
	
	ItemButton image;
	
	int img_width;
	int img_height;
	
	boolean showToggle = false;
	
	public ItemPage(MainActivity a, int id, int width, int height) {
		super(a, id, width, height);
		
		img_width = Data.item_btn_width;
		img_height = (int) (Data.item_btn_height * 2);

		
		this.barBtns = new BarButtons(a);
		
		this.content = new RelativeLayout(a);
		this.content.setLayoutParams(new LayoutParams(Data.content_width, Data.content_height));
		
		image = new ItemButton(a, img_width, img_height, Data.PAGE_ITEM_LARGE_IMAGE, false);
		
		/*img = new ImageView(a);
		img.setLayoutParams(params);
		img.setBackgroundColor(Color.BLUE);*/
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(img_width, Data.bar_height);
		
		btn_cnSwap = new Button(a);
		btn_cnSwap.setLayoutParams(params);
		btn_cnSwap.setX(img_width);
		btn_cnSwap.setY(Data.content_height - Data.bar_height);
		btn_cnSwap.setText("Show Nutrition");
		btn_cnSwap.setId(Data.BTN_CUST_NUTRI_SWAP);
		btn_cnSwap.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
		
		params = new RelativeLayout.LayoutParams(img_width, Data.content_height - img_height);

		desctiption = new TextView(a);
		desctiption.setLayoutParams(params);
		desctiption.setText("SHORT DESCRIPTION GOES HERE");
		desctiption.setTextColor(Color.WHITE);
		desctiption.setTextSize(Data.content_height / 30);
		desctiption.setY(img_height);
		
		params = new RelativeLayout.LayoutParams(img_width, Data.content_height - Data.bar_height);
		
		customizations = new TextView(a);
		customizations.setLayoutParams(params);
		customizations.setText("Customizations Go Here");
		customizations.setTextColor(Color.WHITE);
		customizations.setTextSize(Data.content_height / 30);
		customizations.setX(img_width);
		
		nutrition = new ImageView(a);
		nutrition.setLayoutParams(params);
		nutrition.setX(img_width);
		
		//this.content.addView(img);
		this.content.addView(image.Get());
		this.content.addView(desctiption);
		this.content.addView(customizations);
		this.content.addView(nutrition);
		this.content.addView(btn_cnSwap);
		this.pLayout.addView(barBtns.get());
		this.pLayout.addView(content);
		
		ToggleCustNuti();
	}
	
	public void SetItemData(ItemData iData)
	{
		image.setData(iData.name, null, iData.largeImgResId, (int)(img_height * 0.6));
		desctiption.setText(iData.shortDesc);
		nutrition.setImageResource(iData.nutritionImgResId);
		customizations.setText("Not Yet Implemented");
		//this.img.setImageResource(item.largeImgResId);
	}

	@Override
	public void OnClick(View v) {
		switch(v.getId())
		{
		case Data.BTN_CUST_NUTRI_SWAP:
			ToggleCustNuti();
			break;
		}
	}
	
	public void ToggleCustNuti()
	{
		if(showToggle)
		{
			customizations.setVisibility(View.VISIBLE);
			nutrition.setVisibility(View.INVISIBLE);
			btn_cnSwap.setText("Show Nutrition");
		}
		else
		{
			customizations.setVisibility(View.INVISIBLE);
			nutrition.setVisibility(View.VISIBLE);
			btn_cnSwap.setText("Show Customizations");
		}
		showToggle = !showToggle;
	}

}
