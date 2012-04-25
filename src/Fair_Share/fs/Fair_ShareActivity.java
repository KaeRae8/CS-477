package Fair_Share.fs;

import java.io.*;
import java.net.BindException;
import java.net.Socket;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class Fair_ShareActivity extends Activity {
	
	String noteTitle = "You have no new notifications.";
	LinkedList<OuterList> lists;
	String listOwnerFirstName;
	String listOwnerLastName;
	String currentListName;
	String currentFileName;
	OuterList currentOuterList;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(noteTitle);
        
        setContentView(R.layout.main);
        
        if(super.getIntent().getStringExtra("group").equals("null")){
        	
        }
        else{

        try{
        	lists = parseListFile(readFile(super.getIntent().getStringExtra("group")+".txt"));
        	

        	//writeFile("KennyShaw.txt",ConvertListToFile(parseListFile(readFile("ColinGray"+".txt")),readFile("ColinGray"+".txt")));

        	currentFileName=super.getIntent().getStringExtra("group")+".txt";

        	listOwnerFirstName=super.getIntent().getStringExtra("fName");
        	listOwnerLastName=super.getIntent().getStringExtra("lName");
        	
        	currentOuterList=null;
        	Log.d("colin","mydebug looking for list");
        	for(int i=0;i<lists.size();i++){
        		
        		Log.d("colin","mydebug comparing "+lists.get(i).firstName+" and "+listOwnerFirstName+" then " +lists.get(i).lastName +" and "+ listOwnerLastName);
        		if(lists.get(i).firstName.equalsIgnoreCase(listOwnerFirstName) && lists.get(i).lastName.equalsIgnoreCase(listOwnerLastName))
        		{
        			Log.d("colin","mydebug found list");
        		currentOuterList =lists.get(i);
        		}
        	}
        	
        	//display users first list
        	displayList(currentOuterList.itemLists.get(0),1);

        	currentListName=lists.get(0).itemLists.get(0).listName;
        	
        	///////////////////////////////////
        	//add radio groups
        	RadioGroup listRadios=(RadioGroup) this.findViewById(R.id.radioGroup1);
        	listRadios.removeAllViews();

        	RadioButton button;
        	OnClickListener listener;
        	
        	for(int i =0;i<currentOuterList.itemLists.size() && i<3;i++){
        		button=new RadioButton(Fair_ShareActivity.this);
        		button.setLayoutParams(new LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        		button.setText(currentOuterList.itemLists.get(i).listName);
        		
        		final String name=button.getText().toString();
        		
        		listener = new View.OnClickListener() 
                {
                    public void onClick(View v) 
                    {
                    	changeList(name);
                    	
                    }
                };
        		
        		button.setOnClickListener(listener);
        		
        		if(i==0){
        			//button.setChecked(true);
        		}
        		listRadios.addView(button);
        		
        		
        	}

        }
        catch(Exception e){
        	Log.d("colin", "mydebug error"+e.getMessage());
        }
        }
        
    	LayoutInflater inflater = (LayoutInflater) 
    	Fair_ShareActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	//Here x is the name of the xml which contains the popup components
    	final View popupView = inflater.inflate(R.layout.popup, null, false);
      	final PopupWindow pw = new PopupWindow(popupView,450,675,true);
      	
      	
        TabHost th = (TabHost) findViewById (R.id.tabhost);
        th.setup();
        
        TabSpec ts = th.newTabSpec("tagLists");
        ts.setContent(R.id.lists);
        ts.setIndicator("Lists");
        th.addTab(ts);
        
        ts = th.newTabSpec("tagGroups");
        ts.setContent(R.id.groups);
        ts.setIndicator("Groups");
        th.addTab(ts);
        
        ts = th.newTabSpec("tagMenu");
        ts.setContent(R.id.menu);
        ts.setIndicator("Menu");
        th.addTab(ts); 

        Button logout = (Button) findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {	            	
            	Intent signIn = new Intent(Fair_ShareActivity.this,SignIn.class);
        		startActivity(signIn);
            	setContentView(R.layout.signin);
            }
        });
        
        Button addItem = (Button) findViewById(R.id.additem_btn);
        addItem.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	//Here y is the id of the root component
            	pw.showAtLocation(findViewById(R.id.root), Gravity.CENTER, -200, -70);
            }
        });
        
        Button createlist = (Button) findViewById(R.id.createlist_btn);
        createlist.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Needs new popup window.");
            }
        });
        
        RadioButton alphaSort = (RadioButton) findViewById(R.id.alpha_radio);
        alphaSort.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i =0;i<currentOuterList.itemLists.size();i++){
					if(currentOuterList.itemLists.get(i).listName.equalsIgnoreCase(currentListName)){
						displayList(currentOuterList.itemLists.get(i),1);
					}
				}
			}
		});
        
        RadioButton prioritySort = (RadioButton) findViewById(R.id.priority_radio);
        prioritySort.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				for(int i =0;i<currentOuterList.itemLists.size();i++){
					if(currentOuterList.itemLists.get(i).listName.equalsIgnoreCase(currentListName)){
						displayList(currentOuterList.itemLists.get(i),2);
					}
				}
			}
		});
        
        
        Button delete_btn = (Button) findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Select items to be deleted.");
            }
        });
        
        Button buy_btn = (Button) findViewById(R.id.buy_btn);
        buy_btn.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Itmes have been bought? ***Needs confirmation window***");
            }
        });
        
        Button cancelpop = (Button) popupView.findViewById(R.id.popupcancel_btn);
        cancelpop.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	pw.dismiss();  	
            }
        });
        
        Button addpop = (Button) popupView.findViewById(R.id.popupadditem_btn);
        addpop.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	
            	AutoCompleteTextView name = (AutoCompleteTextView)pw.getContentView().findViewById(R.id.name_fill);
            	AutoCompleteTextView price = (AutoCompleteTextView)pw.getContentView().findViewById(R.id.price_fill);
            	AutoCompleteTextView quantity = (AutoCompleteTextView)pw.getContentView().findViewById(R.id.quantity_fill);
            	AutoCompleteTextView description = (AutoCompleteTextView)pw.getContentView().findViewById(R.id.description_fill);
            	EditText date = (EditText)pw.getContentView().findViewById(R.id.date_fill);
            	
            	RadioButton buttonHigh=(RadioButton)pw.getContentView().findViewById(R.id.priorityhigh_radio);
            	RadioButton buttonMed=(RadioButton)pw.getContentView().findViewById(R.id.prioritymed_radio);
            	RadioButton buttonLow=(RadioButton)pw.getContentView().findViewById(R.id.prioritylow_radio);


            	String nameText=name.getText().toString();
            	String priceText= price.getText().toString();
            	String quantityText=quantity.getText().toString();
            	String descriptionText= description.getText().toString();
            	
            	if(nameText.isEmpty()){
            		nameText="empty";
            	}
            	if(priceText.isEmpty()){
            		priceText="empty";
            	}
            	if(quantityText.isEmpty()){
            		quantityText="empty";
            	}
            	if(descriptionText.isEmpty()){
            		descriptionText="empty";
            	}

            	String priority="";
            	if(buttonHigh.isChecked()){
            		priority="high";
            	}
            	else if(buttonMed.isChecked()){
            		priority="medium";
            	}
            	else if(buttonLow.isChecked()){
            		priority="high";
            	}

            	
            	addItem(lists, new Item( nameText ,priceText ,quantityText, priority, quantityText, descriptionText), currentFileName,listOwnerFirstName,listOwnerLastName ,currentListName);
            	
            	makeToast("Item Added");
            	pw.dismiss();  	
            }
        });
        
        Button settings = (Button) findViewById(R.id.settings_btn);
        settings.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Needs popup window***");
            	pw.dismiss();  	
            }
        });
        
        Button about = (Button) findViewById(R.id.about_btn);
        about.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Needs popup window***");
            	pw.dismiss();  	
            }
        });
        
        Button help = (Button) findViewById(R.id.help_btn);
        help.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	makeToast("***Not Imp***Needs popup window***");
            	pw.dismiss();  	
            }
        });
        
    }
    public File readFile(String fileName) throws IOException
    {
    	File file=new File("/data/data/Fair_Share.fs/"+fileName);
		String serverIP="zombiegod.com";
		
        int serverPort = 1234;
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos= null;
        
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
            System.out.println("I/O error"+e.getMessage());
            e.printStackTrace();
        }
        
    	return file;
    }
    
    public File ConvertListToFile(LinkedList<OuterList> list,File file){
    	
    
    	try{
    	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    	for(int i=0;i<list.size();i++){
    		Log.d("colin","writing "+convertOuterListToLine(list.get(i)));
        	bw.write(convertOuterListToLine(list.get(i)));
        if(i != list.size()){	
        	bw.newLine();
        	}
    	}
    	bw.write(" done");
    	bw.flush();
    	bw.close();
    	}
    	catch(Exception e){
    		Log.d("colin", "mydebug error"+e.getMessage());
    	}
    	

    	return file;
    }
    
    public String convertOuterListToLine(OuterList list){
    	String returnString="";
    	returnString += list.firstName+" ";
    	returnString += list.lastName+" ";
    	
    	for(int i=0;i<list.itemLists.size();i++){
    		returnString+= convertItemListToLine(list.itemLists.get(i),i+1);
    	}
    	int countPlus=list.itemLists.size()+1;
    	returnString+="List"+countPlus+"Name: null";
    	return returnString;
    }

    public String convertItemListToLine(ItemList list,int listNumber){
    	String returnString="";
    	returnString+= "List"+listNumber+"Name:";
    	returnString+= " "+list.listName+" Items:";
    	
    	for(int i =0;i<list.items.size();i++){
    		returnString+=" "+list.items.get(i).name.replace(" ","_")+" "+list.items.get(i).price.replace(" ","_")+" "+list.items.get(i).quantity.replace(" ","_")+" "+list.items.get(i).priority+" "+list.items.get(i).buyDate.replace(" ","_")+" "+list.items.get(i).desc.replace(" ","_")+" ";
    	}
    	
    	return returnString;
    }
    
    public LinkedList<OuterList> parseListFile(File file){
    	
    	LinkedList returnList = new LinkedList<OuterList>();
    	
    	try{
		FileInputStream fis = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		
		//read each line
		while ((strLine = br.readLine()) != null)   {
			 Log.d("colin", "mydebug parsing"+strLine);
			if(! strLine.trim().isEmpty()){
			returnList.add(parseItemList(strLine));}
		}

		return returnList;
		
    	}
    	catch(Exception e){
    		 Log.d("colin", "mydebug error"+e.getMessage());
    		return returnList;
    	}
    	
    }
    
    //this is a mess but it works
    //this is what happens when you code until 6am
    public OuterList parseItemList(String str){
    	OuterList list=new OuterList() ;
    	StringTokenizer tkn =new StringTokenizer(str);
    	String temp;
    	String throwAway;
    	String listName;
    	String thisToken;
    	Item item;
    	ItemList innerList=null;
    	list.firstName = tkn.nextToken();
    	list.lastName = tkn.nextToken();
    	temp= tkn.nextToken();

    	while(temp.contains("List") && temp.contains("Name")&&tkn.hasMoreTokens()){

    		
    		listName=tkn.nextToken();
    		if(listName.trim().equalsIgnoreCase("null")){
    			break;
    		}
    		innerList=new ItemList(listName);

    		//get rid of items;
    		throwAway=tkn.nextToken().replace("_"," ");


    		while(! throwAway.equals("null")){
    			thisToken=tkn.nextToken();

    			if(thisToken.equals("null")){
    				break;
    				}
    			if(thisToken.equals("done")){
    				break;
    				}
    			if(thisToken.contains("List") && thisToken.contains("Name")){
    				temp=thisToken.replace("_"," ");
    				break;}
    			item=new Item(thisToken.replace("_"," "),tkn.nextToken().replace("_"," "),tkn.nextToken().replace("_"," "),tkn.nextToken().replace("_"," "),tkn.nextToken().replace("_"," "),tkn.nextToken().replace("_"," "));

    			innerList.items.add(item);
    		}
    		list.itemLists.add(innerList);
    	}

    	return list;
    	
    }
    

    public void displayList(ItemList listIn,int sortBy){
    	//1=alphabetical
    	//2=priority
    	//3=price
    	
    	LinearLayout listLayout = (LinearLayout)findViewById(R.id.linearList);
    	listLayout.removeAllViews();
    	
    	
        final float scale = getResources().getDisplayMetrics().density;
        LinearLayout layout1;
        CheckBox check1;
        TextView text1;
        TextView text2;
        LinearLayout layout2;
        TextView text3;
        TextView text4;
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        
        Comparator comparator= new AlphaCompare();
        if(sortBy==1){
        comparator = new AlphaCompare();
        }
        else if(sortBy==2){
        	comparator = new PriorityCompare();
        }
        else{
        	comparator = new PriceCompare();
        }
        Collections.sort(listIn.items,comparator);
        for(Item thisItem : listIn.items){
       
        	
        layout1 = new LinearLayout(Fair_ShareActivity.this);
    ////Edit menu popup /////////////////////////////////////////////////////////////////
        	LayoutInflater edit_inflater = (LayoutInflater) 
    	        Fair_ShareActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	        //Here x is the name of the xml which contains the popup components
    	        final View editpopView = edit_inflater.inflate(R.layout.editpop, null, false);
    	        final PopupWindow editpw = new PopupWindow(editpopView,450,675,true);

    	        layout1.setOnLongClickListener(new View.OnLongClickListener() 
    	        {
    	            public boolean onLongClick(View v) 
    	            {
    	            	editpw.showAtLocation(findViewById(R.id.root), Gravity.CENTER, -200, -70);
    	            	
    	            	 
    	            	return true;
    	            }
    	        });
    	        
	        Button editcancelpop = (Button) editpopView.findViewById(R.id.cancelpop_btn);
	        editcancelpop.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v) 
	            {
	            	editpw.dismiss();  	
	            }
	        });
	        
	        Button editpop = (Button) editpopView.findViewById(R.id.editpop_btn);
	        editpop.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v) 
	            {
	            	makeToast("Item Saved");
	            	editpw.dismiss();  	
	            }
	        });
    ////end edit menu popup /////////////////////////////////////////////////////////////////
    	        
       
        

        layout1.setBackgroundColor(0xccCC0000);
        layout1.setOrientation(0);
        layout1.setLayoutParams(llp);
        
        check1 = new CheckBox(Fair_ShareActivity.this);
            check1.setLayoutParams(new LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            check1.setPadding((int) (39 * scale + 0.5f), 0, (int) (5 * scale + 0.5f), 0);
            check1.setTextAppearance(this, android.R.style.TextAppearance_Large);
            check1.setText(thisItem.name);
        
        text1 = new TextView(Fair_ShareActivity.this);
        text1.setLayoutParams(new LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text1.setPadding(0, 0, (int) (5 * scale + 0.5f), 0);
        text1.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        text1.setText(thisItem.quantity);
        
        text2 = new TextView(Fair_ShareActivity.this);
        text2.setLayoutParams(new LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text2.setPadding(0, 0, (int) (5 * scale + 0.5f), 0);
        text2.setTextAppearance(this, android.R.style.TextAppearance_Large);
        text2.setText("$"+thisItem.price);
       
        layout1.addView(check1);
        layout1.addView(text1);
        layout1.addView(text2);
        
        layout2 = new LinearLayout(Fair_ShareActivity.this);
        layout2.setLayoutParams(llp);
        
        text3 = new TextView(Fair_ShareActivity.this);
        text3.setLayoutParams(new LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text3.setPadding((int) (39 * scale + 0.5f),0, (int) (5 * scale + 0.5f), 0);
        text3.setTextAppearance(this, android.R.style.TextAppearance_Small);
        //text3.setText("requested by:");
        
        text4 = new TextView(Fair_ShareActivity.this);
        text4.setLayoutParams(new LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text4.setPadding(0, 0, (int) (5 * scale + 0.5f), 0);
        text4.setTextAppearance(this, android.R.style.TextAppearance_Small);
        //text4.setText("joe");
        if(thisItem.priority.equalsIgnoreCase("high")){
        	layout1.setBackgroundColor(0xccCC0000);
        	layout2.setBackgroundColor(0xccCC0000);

        }
        if(thisItem.priority.equalsIgnoreCase("medium")){
        	layout1.setBackgroundColor(0xccFF6633);
        	layout2.setBackgroundColor(0xccFF6633);

        }
        if(thisItem.priority.equalsIgnoreCase("low")){
        	layout1.setBackgroundColor(Color.LTGRAY);
        	layout2.setBackgroundColor(Color.LTGRAY);

        }
        layout2.addView(text3);
        layout2.addView(text4);
        
        listLayout.addView(layout1);
        listLayout.addView(layout2);
        }
    }
    
  //CREATES TOASTS
    public void makeToast(String s)
    {
    	Context context = getApplicationContext();
    	CharSequence text = s;
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.setGravity(Gravity.CENTER,0,0);
    	toast.show();
    }
  
    public void addItem(LinkedList<OuterList> list, Item item, String filename,String listOwnerFirstName,String listOwnerLastName ,String listName){
    	
    	
    	try{
    		System.out.println("add item step 1");
    	for(int i=0;i<list.size();i++){
    		System.out.println("add item step 2");
    		if(list.get(i).firstName.equalsIgnoreCase(listOwnerFirstName)  && list.get(i).lastName.equalsIgnoreCase(listOwnerLastName)){
    		for(int j=0;j<list.get(i).itemLists.size();j++){
    			if(list.get(i).itemLists.get(j).listName==listName){
    				list.get(i).itemLists.get(j).items.add(item);
    				System.out.println("adding item i:"+i+" j:"+j);
    				displayList(list.get(i).itemLists.get(j),1);
    			}
    				
    			}
    		}
    	}
    	
    	//the first list of every person needs to be the same.
    	//for(int i=0;i<list.size();i++){
    	//	list.get(i).itemLists.get(0).items  = list.get(0).itemLists.get(0).items;
    	//}
    	
    	writeFile(filename,ConvertListToFile(list,readFile(filename)));
    	}
    	catch(Exception e){
    		Log.d("mydebug","error adding item to list "+e.getMessage());
    	}
    }

    public void changeList(String listName){
    	for(int i=0;i<currentOuterList.itemLists.size();i++){
    		if(currentOuterList.itemLists.get(i).listName.equalsIgnoreCase(listName)){
    			displayList(currentOuterList.itemLists.get(i),1);
    			currentListName=listName;
    		}
    	}
    }
    
    public void writeFile(String fileName, File file){
        Socket socket = null;
        String serverIP;
        int serverPort;
        String resource;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        int stage=1;
        
			serverIP="zombiegod.com";
            serverPort = 1234;

        
        try{
		    socket = new Socket(serverIP,serverPort);
		}
		catch(Exception e){
		    System.out.println(e.getMessage());
		    
		}
		
		int remotePort = socket.getPort();
		int localPort  = socket.getLocalPort();
		    try{
		    	oos=new ObjectOutputStream(socket.getOutputStream());
		        
		            //encode request
					   String getRequest="write2";
					   oos.writeObject(getRequest);
					   oos.writeObject(fileName);

					
				    	try{
				    		FileInputStream fis = new FileInputStream(file);
				    		DataInputStream in = new DataInputStream(fis);
				    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
				    		String strLine;
				    		
				    		
				    		//read each line
				    		while ((strLine = br.readLine()) != null)   {
				    			 Log.d("colin", "mydebug parsing"+strLine);
				    			if(! strLine.trim().isEmpty()){
				    				//write each line
				    				oos.writeObject(strLine);
				    			}
				    		}
				    		oos.writeObject("finishedFile");
						oos.flush();
					
				    	}
				    	catch(Exception e){
				    		System.out.println(e.getMessage());
				    	}
		            socket.close();
					System.out.println("transfer success");
				    	
		    }
		    catch(Exception e){
		        System.out.println("I/O error");
		        e.printStackTrace();
		    }
    }
}