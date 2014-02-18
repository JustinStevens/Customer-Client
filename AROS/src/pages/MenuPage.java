package pages;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import main.MainActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import buttons.BarButtons;
import data.Data;
import data.HashKey;
import data.ItemData;
import framework.Page;

public class MenuPage extends Page{

	private MenuSubPage cPage;
	private MenuSubPage pPage;
	private MenuSubPage nPage;
	
	private MenuSubPage temp;
	
	private RelativeLayout touchLayout;
	
	int content_width;
	int content_height;

	int moveThreshold;
	int swipeThreshold;
	int moveLeniency;
	int curr_page;
	
	int count;
	
	private Button btn_next;
	private Button btn_prev;
	private BarButtons barBtns;
	
	public MenuPage (MainActivity a, int id, int width, int height)
	{
		super(a, id, width, height);
		
		content_width = Data.content_width;
		content_height = Data.content_height;
		
		this.moveThreshold = (int) (content_width * 0.33);
		this.swipeThreshold = (int) (content_width * 0.2);
		this.moveLeniency = content_width / 20;
		
		cPage = new MenuSubPage(a, Data.PAGE_ITEM_LIST_ONE, content_width, content_height, this);
		pPage = new MenuSubPage(a, Data.PAGE_ITEM_LIST_TWO, content_width, content_height, this);
		nPage = new MenuSubPage(a, Data.PAGE_ITEM_LIST_THREE, content_width, content_height, this);
		
		barBtns = new BarButtons(a);
		btn_prev = barBtns.addButton("Previous", Data.BTN_MENU_PREV, 0);
		btn_next = barBtns.addButton("Next", Data.BTN_MENU_NEXT, Data.bar_width - Data.bar_btn_width);
		
		btn_prev.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
		
		btn_next.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	OnClick(v);
		    }
		});
		
		this.touchLayout = new RelativeLayout(a);
		this.touchLayout.setLayoutParams(new LayoutParams(content_width, content_height));

		this.pLayout.addView(barBtns.get());
		this.pLayout.addView(cPage.get());
		this.pLayout.addView(pPage.get());
		this.pLayout.addView(nPage.get());
		this.pLayout.addView(touchLayout);
		
		cPage.setVisible(true, false);
		pPage.setVisible(true, false);
		nPage.setVisible(true, false);
		setDefaultX();
		
		SetTouchLayoutListener();
		//cPage.SetButtonData(iData, startPos);
		this.curr_page = 0;
		this.moveThreshold = (int) (content_width * 0.25);
	}

	@Override
	public void OnClick(View v) {
		switch(v.getId())
		{
		case Data.BTN_MENU_NEXT:
			if(!nPage.IsInAnimationPlaying() && !pPage.IsInAnimationPlaying() && !cPage.IsInAnimationPlaying())
			{
				if(curr_page >= count)
					nPage.get().setX(content_width - moveLeniency);
				cTotal_movedX = moveLeniency;
				checkNextFade();
				checkMovement(2);
			}
			break;
		case Data.BTN_MENU_PREV: 
			if(!nPage.IsInAnimationPlaying() && !pPage.IsInAnimationPlaying() && !cPage.IsInAnimationPlaying())
			{
				if(curr_page <= 0)
				{
					pPage.get().setX(-content_width + moveLeniency);
					MainActivity.displayMessage("NOT WORKING? " + (-content_width + moveLeniency));
				}
				cTotal_movedX = -moveLeniency;
				checkPrevFade();
				checkMovement(2);
				
			}
			break;
		default: a.OnClick(v); break;
		}
	}
	
	SortedMap<HashKey, ItemData> ciData;
	ArrayList<ItemData> list;
	
	public void SetMenuData(TreeMap<HashKey, ItemData> iData, int subId)
	{
		ciData = iData.subMap(new HashKey(subId, 0), new HashKey(subId + 1, 0));
		
		list = new ArrayList<ItemData>();
		for(Map.Entry<HashKey, ItemData> entry : iData.entrySet()) 
		{
			if(((HashKey)entry.getKey()).parentId == subId)
				list.add(entry.getValue());
		}
		MainActivity.displayMessage("COUNT " + list.size());
		cPage.SetButtonData(list, 0);
		count = (list.size() + 5) / 6;
		curr_page = 0;
		
		SetPrevPage();
		SetNextPage();
	}
	
	int prev_downX;
	int downX;
	int downY;
	int cTotal_movedX;
	int current_moveX;
	long time_start;
	long time_end;
	boolean swappedPage = false;
	boolean moved = false;
	boolean faded = false;
	boolean moving = false;
	
	public void SetTouchLayoutListener()
	{
		touchLayout.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		    	
				int X = (int) event.getX();
			    int Y = (int) event.getY();
		    	switch (event.getAction() & MotionEvent.ACTION_MASK) 
		    	{
		        case MotionEvent.ACTION_DOWN:
		        	if(!nPage.IsInAnimationPlaying() && !pPage.IsInAnimationPlaying() && !cPage.IsInAnimationPlaying())
		        	{
			        	downX = X;
			        	downY = Y;
			        	cTotal_movedX = 0;
			        	current_moveX = 0;
			        	time_start = System.currentTimeMillis();
		                moved = false;
		                moving = false;
		        	}

		        	PassEvent(event);
		        	//setDefaultX();
		        	break;
		        	
		        case MotionEvent.ACTION_UP:
		        	if(!nPage.IsInAnimationPlaying() && !pPage.IsInAnimationPlaying() && !cPage.IsInAnimationPlaying())
		        	{
		        		if(!moved)
		                {
				        	time_end = System.currentTimeMillis();
				        	float distance = (float) Math.sqrt((X-downX) * (X-downX) + (Y-downY) * (Y-downY));
			                float speed = distance / (time_end - time_start);
		                
		                	swappedPage = checkMovement(speed);
			                moved = true;
		                }
		                
		                if(!swappedPage)
		                {
		                	nPage.get().setX(content_width);
		                	pPage.get().setX(-content_width);
		                }
		        	}
		        	
		        	PassEvent(event);
		        	
		        	if(moving)
		        	{
		        		SetClickable(true);
		        	}
		        	moving = false;
		        	//setDefaultX();
		        	break;
		        	
		        case MotionEvent.ACTION_MOVE:
		        	if(!nPage.IsInAnimationPlaying() && !pPage.IsInAnimationPlaying() && !cPage.IsInAnimationPlaying() && !moved)
		        	{
			        	current_moveX = prev_downX - X;
			        	cTotal_movedX += current_moveX;
			        	if(moving || cTotal_movedX > 10 || cTotal_movedX < -10)
			        	{
			        		if(moving || cTotal_movedX > 25 || cTotal_movedX < -25)
			        		{
			        			moving = true;
			        			SetClickable(false);
			        		}
				        	// Move the next page
				        	if(cTotal_movedX > 0)
				        	{
				        		if(curr_page >= count - 1 && cTotal_movedX > moveLeniency)
				        			cTotal_movedX = moveLeniency;
				        		nPage.get().setX(content_width - cTotal_movedX);
				        		pPage.get().setX(-content_width);

				        		checkNextFade();
				        		//pPage.get().setX(-content_width);
				        	}
				        	// Move the previous page
				        	else if (cTotal_movedX < 0)
				        	{
				        		if(curr_page <= 0 && cTotal_movedX < -moveLeniency)
				        			cTotal_movedX = -moveLeniency;
				        		pPage.get().setX(-content_width - cTotal_movedX);
				        		nPage.get().setX(content_width);
				        		
				        		checkPrevFade();
				        		
				        		//nPage.get().setX(content_width);
				        	}
				        	
				        	if(cTotal_movedX > moveThreshold || cTotal_movedX < -moveThreshold)
				        	{
				        		swappedPage = checkMovement(0);
				        		moved = true;
				        	}
			        	}
		        	}	
		        	PassEvent(event);
		            break;
		    	}

		    	prev_downX = X;
		        return true;
		    }
		    
		});
	}
	
	private void checkNextFade()
	{
		if(!faded && cTotal_movedX >= moveLeniency)
		{
			cPage.PlayAnimation(false);
			//cPage.get().setAlpha(FADE_AMOUNT);
			faded = true;
		}
		else if (faded && cTotal_movedX <= moveLeniency)
		{
			cPage.PlayAnimation(true);
			//cPage.get().setAlpha(FADE_AMOUNT);
			faded = false;
		}
	}
	
	private void checkPrevFade()
	{
		if(!faded && cTotal_movedX <= -moveLeniency)
		{
			cPage.PlayAnimation(false);
			//cPage.get().setAlpha(FADE_AMOUNT);
			faded = true;
		}
		else if (faded && cTotal_movedX >= -moveLeniency)
		{
			cPage.PlayAnimation(true);
			//cPage.get().setAlpha(FADE_AMOUNT);
			faded = false;
		}
	}
	
	private void setDefaultX()
	{
		cPage.get().setX(0);
		pPage.get().setX(-content_width);
		nPage.get().setX(content_width);
	}

	private void SetClickable(boolean clickable)
	{
		cPage.SetClickable(clickable);
		pPage.SetClickable(clickable);
		nPage.SetClickable(clickable);
	}
	
	private void PassEvent(MotionEvent event)
	{
		cPage.get().dispatchTouchEvent(event);
	}
	
	private boolean checkMovement(float speed)
	{
		if((cTotal_movedX <= -this.swipeThreshold) || (speed > 1.25 && cTotal_movedX < 0))
		{
			animate(true);
			return true;
		}
			
		else if((cTotal_movedX >= this.swipeThreshold && curr_page < count) || (speed > 1.25 && cTotal_movedX > 0))
		{
			animate(true);
			return true;
		}
		else
		{
			animate(false);
			return false;
		}
	}
	
	private void animate(boolean stayOnPage)
	{
		//MainActivity.displayMessage(nPage.get().getX());
		if(cTotal_movedX < 0)
		{
			if(stayOnPage && curr_page - 1 >= 0)
			{
				curr_page--;
				//SetPrevPage();
				pPage.get().setX(0);
				pPage.PlayAnimation((-content_width - cTotal_movedX), 0, -1);
				//pPage.PlayAnimation((int)(pPage.get().getX()), 0);
				//pPage.get().setX(0);
				SwapPrevPage();
				//curr_page--;
			}
			else
			{
				pPage.get().setX(-content_width);
				pPage.PlayAnimation(-cTotal_movedX, 0, 0);
				if(faded)
				{
					cPage.PlayAnimation(true);
					faded = false;
				}
			}
			//setDefaultX();
		}
		else if(cTotal_movedX > 0)
		{
			if(stayOnPage && curr_page + 1 < count)
			{
				curr_page++;
				nPage.get().setX(0);
				nPage.PlayAnimation((content_width - cTotal_movedX), 0, 1);
				SwapNextPage();
				//curr_page++;
			}
			else
			{
				nPage.get().setX(content_width);
				nPage.PlayAnimation( -cTotal_movedX, 0, 0);
				if(faded)
				{
					cPage.PlayAnimation(true);
					faded = false;
				}
			}
			//nPage.get().setX(0);
			//SwapNextPage();
			
			//setDefaultX();
		}
	}
	
	public void setNextDefaultX()
	{
		nPage.get().setX(content_width);
		nPage.get().bringToFront();
		pPage.get().bringToFront();
		touchLayout.bringToFront();
		SetPrevPage();// ADD syncronous thread to load these!!
		SetNextPage();
		nPage.get().setAlpha(1);
		pPage.get().setAlpha(1);
		cPage.get().setAlpha(1);
		nPage.get().clearAnimation();
		pPage.get().clearAnimation();
		faded = false;
	}
	
	public void setPrevDefaultX()
	{
		pPage.get().setX(-content_width);
		pPage.get().bringToFront();
		nPage.get().bringToFront();
		touchLayout.bringToFront();
		SetPrevPage();
		SetNextPage();
		nPage.get().setAlpha(1);
		pPage.get().setAlpha(1);
		cPage.get().setAlpha(1);
		nPage.get().clearAnimation();
		pPage.get().clearAnimation();
		faded = false;
	}
	
	private void SwapNextPage()
	{
		temp = cPage;
		cPage = nPage;
		nPage = temp;
		temp = null;
		
		//setDisplayOrder();
	}
	
	private void SwapPrevPage()
	{
		temp = cPage;
		cPage = pPage;
		pPage = temp;
		temp = null;
		//setDisplayOrder();
	}
	
	private void SetPrevPage()
	{
		if(curr_page - 1 >= 0)
			pPage.SetButtonData(list, (curr_page - 1) * 6);
		else
			pPage.SetButtonData(null, 0);
	}
	
	private void SetNextPage()
	{
		if(curr_page + 1 < count)
			nPage.SetButtonData(list, (curr_page + 1) * 6);
		else
			nPage.SetButtonData(null, 0);
	}
	
	/*public void CheckMove(float speed)
	{
		if(curr_page < count)
		{
			if(total_movedX > this.moveThreshold || (speed > 1.5 && total_movedX > 0))
				GoToNextPage();
			else if (-total_movedX > this.moveThreshold || (speed > 1.5 && total_movedX < 0))
				GoToPrevPage();
			else
				GoToSamePage();
		}
	}
	
	private void GoToNextPage()
	{
		if(curr_page + 1 < count)
		{
			curr_page++;
			SetPage();
			
			//cPage.PlayAnimation((int)(cPage.get().getX()), -content_width);
			cPage.get().setX(0);
			cPage.setVisible(false, false);
			//HidePage(curr_page);
			//ShowPage(++curr_page);
			oPage.setVisible(true, false);
			oPage.PlayAnimation((int)content_width, 0);
			oPage.get().setX(0);
			SwapPages();
		}
		else
			GoToSamePage();
		
		//SetButtonVisibility();
	}
	
	private void GoToPrevPage()
	{
		if(curr_page - 1 >= 0)
		{
			curr_page--;
			SetPage();
			
			//cPage.PlayAnimation((int)(cPage.get().getX()), content_width);
			cPage.get().setX(0);
			cPage.setVisible(false, false);
			//HidePage(curr_page);
			//ShowPage(--curr_page);
			oPage.setVisible(true, false);
			oPage.PlayAnimation((int)-content_width, 0);
			oPage.get().setX(0);
			SwapPages();
		}
		else
			GoToSamePage();
		
		//SetButtonVisibility();
	}
	
	private void GoToSamePage()
	{
		cPage.PlayAnimation((int)(cPage.get().getX()), 0);
		cPage.get().setX(0);
		
		//SetButtonVisibility();
	}
	

	
	private void SetPrevPage()
	{
		oPage.SetButtonData(list, (curr_page - 1) * 6);
	}
	
	private void SetNextPage()
	{
		oPage.SetButtonData(list, (curr_page + 1) * 6);
	}
	
	public void Move(int x)
	{
		cPage.get().setX(-x);
	}*/
}

