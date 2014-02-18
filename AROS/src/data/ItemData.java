package data;

import android.R;
import android.graphics.Bitmap;

public class ItemData {
	public ItemData(int id, int subId, String name, 
			String shortDesc, String longDesc, int price, int refillPrice,
			int startTime, int durration)
	{
		this.id = id;
		this.subId = subId;
		
		this.smallImgResId = Data.res.getIdentifier("drawable/sml_" + id, null, Data.packageName);
		this.largeImgResId = Data.res.getIdentifier("drawable/lrg_" + id, null, Data.packageName);
		this.nutritionImgResId = Data.res.getIdentifier("drawable/nut_" + id, null, Data.packageName);
		
		if(this.smallImgResId == 0)
			this.smallImgResId = Data.res.getIdentifier("drawable/sml_none", null, Data.packageName);
		
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		
		this.price = price;
		this.refillPrice = refillPrice;
		
		this.startTime = startTime;
		this.durration = durration;
	}
	
	public void LoadBitmaps(int sml_width, int sml_height)//, int lrg_width, int lrg_height, int nut_width, int nut_height)
	{
		//sml_img = Functions.LoadBitmap(Data.res, smallImgResId, sml_width, sml_height);
		//lrg_img = Functions.LoadBitmap(Data.res, largeImgResId, lrg_width, lrg_height);
		//nut_img = Functions.LoadBitmap(Data.res, nutritionImgResId, nut_width, nut_height);
	}

	///public Bitmap sml_img;
	//public Bitmap lrg_img;
	//public Bitmap nut_img;
	
	public int id;
	public int subId;
	
	public int smallImgResId;
	public int largeImgResId;
	public int nutritionImgResId;
	
	public String name;
	public String shortDesc;
	public String longDesc;
	
	public float price;
	public float refillPrice;
	
	public int startTime;
	public int durration;
}
