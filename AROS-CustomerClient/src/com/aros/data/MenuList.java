package com.aros.data;

public class MenuList {
	
	public MenuList(int id, String name, long startDate, long duration)
	{
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
	}
	
	public int id;
	public String name;
	public long startDate;
	public long duration;
}
