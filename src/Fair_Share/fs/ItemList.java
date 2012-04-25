package Fair_Share.fs;

import java.util.LinkedList;

public class ItemList {
	public String listName;
	public LinkedList<Item> items;
	
	public ItemList(String listName){
		this.listName=listName;
		items=new LinkedList<Item>();
	}
}
