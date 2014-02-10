package com.aros.customerclient;

public class ItemInfo {
	
	public ItemInfo(int imgResId, String name, float price)
	{
		this.imgResId = imgResId;
		this.name = name;
		this.price = price;
	}
	
	public ItemInfo(int imgResId, String name, String desc)
	{
		this.imgResId = imgResId;
		this.name = name;
		this.desc = desc;
	}
	
	public int imgResId;
	public String name;
	public String desc;
	public float price;
}
