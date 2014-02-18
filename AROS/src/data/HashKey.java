package data;

public class HashKey implements Comparable<HashKey> {
	public int id;
	public int parentId;
	
	public HashKey(int id, int parentId)
	{
		this.id = id;
		this.parentId = parentId;
	}

	@Override
	public int compareTo(HashKey another) {
		if(this.id < another.id)
	        return -1;
	    else if(this.id > another.id)
	        return 1;
	    return 0;
	}
}
