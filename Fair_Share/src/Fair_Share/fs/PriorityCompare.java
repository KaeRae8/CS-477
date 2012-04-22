package Fair_Share.fs;

import java.util.Comparator;
 
public class PriorityCompare implements Comparator<Item>{
 
    public int compare(Item item1, Item item2) {
 
    	Integer item1P=0;
    	Integer item2P=0;
    	
    	if(item1.priority.equalsIgnoreCase("high")){
    	item1P=3;	
    	}
    	else if(item1.priority.equalsIgnoreCase("medium")){
    		item1P=2;		
    	}
    	else if(item1.priority.equalsIgnoreCase("low")){
    		item1P=1;	
    	}
    	if(item2.priority.equalsIgnoreCase("high")){
    	item2P=3;	
    	}
    	else if(item1.priority.equalsIgnoreCase("medium")){
    		item2P=2;		
    	}
    	else if(item1.priority.equalsIgnoreCase("low")){
    		item2P=1;	
    	}
    	return item1P.compareTo(item2P);

    	
    }
}