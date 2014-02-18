package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

import main.MainActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    @SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/com.aros/databases/";
 
    private static String DB_NAME = "AROS_DB";
    
    private SQLiteDatabase myDatabase; 
 
    private final Context myContext;
    
    public DBHelper(Context context) 
    {
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
        //DB_PATH = this.myContext.getd.getFilesDir().getPath() + "/";
        //MainActivity.displayMessage(DB_PATH + DB_NAME);
    }	
 
    public void createDatabase() throws IOException
    {
    	myContext.deleteDatabase(DB_NAME);
    	boolean dbExist = checkDataBase();
    	
    	if(!dbExist) 
    	{
        	this.getReadableDatabase();
 
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
    }
 
    private boolean checkDataBase()
    {
    	SQLiteDatabase checkDB = null;
 
    	try {
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	} catch(SQLiteException e){
    		//database does't exist yet.
    	}
 
    	if(checkDB != null) {
    		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException
    {
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    }
 
    public void openDataBase() throws SQLException
    {
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    @Override
	public synchronized void close() 
    {
    	if(myDatabase != null)
		    myDatabase.close();
 
	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) { }
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
	
	public TreeMap<HashKey, CategoryData> getCategoryData()
	{
		TreeMap<HashKey, CategoryData> cData = new TreeMap<HashKey, CategoryData>();
		try
		{
			Cursor cursor = myDatabase.query("category", new String[] {
					"categoryId", "name", "startDate", "duration" }, 
					null, null, null, null, null, null);
			
			if (cursor != null) 
			{
				for(int i = 0; i < cursor.getCount(); i++)
				{
				    if (cursor.moveToNext())
				    	cData.put(new HashKey(cursor.getInt(0), 0), new CategoryData(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
				}
			}
		} 
		catch (Exception e) {MainActivity.displayMessage(e.toString());}
		return cData;
	}
	
	public TreeMap<HashKey, SubCategoryData> getSubCategoryData()
	{
		TreeMap<HashKey, SubCategoryData> scData = new TreeMap<HashKey, SubCategoryData>();
		try
		{
			Cursor cursor = myDatabase.query("subCategory", new String[] {
					"subId", "categoryId", "name", "startDate", "duration" }, 
					null, null, null, null, null, null);
			
			if (cursor != null) 
			{
				for(int i = 0; i < cursor.getCount(); i++)
				{
				    if (cursor.moveToNext())
				    	scData.put(new HashKey(cursor.getInt(0), cursor.getInt(1)), new SubCategoryData(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
				}
			}
		} 
		catch (NumberFormatException e) {}
		return scData;
	}
	
	public TreeMap<HashKey, ItemData> getItemData()
	{
		TreeMap<HashKey, ItemData> iData = new TreeMap<HashKey, ItemData>();
		try
		{
			Cursor cursor = myDatabase.query("item", new String[] {
					"itemId", "name", "price", "longDesc", "shortDesc", "refillPrice", "subId"}, 
					null, null, null, null, null, null);
			
			if (cursor != null) 
			{
				for(int i = 0; i < cursor.getCount(); i++)
				{
				    if (cursor.moveToNext())
				    	iData.put(new HashKey(cursor.getInt(0), cursor.getInt(6)), new ItemData(cursor.getInt(0), cursor.getInt(6), cursor.getString(1), 
				    			cursor.getString(4), cursor.getString(3), cursor.getInt(2), cursor.getInt(5),
				    			0, 0));
				}
			}
		} 
		catch (NumberFormatException e) {}
		return iData;
	}
}