package Fair_Share.fs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.StringTokenizer;
import android.util.Log; 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends Activity{
	
	public boolean startup = true;
	public File users;
	public LinkedList<User> userList;
    public EditText username;
    public EditText password;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.signin);
        try{
        File users = readFile("Users.txt");
    	userList=parseUsers(users);
    	password=(EditText) findViewById(R.id.password);
    	username=(EditText) findViewById(R.id.username);
       

        }
        catch(Exception e){
        	Log.d("colin", "mydebug "+e.getMessage());
        	e.printStackTrace();
        }
        Button signin = (Button) findViewById(R.id.signin_btn);

        
        signin.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	//I removed startup for now, because I was having trouble telling the app what user was logged in
            	//if(startup)
            	//{
            		
	            	Intent startMain = new Intent("fair_shareactivity.MAIN");
	            	
	            	//for easy login
	            	//startup = false;
	            	//startActivity(startMain);
	            	//setContentView(R.layout.main);
	            	
	            	
	            	if(validLogin())
	            	{
		            	User valid= getValidUser();
		            	startMain.putExtra("fName", valid.firstName);
		            	startMain.putExtra("lName", valid.lastName);
		            	startMain.putExtra("group", valid.groupName);
		            	startActivity(startMain);
		            	setContentView(R.layout.main);
		            	startup = false;
	            	}

            	}
            	//if(startup == false)
            	//{
            	//	setContentView(R.layout.main);
            	//}
           // }
        });
    }
    public boolean validLogin(){

    	String usernameInput= username.getText().toString();
    	String passwordInput= password.getText().toString();
    	if(userList==null){
    		Log.d("colin", "mydebug list is null");
    		return false;
    	}
    	if(userList.size()<1){
    		Log.d("colin", "mydebug list is empty");
    		return false;
    	}
    	for(int i=0;i<userList.size();i++){
    		Log.d("colin", "mydebug comparing "+userList.get(i).username+" and "+usernameInput+" and "+userList.get(i).password +" and "+passwordInput);
    	if(userList.get(i).username.equals(usernameInput) && userList.get(i).password.equals(passwordInput)){
    		return true;
    	}
    	}
    	return false;
    }
    public User getValidUser(){

    	String usernameInput= username.getText().toString();
    	String passwordInput= password.getText().toString();
    	User user=null;
    	for(int i=0;i<userList.size();i++){
    	if(userList.get(i).username.equals(usernameInput) && userList.get(i).password.equals(passwordInput)){
    		return userList.get(i);
    	}
    	}
    	return user;
    }
    
    public File readFile(String fileName) throws IOException
    {
    	File file=new File("/data/data/Fair_Share.fs/"+fileName);
		String serverIP="zombiegod.com";
        int serverPort = 1235;
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        
        try{
            socket = new Socket(serverIP,serverPort);
        }
        catch(BindException e){
            System.exit(-1);
        }
        try{
        	oos = new ObjectOutputStream(socket.getOutputStream());
            
                //encode request
				   String getRequest="read2";
				   oos.writeObject(getRequest);
				   getRequest=fileName;
				   oos.writeObject(getRequest);
                ois= new ObjectInputStream(socket.getInputStream());
                byte[] myBytes= new byte[1024];
				ois.read(myBytes,0,1024);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(myBytes);
                //socket.close();
        }
        catch(Exception e){
            System.out.println("I/O error");
            e.printStackTrace();
        }
        
    	return file;
    }
    
    public LinkedList<User> parseUsers(File fileIn){
    	LinkedList<User> list= new LinkedList<User>();
    	
        try{
          FileInputStream fstream = new FileInputStream(fileIn);
      	  DataInputStream in = new DataInputStream(fstream);
      	  BufferedReader br = new BufferedReader(new InputStreamReader(in));
      	  String strLine;
      	  StringTokenizer token;
      	  while ((strLine = br.readLine()) != null)   {
      		  token=new StringTokenizer(strLine);
      		  if(token.countTokens()==6){
      			  User tempUser = new User(token.nextToken(),token.nextToken(),token.nextToken(),token.nextToken(),token.nextToken(),token.nextToken());
      			  list.add(tempUser);
      		  }
      		  else{
      			  
      		  }
      		  System.out.println (strLine);
      		  }
      		  in.close();
      		  }
          catch(Exception e){
          
          }
        return list;
    }
}