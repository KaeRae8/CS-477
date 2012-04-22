
package Fair_Share.fs;
import java.util.Comparator;
 
public class AlphaCompare implements Comparator<Item>{
 
    public int compare(Item item1, Item item2) {
 
    	return item1.name.compareTo(item2.name);
    	
    }
}