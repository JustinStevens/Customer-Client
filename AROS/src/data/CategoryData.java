package data;

public class CategoryData {
	
	public int id;
	public String name;
	public long startDate;
	public long duration;

	public CategoryData(int id, String name, long startDate, long duration)
	{
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
	}
}
