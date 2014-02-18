package data;

public class SubCategoryData extends CategoryData {
	
	public int parentId;

	public SubCategoryData(int id, int parentId, String name, long startDate, long duration)
	{
		super(id, name, startDate, duration);
		this.parentId = parentId;
	}
}
