package com.aros.layouts;

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

import com.aros.customerclient.Functions;
import com.aros.customerclient.R;
@SuppressWarnings("deprecation")
public class MainLayout {
	
	private Activity a;
	
	private RelativeLayout mainLayout;
	private RelativeLayout contentLayout;
	private LinearLayout leftColumn;
	private RelativeLayout topBar;
	
	private Button homeBtn;
	
	// Sizes for the column on the left of the layout
	private int leftCol_width;
	private int leftCol_height;
	
	// Sizes for the bar at the top of the layout
	private int topBar_height;
	private int topBar_width;
	
	// Sizes for the logo on the top left of the screen
	private int logo_width;
	private int logo_height;
	
	// Sizes for the main content area
	private int content_width;
	private int content_height;
	
	// Sizes for the main menu buttons
	private int menuButton_width;
	private int menuButton_height;
	
	// Sizes for the radiuses used by the menu buttons
	private float menuButton_btmR_Xradius; // Bottom Right X Radius
	private float menuButton_btmR_Yradius; // Bottom Right Y Radius
	private float menuButton_topR_Xradius; // Top Right X Radius
	private float menuButton_topR_Yradius; // Top Right Y Radius
	
	// Sizes for the radiuses used by the home button
	private float homeButton_topR_Xradius; // Top Right X Radius
	private float homeButton_topR_Yradius; // Top Right Y Radius
	
	// Sizes for the radiuses used by the waitress button
	private float waitressButton_btm_Xradius; // X Radius
	private float waitressButton_btm_Yradius; // Y Radius
	
	// height of the home button
	private int homeButton_height;
	
	// Gradients used to color and set corner radiuses for the menu buttons
	private GradientDrawable menuButton_normal;
	private GradientDrawable menuButton_pressed;

	// Gradients used to color and set corner radiuses for the home button
	private GradientDrawable homeButton_normal;
	private GradientDrawable homeButton_pressed;
	
	// Gradients used to color and set corner radiuses for the waitress button
	private GradientDrawable waitressButton_normal;
	private GradientDrawable waitressButton_pressed;
	
	// Temporary menu names until database is implemented
	private String[] menuNames = new String[]{"Breakfast", "Lunch", "Dinner", "Appetizers", "Desert", "Beverages", "Bar"};
	
	/****************************************************************************************
	 * Creates the main layout using the screens size to determine sizes
	 * @param activity: The activity to contain this layout
	 * @param displaySize: The size of the devices screen in pixels
	 ****************************************************************************************/
	public MainLayout(Activity activity, Point displaySize)
	{
		this.a = activity;
		SetParameters(displaySize);
		
		this.mainLayout = new RelativeLayout(this.a);
		this.mainLayout.setBackgroundColor(Color.DKGRAY);
		
		this.contentLayout = new RelativeLayout(this.a);
		this.contentLayout.setLayoutParams(new LayoutParams(this.content_width, this.getContent_height()));
		this.contentLayout.setX(leftCol_width);
		this.contentLayout.setY(topBar_height);
		this.mainLayout.addView(contentLayout);
		
		SetLogo();
		SetHomeButton();
		SetLeftColumn();
		SetMenuButtons(this.menuNames);
		
		SetTopBar();
		SetClock();
		SetCallWaitressButton();
	}
	/****************************************************************************************
	 * Calculates the parameters based on the devices screen size
	 * @param displaySize: The size of the devices screen in pixels
	 ****************************************************************************************/
	private void SetParameters(Point displaySize)
	{
		this.leftCol_width 	 	= (int) (displaySize.x / 6);
		this.menuButton_width 	= this.leftCol_width;
		this.logo_width 		= this.leftCol_width;
		this.logo_height 		= (int)(this.logo_width / 2);
		this.homeButton_height	= displaySize.y / 8;
		
		if(this.homeButton_height > menuButton_width / 2.25)
			this.homeButton_height = (int) (menuButton_width / 2.25);
		
		
		this.leftCol_height		= displaySize.y - this.homeButton_height - this.logo_height;
		this.menuButton_height	= this.leftCol_height / this.menuNames.length;
		this.topBar_height		= displaySize.y / 16;
		this.topBar_width		= displaySize.x - this.leftCol_width;
		
		if(this.menuButton_height > menuButton_width / 2.5)
			this.menuButton_height = (int) (menuButton_width / 2.5);
		
		this.content_width = displaySize.x - leftCol_width;
		this.content_height = (displaySize.y - topBar_height);
			
		this.menuButton_btmR_Xradius = (float) (menuButton_height * 0.85);
		this.menuButton_btmR_Yradius = (float) (menuButton_height * 0.75);
		
		this.menuButton_topR_Xradius = (float) (menuButton_height * 0.2);
		this.menuButton_topR_Yradius = (float) (menuButton_height * 0.2);
		
		this.homeButton_topR_Xradius = (float) (menuButton_height * 1.0);
		this.homeButton_topR_Yradius = (float) (menuButton_height * 1.0);
		
		this.waitressButton_btm_Xradius = (float) (topBar_height * 0.5);
		this.waitressButton_btm_Yradius = (float) (topBar_height * 0.5);
		
		menuButton_normal = Functions.SetGradient(
				new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);

		menuButton_pressed = Functions.SetGradient(
				new int[] { Color.WHITE, Color.LTGRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, menuButton_topR_Xradius, menuButton_topR_Yradius, menuButton_btmR_Xradius, menuButton_btmR_Yradius, 0, 0 },
				Color.DKGRAY, 1);
		
		homeButton_normal = Functions.SetGradient(
				new int[] { Color.LTGRAY, Color.GRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, homeButton_topR_Xradius, homeButton_topR_Yradius, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		homeButton_pressed = Functions.SetGradient(
				new int[] { Color.WHITE, Color.LTGRAY, Color.GRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, homeButton_topR_Xradius, homeButton_topR_Yradius, 0, 0, 0, 0 },
				Color.DKGRAY, 1);
		
		waitressButton_normal = Functions.SetGradient(
				new int[] { Color.LTGRAY, Color.GRAY, Color.LTGRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, 0, 0, waitressButton_btm_Xradius, waitressButton_btm_Yradius, waitressButton_btm_Xradius, waitressButton_btm_Yradius },
				Color.DKGRAY, 1);
		
		waitressButton_pressed = Functions.SetGradient(
				new int[] { Color.LTGRAY, Color.LTGRAY, Color.LTGRAY}, 
				new float[]{ 0, 0, 0, 0, 0, 0, 0, 0 },
				//new float[]{ 0, 0, 0, 0, waitressButton_btm_Xradius, waitressButton_btm_Yradius, waitressButton_btm_Xradius, waitressButton_btm_Yradius },
				Color.DKGRAY, 1);
	}

	/**
	 * 
	 * @param colors
	 * @param conerRadii
	 * @param stroke
	 * @param strokeWidth
	 * @return
	 */


	/**
	 * 
	 */
	public void SetLeftColumn()
	{
		this.leftColumn = new LinearLayout(this.a);
		this.leftColumn.setOrientation(LinearLayout.VERTICAL);
		this.leftColumn.setLayoutParams(new LayoutParams(this.leftCol_width, this.leftCol_height));
		this.leftColumn.setY(this.logo_height);
		this.mainLayout.addView(this.leftColumn);
	}

	/**
	 * 
	 * @param menuNames
	 */
	private void SetMenuButtons(String[] menuNames)
	{		
	    Button button;
		for(int i = 0; i < menuNames.length; i++)
		{
			button = new Button(this.a);
		    button.setText(menuNames[i]);
		    button.setGravity(Gravity.CENTER_VERTICAL);
		    button.setLayoutParams(new LayoutParams(this.menuButton_width, this.menuButton_height));
		    button.setId(1001+i);
		    button.setTextSize((float) (this.menuButton_height / 3.75));
		    button.setPadding(8, 0, 0, 20);
		    
		    StateListDrawable states = new StateListDrawable();
		    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
		    states.addState(new int[] { }, menuButton_normal);
		    
		    button.setBackgroundDrawable(states);
		    this.leftColumn.addView(button);
		}
	}
	
	public void SetMenuButtonSelected(int id, boolean selected)
	{
		Button btn = (Button)a.findViewById(id);
		if(btn != null)
		{
			if(selected)
				btn.setBackgroundDrawable(menuButton_pressed);
			else
			{
			    StateListDrawable states = new StateListDrawable();
			    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
			    states.addState(new int[] { }, menuButton_normal);
			    
			    btn.setBackgroundDrawable(states);
			}
				
		}
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
	private void SetClock()
	{
		DigitalClock clock = new DigitalClock(a);
		clock.setTextSize((float) (topBar_height / 2));
		clock.setPadding(0, 0, 10, 0);
		clock.setTextColor(Color.BLACK);
		clock.setY(0);
		clock.setId(101);
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		this.mainLayout.addView(clock, layout);
	}
	
	/**
	 * 
	 */
	private void SetLogo()
	{
		ImageView logo = new ImageView(this.a);
		logo.setImageDrawable(a.getResources().getDrawable(R.drawable.logo));
		logo.setScaleType(ImageView.ScaleType.CENTER_CROP);
		logo.setLayoutParams(new LayoutParams(this.logo_width, this.logo_height));
		logo.setPadding(0, 0, 0, 0);

		this.mainLayout.addView(logo);
	}

	/**
	 * 
	 */
	private void SetHomeButton()
	{
		homeBtn = new Button(this.a);
		homeBtn.setText("Home");
		homeBtn.setGravity(Gravity.CENTER_VERTICAL);
		homeBtn.setId(1000);
	    RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(this.menuButton_width, this.homeButton_height);
		layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		homeBtn.setTextSize(this.homeButton_height / 3);
		homeBtn.setPadding(8, 0, 10, 0);
	    
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, homeButton_pressed);
	    states.addState(new int[] { }, homeButton_normal);
	    
	    homeBtn.setBackgroundDrawable(states);
	    this.mainLayout.addView(homeBtn, layout);
	}
	
	private void SetCallWaitressButton()
	{
		Button button = new Button(this.a);
	    button.setText("Call Waitress");
	    button.setGravity(Gravity.CENTER);
	    button.setId(100);
	    RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(this.menuButton_width * 2, this.topBar_height);
		layout.addRule(RelativeLayout.LEFT_OF, 101);
		layout.setMargins(0, 0, 20, 0);
	    button.setTextSize(this.topBar_height / 3);
	    button.setPadding(8, 0, 10, 0);
	    
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, waitressButton_pressed);
	    states.addState(new int[] { }, waitressButton_normal);
	    
	    button.setBackgroundDrawable(states);
	   // this.mainLayout.addView(button, layout);
	}
	
	/**
	 * 
	 * @return
	 */
	public RelativeLayout Get()
	{
		return this.mainLayout;
	}
	
	public RelativeLayout GetContent() {
		return this.contentLayout;
	}
	
	public int getContent_height() {
		return content_height;
	}
	
	public int getContent_width() {
		return content_width;
	}
	public void showHideBtn(boolean b) {
		if(b)
		{
			homeBtn.setVisibility(Button.VISIBLE);
			homeBtn.setClickable(true);
		}
		else
		{
			homeBtn.setVisibility(Button.INVISIBLE);
			homeBtn.setClickable(false);
		}
	}
}
