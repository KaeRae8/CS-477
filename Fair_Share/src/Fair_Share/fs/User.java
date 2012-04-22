package Fair_Share.fs;

public class User {
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String email;
	public String groupName;
	
	public User(String username, String password,String firstName, String lastName, String email, String groupname){
		this.username=username;
		this.password=password;
		this.email=email;
		this.groupName=groupname;
		this.firstName=firstName;
		this.lastName=lastName;
	}
}
