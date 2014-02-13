package com.aros.data;

public class SubMenuData extends MenuData {
	
	public SubMenuData(int id, int parentCateId, String name, long startDate, long duration)
	{
		super(id, name, startDate, duration);
		this.parentCateId = parentCateId;
	}
	
	public int parentCateId;
}
