package com.aros.pages;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.aros.abstractclasses.AbstractPage;
import com.aros.customerclient.Ids;
import com.aros.customerclient.ItemInfo;
import com.aros.customerclient.MainActivity;
import com.aros.customerclient.R;

public class MenuPage extends AbstractPage {
	
	public ItemPage[] itemPages;

	private RelativeLayout touchLayout;
	
	int content_width;
	int content_height;
	int bar_width;
	int bar_height;
	int moveThreshold;
	int curr_page;
	
	private Button btn_next;
	private Button btn_prev;
	private Button btn_home;
	
	public MenuPage (MainActivity a, int id, int width, int height, ItemInfo[][] iInfo)
	{
		super(a, id, width, height);

		this.content_width = (int) (width);
		this.content_height = (int) (height * 0.88);
		this.bar_width = content_width;
		this.bar_height = (int) (height * 0.12);
		this.curr_page = 0;	
		
		this.moveThreshold = (int) (content_width * 0.25);

		this.touchLayout = new RelativeLayout(a);
		this.touchLayout.setLayoutParams(new LayoutParams(content_width, content_height));
		
		itemPages = new ItemPage[iInfo.length];
		
		itemPages[0] = new ItemPage(a, 0, content_width, content_height, iInfo[0]);
		itemPages[1] = new ItemPage(a, 1, content_width, content_height, iInfo[1]);
		
		pLayout.addView(itemPages[0].Get());
		pLayout.addView(itemPages[1].Get());
		
		itemPages[0].setVisible(false);
		itemPages[1].setVisible(false);
		
		btn_prev = SetButton(a, "Previous", Ids.BTN_MENU_PREV, 0, this.content_height, (int)(this.bar_height * 2.5), this.bar_height);
		btn_next = SetButton(a, "Next", Ids.BTN_MENU_NEXT, (this.content_width - (int)(this.bar_height * 2.5)), this.content_height, (int)(this.bar_height * 2.5), this.bar_height);
		btn_home = SetButton(a, "Home", Ids.BTN_MENU_HOME, (this.content_width / 2 - (int)(this.bar_height * 2.5 / 2)), this.content_height, (int)(this.bar_height * 2.5), this.bar_height);

		this.pLayout.addView(btn_prev);
		this.pLayout.addView(btn_next);
		this.pLayout.addView(btn_home);
		this.pLayout.addView(touchLayout);
		
		SetButtonVisibility();
		touchLayout.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		    	
				int X = (int) event.getX();
			    int Y = (int) event.getY();
		    	switch (event.getAction() & MotionEvent.ACTION_MASK) 
		    	{
		        case MotionEvent.ACTION_DOWN:
		        	downX = X;
		        	downY = Y;
		        	total_movedX = 0;
		        	total_movedY = 0;
		        	curr_moveX = 0;
		        	curr_moveY = 0;
		        	moving = false;
		        	time_down = System.currentTimeMillis();
		        	itemPages[curr_page].Get().dispatchTouchEvent(event);
		        	break;
		        	
		        case MotionEvent.ACTION_UP:
		        	time_up = System.currentTimeMillis();
		        	float distance = (float) Math.sqrt((X-downX) * (X-downX) + (Y-downY) * (Y-downY));
	                float speed = distance / (time_up - time_down);
		        	
	                itemPages[curr_page].Get().dispatchTouchEvent(event);
		        	if(moving)
		        		itemPages[curr_page].SetClickable(true);
	                
		        	CheckMove(speed);
		        	
		        	moving = false;
		        	break;
		        	
		        case MotionEvent.ACTION_MOVE:
		        		                
		        	curr_moveX = prevX - X;
		        	total_movedX += curr_moveX;

		        	if(moving || total_movedX > 10 || total_movedX < -10)
		        	{
		        		if(moving || total_movedX > 25 || total_movedX < -25)
		        		{
		        			moving = true;
		        			itemPages[curr_page].SetClickable(false);
		        		}
		        		
			        	if(curr_page == 0 && total_movedX < 0)
			        	{
			        		if(total_movedX < -(content_width / 20))
			        			total_movedX = -(content_width / 20);
			        		downX = X;
			        	}
			        	else if(curr_page == itemPages.length - 1 && total_movedX > 0)
			        	{
			        		if(total_movedX > content_width / 20)
			        			total_movedX = content_width / 20;
			        		downX = X;
			        	}
			        	
			        	
			        	Move(total_movedX);
		        	}
		        	
		        	itemPages[curr_page].Get().dispatchTouchEvent(event);
		            break;
		    	}
		    	
		    	prevX = X;
		    	
		        return true;
		    }
		    
		});
	}
	long time_down = 0;
	long time_up = 0;
	int downX = 0;
	int downY = 0;
	int prevX = 0;
	int total_movedX = 0;
	int total_movedY = 0;
	int curr_moveX = 0;
	int curr_moveY = 0;
	
	boolean moving = false;
	
	public void Move(int x)
	{
		itemPages[curr_page].Get().setX(-x);
	}
	
	public void CheckMove(float speed)
	{
		if(total_movedX > this.moveThreshold || (speed > 1.5 && total_movedX > 0))
			GoToNextPage();
		else if (-total_movedX > this.moveThreshold || (speed > 1.5 && total_movedX < 0))
			GoToPrevPage();
		else
			GoToSamePage();
	}
	
	public void ShowPage(int id)
	{
		itemPages[id].setVisible(true);
	}
	
	public void HidePage(int id)
	{
		itemPages[id].setVisible(false);
	}
	
	private Button SetButton(Activity a, String text, int id, int x, int y, int width, int height)
	{		
	    Button button;
		button = new Button(a);
	    button.setLayoutParams(new LayoutParams(width, height));
	    button.setId(id);
	    button.setText(text);
	    button.setTextSize((float) (height / 3.75));
	    button.setX(x);
	    button.setY(y);
	    button.setPadding(0, 0, 0, 0);
	    StateListDrawable states = new StateListDrawable();
	    states.addState(new int[] {android.R.attr.state_pressed}, menuButton_pressed);
	    states.addState(new int[] { }, menuButton_normal);
	    button.setBackgroundDrawable(states);
	    
	    button.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
	    
	    return button;
	}
	
	private void OnClick(View v)
	{
		switch(v.getId())
		{
		case Ids.BTN_MENU_NEXT: GoToNextPage(); break;
		case Ids.BTN_MENU_PREV: GoToPrevPage(); break;
		case Ids.BTN_MENU_HOME: a.OnClick(v); break;
		}
	}
	
	private void GoToNextPage()
	{
		if((curr_page + 1 < itemPages.length))
		{
			itemPages[curr_page].PlayAnimation((int)(itemPages[curr_page].Get().getX()), -content_width);
			itemPages[curr_page].Get().setX(0);
			HidePage(curr_page);
			ShowPage(++curr_page);
			itemPages[curr_page].PlayAnimation((int)content_width, 0);
		}
		else
			GoToSamePage();
		
		SetButtonVisibility();
	}
	
	private void GoToPrevPage()
	{
		if(!(curr_page - 1 < 0))
		{
			itemPages[curr_page].PlayAnimation((int)(itemPages[curr_page].Get().getX()), content_width);
			itemPages[curr_page].Get().setX(0);
			HidePage(curr_page);
			ShowPage(--curr_page);
			itemPages[curr_page].PlayAnimation((int)-content_width, 0);
		}
		else
			GoToSamePage();
		
		SetButtonVisibility();
	}
	
	private void GoToSamePage()
	{
		itemPages[curr_page].PlayAnimation((int)(itemPages[curr_page].Get().getX()), 0);
		itemPages[curr_page].Get().setX(0);
		
		SetButtonVisibility();
	}
	
	private void SetButtonVisibility()
	{
		if(curr_page + 1 >= itemPages.length)
		{
			btn_next.setClickable(false);
			btn_next.setEnabled(false);
		}
			//btn_next.setVisibility(View.GONE);
		else
		{
			btn_next.setClickable(true);
			btn_next.setEnabled(true);
		}
			//btn_next.setVisibility(View.VISIBLE);
		
		if(curr_page - 1 < 0)
		{
			btn_prev.setClickable(false);
			btn_prev.setEnabled(false);
		}
			//btn_prev.setVisibility(View.GONE);
		else
		{
			btn_prev.setClickable(true);
			btn_prev.setEnabled(true);
		}
			//btn_prev.setVisibility(View.VISIBLE);
	}
	
	/*public boolean onTouch(View view, MotionEvent event) {
	    final int X = (int) event.getRawX();
	    final int Y = (int) event.getRawY();
	    switch (event.getAction() & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_DOWN:
	            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
	            _xDelta = X - lParams.leftMargin;
	            _yDelta = Y - lParams.topMargin;
	            break;
	        case MotionEvent.ACTION_UP:
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN:
	            break;
	        case MotionEvent.ACTION_POINTER_UP:
	            break;
	        case MotionEvent.ACTION_MOVE:
	            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
	            layoutParams.leftMargin = X - _xDelta;
	            layoutParams.topMargin = Y - _yDelta;
	            layoutParams.rightMargin = -250;
	            layoutParams.bottomMargin = -250;
	            view.setLayoutParams(layoutParams);
	            break;
	    }
	    _root.invalidate();
	    return true;
	}*/
}
