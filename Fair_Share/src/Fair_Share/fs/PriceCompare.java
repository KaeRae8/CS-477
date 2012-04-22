
package Fair_Share.fs;
import java.util.Comparator;
 
public class PriceCompare implements Comparator<Item>{
 
    public int compare(Item item1, Item item2) {
 
    	return item1.price.compareTo(item2.price);
    	
    }
}