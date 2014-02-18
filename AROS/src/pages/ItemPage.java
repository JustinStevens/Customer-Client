package pages;

import main.MainActivity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import buttons.BarButtons;
import data.Data;
import framework.Page;

public class ItemPage extends Page {

	private BarButtons barBtns;
	private RelativeLayout content;
	private ImageView img;
	private TextView desctiption;
	private TextView customizations;
	
	public ItemPage(MainActivity a, int id, int width, int height) {
		super(a, id, width, height);
		
		int img_width = Data.item_btn_width;
		int img_height = (int) (Data.item_btn_height * 2);

		
		this.barBtns = new BarButtons(a);
		
		this.content = new RelativeLayout(a);
		this.content.setLayoutParams(new LayoutParams(Data.content_width, Data.content_height));
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(img_width, img_height);
		
		img = new ImageView(a);
		img.setLayoutParams(params);
		img.setBackgroundColor(Color.BLUE);
		
		params = new RelativeLayout.LayoutParams(img_width, Data.content_height - img_height);

		desctiption = new TextView(a);
		desctiption.setLayoutParams(params);
		desctiption.setText("SHORT DESCRIPTION GOES HERE");
		desctiption.setBackgroundColor(Color.YELLOW);
		desctiption.setY(img_height);
		
		params = new RelativeLayout.LayoutParams(img_width, Data.content_height);
		
		customizations = new TextView(a);
		customizations.setLayoutParams(params);
		customizations.setText("Customizations Go Here");
		customizations.setBackgroundColor(Color.GREEN);
		customizations.setX(img_width);
		
		this.content.addView(img);
		this.content.addView(desctiption);
		this.pLayout.addView(barBtns.get());
		this.pLayout.addView(content);
	}

	@Override
	public void OnClick(View v) {

	}

}
