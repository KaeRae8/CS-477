
package Fair_Share.fs;
import java.util.Comparator;
 
public class PriceCompare implements Comparator<Item>{
 
    public int compare(Item item1, Item item2) {
 
    	Float item1P=Float.parseFloat(item1.price);
    	Float item2P=Float.parseFloat(item2.price);
    	return item1P.compareTo(item2P);
    	
    }
}