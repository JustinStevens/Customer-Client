package pages;

import main.MainActivity;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import buttons.BarButtons;
import buttons.ItemButton;
import data.Data;
import data.ItemData;
import framework.Page;

public class ItemPage extends Page {

	private BarButtons barBtns;
	private RelativeLayout content;
	private RelativeLayout orderLayout;
	
	private ScrollView custScroll;
	private LinearLayout custLayout;
	
	//private ImageView img;
	private TextView description;
	private TextView customizations;
	private ImageView nutrition;
	private ScrollView scroll;
	private Button btn_nuti;
	private Button btn_desc;
	private Button btn_order;
	private Button btn_addToCard;
	ItemButton image;
	
	private Animation descAnimShow;
	private Animation descAnimHide;
	private Animation nutiAnimShow;
	private Animation nutiAnimHide;
	
	private Animation btnAnimRight;
	private Animation btnAnimLeft;
	
	int img_width;
	int img_height;
	
	boolean showToggle = false;
	
	int menuButton_width;
	int menuButton_height;
	
	public ItemPage(MainActivity a, int id, int width, int height) {
		super(a, id, width, height);
		
		img_width = width;
		img_height = height - Data.bar_height;

		
		this.barBtns = new BarButtons(a);

		this.content = new RelativeLayout(a);
		this.content.setLayoutParams(new LayoutParams(Data.content_width, Data.content_height));
				
		image = new ItemButton(a, img_width, img_height, Data.PAGE_ITEM_LARGE_IMAGE, false, false);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(width - width * 0.2), (int)(height * 0.4));

		description = new TextView(a);
		description.setLayoutParams(params);
		description.setText("SHORT DESCRIPTION GOES HERE");
		description.setTextColor(Color.WHITE);
		description.setTextSize(Data.content_height / 30);
		description.setY(height - (int)(height * 0.4));
		description.setX((int)(-width * 0.9) - 1);
		description.setBackgroundColor(Color.argb(180, 40, 40, 40));
		
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
		
		nutrition = new ImageView(a);
		nutrition.setLayoutParams(params);
		nutrition.setAdjustViewBounds(true);
		
		descAnimShow = SetAnimation(0, (int)(width), 400, true);
		descAnimHide = SetAnimation(0, (int)-(width), 400, true);
		
		nutiAnimShow = SetAnimation(0, -(width / 2), 400, true);
		nutiAnimHide = SetAnimation(0, (width / 2) + 1, 400, true);
		
		
		params = new RelativeLayout.LayoutParams(width / 2, height);
		
		this.scroll = new ScrollView(a);
		this.scroll.setLayoutParams(params);
		this.scroll.setX(width + 1);

		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		LinearLayout sLayout = new LinearLayout(a);
		sLayout.setLayoutParams(params);
		sLayout.setPadding(2, 2, 2, 2);
		
		menuButton_width = width / 4;
		menuButton_height = menuButton_width / 4;
		
		btn_order = SetButton(a, "Order", Data.BTN_ITEM_ORDER, 0, height - menuButton_width / 3, menuButton_width, menuButton_width / 3);
		
		btnAnimRight = SetAnimation(0, menuButton_width / 4, 250, false);
		btnAnimLeft = SetAnimation(0, -(menuButton_width / 4), 250, false);
		
		btn_desc = SetButton(a, "Description", Data.BTN_ITEM_DESCRIPTION, 0, (int)(height * 0.175), menuButton_width, menuButton_height);
		btn_nuti = SetButton(a, "Nutrition", Data.BTN_ITEM_NUTRITION, 0, (int)(height * 0.175 + menuButton_height * 1.1), menuButton_width, menuButton_height);
		
		btn_nuti.setAlpha(0.8f);
		btn_desc.setAlpha(0.8f);
		
		//this.content.addView(img);
		this.content.addView(image.Get());
		this.content.addView(description);
		this.content.addView(scroll);
		//this.scroll.addView(sLayout);
		//sLayout.addView(customizations);
		this.scroll.addView(nutrition);
		this.pLayout.addView(barBtns.get());
		
		this.content.addView(btn_nuti);
		this.content.addView(btn_desc);
		this.pLayout.addView(content);
		this.pLayout.addView(btn_order);
		
		
		//Order Page
		this.orderLayout = new RelativeLayout(a);
		this.orderLayout.setLayoutParams(new LayoutParams(Data.content_width, Data.content_height));
		this.orderLayout.setBackgroundColor(Color.argb(180, 40, 40, 40));
		
		params = new RelativeLayout.LayoutParams(width / 2, height);
		
		custScroll = new ScrollView(a);
		custScroll.setLayoutParams(params);
		custScroll.setX(width / 2);
		custScroll.setBackgroundColor(Color.argb(180, 40, 40, 40));
		
		custLayout = new LinearLayout(a);
		custLayout.setBackgroundColor(Color.WHITE);
		
		btn_addToCard = SetButton(a, "Add to Cart", Data.BTN_ITEM_ADDTOCART, 0, height - menuButton_width / 3, menuButton_width, menuButton_width / 3);
		
		this.custScroll.addView(custLayout);
		this.orderLayout.addView(custScroll);
		this.pLayout.addView(btn_addToCard);
		this.pLayout.addView(orderLayout);
	}
	
	public void SetItemData(ItemData iData)
	{
		image.setFontScale(0.8f);
		image.setData(iData.name, "$" + iData.price / 100, iData.largeImgResId, (int)(img_height * 0.6));
		description.setText(iData.shortDesc);
		nutrition.setImageResource(iData.nutritionImgResId);
		//customizations.setText("Not Yet Implemented");
		//this.img.setImageResource(item.largeImgResId);
		showDesc = false;
		showNutri = false;
		ShowOrder(false);
		animEnd();
	}
	
	boolean showDesc = false;
	boolean showNutri = false;
	
	@Override
	public void OnClick(View v) {
		
		boolean playDescAnim = false;
		boolean playNutiAnim = false;
		
		if(!animPlaying)
		{
			switch(v.getId())
			{
			case Data.BTN_ITEM_DESCRIPTION:
				playDescAnim = true;
				break;
			case Data.BTN_ITEM_NUTRITION:
				playNutiAnim = true;
				break;
			case Data.BTN_ITEM_ORDER:
				if(showDesc == true)
					playDescAnim = true;
				if(showNutri == true)
					playNutiAnim = true;
				ShowOrder(true);
				break;
			}
			
			playAnim(playDescAnim, playNutiAnim);
		}
	}
	
	private void ShowOrder(boolean show)
	{
		if(show)
		{
			btn_nuti.setVisibility(View.INVISIBLE);
			btn_desc.setVisibility(View.INVISIBLE);
			btn_order.setVisibility(View.INVISIBLE);
			orderLayout.setVisibility(View.VISIBLE);
			btn_addToCard.setVisibility(View.VISIBLE);
		}
		else
		{
			btn_nuti.setVisibility(View.VISIBLE);
			btn_desc.setVisibility(View.VISIBLE);
			btn_order.setVisibility(View.VISIBLE);
			orderLayout.setVisibility(View.INVISIBLE);
			btn_addToCard.setVisibility(View.INVISIBLE);
		}
	}
	
	private void playAnim(boolean playDescAnim, boolean playNutiAnim)
	{
		if((playDescAnim || playNutiAnim))
		{
			if(!showDesc && playDescAnim)
			{
				showDesc = true;
				description.startAnimation(descAnimShow);
				btn_desc.startAnimation(btnAnimRight);
				//description.setX(descXShow);
			}
			else if(showDesc)
			{
				showDesc = false;
				description.startAnimation(descAnimHide);
				btn_desc.startAnimation(btnAnimLeft);
				//description.setX(descXHide);
			}
			
			if(!showNutri && playNutiAnim)
			{
				showNutri = true;
				scroll.startAnimation(nutiAnimShow);
				btn_nuti.startAnimation(btnAnimRight);
				//description.setX(descXShow);
			}
			else if(showNutri )
			{
				showNutri = false;
				scroll.startAnimation(nutiAnimHide);
				btn_nuti.startAnimation(btnAnimLeft);
				//description.setX(descXHide);
			}
		}
	}
	
	public Animation SetAnimation(int startX, final int endX, int duration, boolean listen)
	{
		Animation anim;
		anim = new TranslateAnimation(startX, endX, 0, 0);
		anim.setDuration(duration);
		anim.setInterpolator(new DecelerateInterpolator());
		anim.setFillAfter(true);
		if(listen)
		{
			anim.setAnimationListener(new Animation.AnimationListener() 
			{
	            @Override
	            public void onAnimationStart(Animation animation) 
	            { 
	            	animPlaying = true; 
	            }
	
	            @Override
	            public void onAnimationEnd(Animation animation) 
	            { 
	            	animPlaying = false;
	            	animEnd();
	            }
	
	            
	            public void onAnimationRepeat(Animation animation) { }
	        });
		}
		//anim.setFillEnabled(true);
		return anim;
	}
	
	private void animEnd()
	{
    	btn_desc.clearAnimation();
    	btn_nuti.clearAnimation();
    	scroll.clearAnimation();
    	description.clearAnimation();
    	
    	if(showDesc)
    	{
    		btn_desc.setX(menuButton_width / 4);
    		description.setX((int)(img_width * 0.1));
    	}
    	else
    	{
    		btn_desc.setX(0);
    		description.setX((int)(-img_width * 0.9) - 1);
    	}
    	
    	if(showNutri)
    	{
    		btn_nuti.setX(menuButton_width / 4);
    		scroll.setX(img_width / 2);
    	}
    	else
    	{
    		btn_nuti.setX(0);
    		scroll.setX(img_width + 1);
    	}
	}
	
	/*
	public void ToggleCustNuti()
	{
		if(showToggle)
		{
			customizations.setVisibility(View.VISIBLE);
			nutrition.setVisibility(View.GONE);
			btn_cnSwap.setText("Show Nutrition");
		}
		else
		{
			customizations.setVisibility(View.GONE);
			nutrition.setVisibility(View.VISIBLE);
			btn_cnSwap.setText("Show Customizations");
		}
		showToggle = !showToggle;
	}*/

}
