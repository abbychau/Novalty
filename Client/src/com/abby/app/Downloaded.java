package com.abby.app;


import java.io.File;

import java.util.ArrayList;

import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Downloaded extends Activity {
	
	private String mylist[]={"test"};
	private ListView lv1;
	List<String> list = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloaded);


        File filelist = new File("/sdcard/novel/");
        mylist = filelist.list();
        
        lv1 = (ListView)findViewById(R.id.listView1);
        // By using setAdpater method in listview we an add string array in list.
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , mylist));

        lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	            Intent intent=new Intent();
	            intent.setClass(Downloaded.this, Reading.class);
	            intent.putExtra("file",lv1.getItemAtPosition(position).toString());  
	            Downloaded.this.startActivity(intent);
			}
        });
        
    }
    
    
    
    
}
