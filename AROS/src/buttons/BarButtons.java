package buttons;

import main.MainActivity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import data.Data;

public class BarButtons {

	private RelativeLayout bLayout;
	private Button btn_home;
	private Button btn_return;
	
	private MainActivity a;
	
	public BarButtons(MainActivity a)
	{
		this.a = a;
		
		LayoutParams params = new LayoutParams(Data.bar_width, Data.bar_height);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		this.bLayout = new RelativeLayout(a);
		this.bLayout.setLayoutParams(params);
		this.bLayout.setBackgroundColor(Color.WHITE);
		
		btn_home = SetButton("Home", Data.BTN_MENU_HOME, (Data.content_width / 2 - (int)((Data.bar_btn_width - Data.bar_btn_width) / 2)), 0, Data.bar_btn_width, Data.bar_height);
		btn_return = SetButton("Return", Data.BTN_MENU_RETURN, (Data.content_width / 2 - (int)((Data.bar_btn_width + Data.bar_btn_width) / 2)), 0, Data.bar_btn_width, Data.bar_height);
		
		bLayout.addView(btn_home);
		bLayout.addView(btn_return);
	}
	
	public RelativeLayout get()
	{
		return bLayout;
	}
	
	protected Button SetButton(String text, int id, int x, int y, int width, int height)
	{		
	    Button button;
		button = new Button(a);
	    button.setLayoutParams(new LayoutParams(width, height));
	    button.setId(id);
	    button.setText(text);
	    button.setTextSize((float) (height / 4));
	    button.setX(x);
	    button.setY(y);
	    button.setPadding(0, 0, 0, 0);
	    button.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	a.OnClick(v);
		    }
		});
	    
	    return button;
	}
	
	public Button addButton(String text, int id, int x)
	{
		Button btn = SetButton(text, id, x, 0, Data.bar_btn_width, Data.bar_height);
		bLayout.addView(btn);	
		
		return btn;
	}
}
