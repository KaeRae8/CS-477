package Fair_Share.fs;

import java.util.LinkedList;

public class OuterList {
public String firstName;
public String lastName;
public LinkedList<ItemList> itemLists;

public OuterList(){
	firstName="";
	lastName="";
	itemLists=new LinkedList<ItemList>();
}

}
