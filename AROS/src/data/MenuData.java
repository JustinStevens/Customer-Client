package data;

import java.util.Map;
import java.util.TreeMap;

public class MenuData {
	public TreeMap<HashKey, CategoryData> cData;// = new HashMap<Key, CategoryData>();
	public TreeMap<HashKey, SubCategoryData> scData;// = new HashMap<Key, SubCategoryData>();
	public TreeMap<HashKey, ItemData> iData;// = new HashMap<Key, ItemData>();
	
	public MenuData(DBHelper dbHelper)
	{
		cData = dbHelper.getCategoryData();
		scData = dbHelper.getSubCategoryData();
		iData = dbHelper.getItemData();
	}

	public void LoadBitmaps() 
	{
		for(Map.Entry<HashKey, ItemData> entry : iData.entrySet()) 
		{
			ItemData value = entry.getValue();
			value.LoadBitmaps(Data.item_btn_width, Data.item_btn_height);//, lrg_width, lrg_height, nut_width, nut_height);
		}
	}
}


