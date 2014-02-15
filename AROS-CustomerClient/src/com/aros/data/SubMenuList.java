package com.aros.data;

public class SubMenuList extends MenuList {
	
	public SubMenuList(int id, int parentCateId, String name, long startDate, long duration)
	{
		super(id, name, startDate, duration);
		this.parentCateId = parentCateId;
	}
	
	public int parentCateId;
}
