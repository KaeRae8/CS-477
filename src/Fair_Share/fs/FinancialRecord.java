package Fair_Share.fs;

public class FinancialRecord {
public String debterFirstName;
public String debterLastName;
public float debt;
public String loanerFirstName;
public String loanerLastName;

public FinancialRecord(String debterFirstName,String debterLastName, float debt, String loanerFirstName,String loanerLastName){
	this.debterFirstName=debterFirstName;
	this.debterLastName=debterLastName;
	this.debt=debt;
	this.loanerFirstName=loanerFirstName;
	this.loanerLastName=loanerLastName;
}

public String toString(){
	return debterFirstName+" "+debterLastName+" owes " +loanerFirstName +" "+loanerLastName +" $"+debt ;
}

}
