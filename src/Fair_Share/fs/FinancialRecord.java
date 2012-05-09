package Fair_Share.fs;

import java.util.LinkedList;

public class FinancialRecord {
public String debterFirstName;
public String debterLastName;
public LinkedList<FinancialInfo> info;

public FinancialRecord(String debterFirstName,String debterLastName){
	this.debterFirstName=debterFirstName;
	this.debterLastName=debterLastName;	
	info=new LinkedList<FinancialInfo>();
}

public FinancialRecord(String debterFirstName,String debterLastName, float debt, String loanerFirstName,String loanerLastName){
	this.debterFirstName=debterFirstName;
	this.debterLastName=debterLastName;
	info=new LinkedList<FinancialInfo>();
	info.add(new FinancialInfo(loanerFirstName,loanerLastName,debt));
}

public String toString(){
		

	
	 String returnString = debterFirstName+" "+debterLastName;
	for(int i=0;i<info.size();i++){
		// I only want to show positive debt
		returnString+= " owes " +info.get(i).loanerFirstName +" "+info.get(i).loanerLastName +" $"+info.get(i).debt ;
		
	}
	return returnString;
	
}

}
