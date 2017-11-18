package com.abby.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Novel extends Activity {
	
	private String mylist[]={"test"};
	private ListView lv1;
	List<String> list = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel);


        	HttpClient client = new DefaultHttpClient();
        	StringBuilder builder = new StringBuilder();

        	HttpGet myget = new HttpGet("http://abby.zkiz.com/novel.php?type=retrivelist");
        	try {
        		HttpResponse response = client.execute(myget);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(
        		response.getEntity().getContent()));
        		for (String s = reader.readLine(); s != null; s = reader.readLine()) {
        			builder.append(s);
        		}
        		
        		//JSONObject jsonObject = new JSONObject(builder.toString());
        		mylist = builder.toString().split("9999");

        		
        	} catch (Exception e) {

        		e.printStackTrace();
        	}

    	

        
        lv1 = (ListView)findViewById(R.id.listView1);
        // By using setAdpater method in listview we an add string array in list.
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , mylist));

        lv1.setTextFilterEnabled(true);
        lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				
				int tmpid = Integer.parseInt(lv1.getItemAtPosition(position).toString().split("#")[0]);
				String tmptitle = lv1.getItemAtPosition(position).toString(); 
		        try {
		        	HttpClient client = new DefaultHttpClient();
		        	HttpGet request = new HttpGet("http://abby.zkiz.com/novel.php?type=downloadbook&id="+tmpid);
		        	HttpResponse response = client.execute(request);
		        	String retSrc = EntityUtils.toString(response.getEntity());
		        	
		        	
		        	// create a File object for the parent directory
		        	File wallpaperDirectory = new File("/sdcard/novel/");
		        	// have the object build the directory structure, if needed.
		        	wallpaperDirectory.mkdirs();
		        	
		        	File tmp = new File("/sdcard/novel/"+tmptitle);
	        		tmp.createNewFile();

		        	
					FileWriter f = new FileWriter("/sdcard/novel/"+tmptitle);
		            f.write(retSrc);
		            f.flush();
		            f.close();
		        	
		            AlertDialog.Builder adb=new AlertDialog.Builder(Novel.this);
		        	adb.setTitle("Download the Novel");
		        	adb.setMessage("The novel id:"+tmpid+" ("+lv1.getItemAtPosition(position)+") is downloaded!");
		        	adb.setPositiveButton("OK", null);
		        	adb.show();
		        	
        			
		        	//RECORD DOWN the last novel
		        	File tmp2 = new File("/sdcard/novel/novelty_record");
					tmp2.createNewFile();
					FileWriter f2 = new FileWriter("/sdcard/novel/novelty_record");
		            f2.write(tmptitle);
		            f2.flush();
		            f2.close();
					
		        } catch(IOException e) {
					e.printStackTrace();
		        	AlertDialog.Builder adb=new AlertDialog.Builder(Novel.this);
		        	adb.setTitle("Download the Novel");
		        	adb.setMessage(""+e.toString());
		        	adb.setPositiveButton("OK", null);
		        	adb.show();
		        }
		        


        	}

        });
        
    }
    
    
    
    
}
