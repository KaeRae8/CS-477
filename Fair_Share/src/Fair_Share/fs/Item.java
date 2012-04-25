package Fair_Share.fs;

public class Item {

	public String name;
	public String quantity;
	public String price;
	public String priority;
	public String requestedBy;
	public String buyDate;
	public String desc;
	
	public Item( String name ,String price,String quantity,  String priority){
		this.name=name;
		this.quantity=quantity;
		this.price=price;
		this.priority=priority;
	}
	
	public Item( String name ,String price,String quantity,  String priority, String buyDate, String desc){
		this.name=name;
		this.quantity=quantity;
		this.price=price;
		this.priority=priority;
		this.buyDate=buyDate;
		this.desc=desc;
	}
	
}
