package com.aros.data;

import android.content.Context;

public class ItemData {
	
	public ItemData(int id, int subId, String imageName, String name, 
			String shortDesc, String longDesc, int price, int refillPrice,
			int startTime, int durration, Context context, String packageName)
	{
		this.id = id;
		this.subId = subId;
		
		this.smallImgResId = context.getResources().getIdentifier("drawable/sml_" + imageName, null, packageName);
		this.largeImgResId = context.getResources().getIdentifier("drawable/lrg_" + imageName, null, packageName);
		this.nutritionImgResId = context.getResources().getIdentifier("drawable/nut_" + imageName, null, packageName);
		
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		
		this.price = price;
		this.refillPrice = refillPrice;
		
		this.startTime = startTime;
		this.durration = durration;
	}
	
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
