package Fair_Share.fs;

import java.util.Comparator;

import android.util.Log;
 
public class PriorityCompare implements Comparator<Item>{
 
    public int compare(Item item1, Item item2) {
 
    	Integer item1P=0;
    	Integer item2P=0;
    	

    	if(item1.priority.trim().equalsIgnoreCase("high")){
    	item1P=3;	
    	}
    	else if(item1.priority.trim().equalsIgnoreCase("medium")){
    		item1P=2;		
    	}
    	else if(item1.priority.trim().equalsIgnoreCase("low")){
    		item1P=1;	
    	}
    	
    	
    	if(item2.priority.trim().equalsIgnoreCase("high")){
    	item2P=3;	
    	}
    	else if(item2.priority.trim().equalsIgnoreCase("medium")){
    		item2P=2;		
    	}
    	else if(item2.priority.trim().equalsIgnoreCase("low")){
    		item2P=1;	
    	}
    	
    	return item2P.compareTo(item1P);

    	
    }
}