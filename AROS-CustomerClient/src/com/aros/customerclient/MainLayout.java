package com.aros.customerclient;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@SuppressWarnings("deprecation")
public class MainLayout {
	
	private Activity a;
	
	private RelativeLayout mainLayout;
	private LinearLayout leftColumn;
	private RelativeLayout topBar;
	
	private int leftCol_width;
	private int leftCol_height;
	
	private int topBar_height;
	private int topBar_width;
	
	private int logo_width;
	private int logo_height;
	
	private int menuButton_width;
	private int menuButton_height;
	
	private float menuButton_btmR_Xradius;
	private float menuButton_btmR_Yradius;
	
	private float menuButton_topR_Xradius;
	private float menuButton_topR_Yradius;
	
	private float homeButton_topR_Xradius;
	private float homeButton_topR_Yradius;
	
	private int homeButton_height;
	
	private GradientDrawable menuButton_normal;
	private GradientDrawable menuButton_pressed;
	private GradientDrawable homeButton_normal;
	private GradientDrawable homeButton_pressed;
	
	private String[] menuNames = new String[]{"Breakfast", "Lunch", "Dinner", "Appetizers", "Desert", "Beverages", "Bar"};
	
	public MainLayout(Activity activity, Point displaySize)
	{
		this.a = activity;
		SetParameters(displaySize);
		this.mainLayout = new RelativeLayout(this.a);
		this.mainLayout.setBackgroundColor(Color.DKGRAY);
		
		SetLogo();
		SetHomeButton();
		SetLeftColumn();
		SetMenuButtons(this.menuNames);
		SetTopBar();
		SetClock();
	}
	
	private void SetParameters(Point displaySize)
	{
		this.leftCol_width 	 	= (int) (displaySize.x / 6);
		this.menuButton_width 	= this.leftCol_width;
		this.logo_width 		= this.leftCol_width;
		this.logo_height 		= (int)(this.logo_width / 2);
		this.homeButton_height	= displaySize.y / 8;
		
		if(this.homeButton_height > 200)
			this.homeButton_height = 200;
		
		this.leftCol_height		= displaySize.y - this.homeButton_height - this.logo_height;
		this.menuButton_height	= this.leftCol_height / this.menuNames.length;
		this.topBar_height		= displaySize.y / 10;
		this.topBar_width		= displaySize.x - this.leftCol_width;
		
		this.menuButton_btmR_Xradius = (float) (menuButton_height * 1.0);
		this.menuButton_btmR_Yradius = (float) (menuButton_height * 0.75);
		
		this.menuButton_topR_Xradius = (float) (menuButton_height * 0.2);
		this.menuButton_topR_Yradius = (float) (menuButton_height * 0.2);
		
		this.homeButton_topR_Xradius = (float) (menuButton_height * 1.0);
		this.homeButton_topR_Yradius = (float) (menuButton_height * 1.0);
		
		menuButton_normal = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY});
		menuButton_normal.setGradientType(GradientDrawable.RECTANGLE);
		menuButton_normal.setCornerRadii(new float[]{
    		0,0,
    		menuButton_topR_Xradius, menuButton_topR_Yradius,
    		menuButton_btmR_Xradius, menuButton_btmR_Yradius,
    		0,0});
		menuButton_normal.setStroke(1, Color.DKGRAY);
		
		menuButton_pressed = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { Color.LTGRAY, Color.LTGRAY, Color.GRAY});
		menuButton_pressed.setGradientType(GradientDrawable.RECTANGLE);
		menuButton_pressed.setCornerRadii(new float[]{
    		0,0,
    		menuButton_topR_Xradius, menuButton_topR_Yradius,
    		menuButton_btmR_Xradius, menuButton_btmR_Yradius,
    		0,0});
		menuButton_pressed.setStroke(1, Color.DKGRAY);
		
		homeButton_normal = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY});
		homeButton_normal.setGradientType(GradientDrawable.RECTANGLE);
		homeButton_normal.setCornerRadii(new float[]{
    		0,0,
    		homeButton_topR_Xradius, homeButton_topR_Yradius,
    		0,0,
    		0,0});
		homeButton_normal.setStroke(1, Color.DKGRAY);
		homeButton_normal.setSize(menuButton_width + (menuButton_width / 5), menuButton_height);
		
		homeButton_pressed = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { Color.LTGRAY, Color.LTGRAY, Color.GRAY});
		homeButton_pressed.setGradientType(GradientDrawable.RECTANGLE);
		homeButton_pressed.setCornerRadii(new float[]{
    		0,0,
    		homeButton_topR_Xradius, homeButton_topR_Yradius,
    		0,0,
    		0,0});
		homeButton_pressed.setStroke(1, Color.DKGRAY);
	}
	
	
	public void SetLeftColumn()
	{
		this.leftColumn = new LinearLayout(this.a);
		this.leftColumn.setOrientation(LinearLayout.VERTICAL);
		this.leftColumn.setLayoutParams(new LayoutParams(this.leftCol_width, this.leftCol_height));
		this.leftColumn.setY(this.logo_height);
		this.mainLayout.addView(this.leftColumn);
	}

	private void SetMenuButtons(String[] menuNames)
	{		
	    Button button;
		for(int i = 0; i < menuNames.length; i++)
		{
			button = new Button(this.a);
		    button.setText(menuNames[i]);
		    button.setGravity(Gravity.CENTER_VERTICAL);
		    button.setLayoutParams(new LayoutParams(this.menuButton_width, this.menuButton_height));

		    button.setTextSize((float) (this.menuButton_height / 3.75));
		    button.setPadding(8, 0, 0, 20);
		    
		    StateListDrawable states = new StateListDrawable();
		    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
		    states.addState(new int[] { }, menuButton_normal);
		    
		    button.setBackgroundDrawable(states);
		    this.leftColumn.addView(button);
		}
	}
	
	private void SetTopBar()
	{
		this.topBar = new RelativeLayout(this.a);
		this.topBar.setLayoutParams(new LayoutParams(this.topBar_width, this.topBar_height));
		this.topBar.setX(this.leftCol_width);
		this.topBar.setPadding(0, 0, 0, 0);
		this.topBar.setGravity(Gravity.RIGHT);
		this.topBar.setBackgroundResource(R.drawable.topbar);
		this.mainLayout.addView(this.topBar);
		
	}
	
	private void SetClock()
	{
		DigitalClock clock = new DigitalClock(a);
		clock.setTextSize((float) (topBar_height / 3));
		clock.setPadding(0, 0, 10, 0);
		clock.setTextColor(Color.BLACK);
		clock.setY(10);
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		this.mainLayout.addView(clock, layout);
	}
	
	private void SetLogo()
	{
		ImageView logo = new ImageView(this.a);
		logo.setImageDrawable(a.getResources().getDrawable(R.drawable.logo));
		logo.setLayoutParams(new LayoutParams(this.logo_width, this.logo_height));
		logo.setPadding(0, 0, 0, 0);
		this.mainLayout.addView(logo);
	}

	private void SetHomeButton()
	{
		Button button = new Button(this.a);
	    button.setText("Home");
	    button.setGravity(Gravity.CENTER_VERTICAL);

	    RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(this.menuButton_width, this.menuButton_height);
		layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

	    button.setTextSize(this.menuButton_height / 3);
	    button.setPadding(8, 0, 10, 0);
	    
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, homeButton_pressed);
	    states.addState(new int[] { }, homeButton_normal);
	    
	    button.setBackgroundDrawable(states);
	    this.mainLayout.addView(button, layout);
	}
	
	public RelativeLayout Get()
	{
		return this.mainLayout;
	}
}
