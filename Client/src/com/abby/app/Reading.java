package com.abby.app;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Reading extends Activity {
	

	List<String> list = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading);

        Intent it = getIntent();
   
		//Get the text file
		File file = new File("/sdcard/novel/" + it.getStringExtra("file"));
		this.setTitle(it.getStringExtra("file"));
		
		//Read text from file
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line2;

		    while ((line2 = br.readLine()) != null) {
		        text.append(line2);
		        text.append('\n');
		    }
		}
		catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		final TextView tv;        
        tv = (TextView) findViewById(R.id.textView1);	
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setTextSize(18);
        
        tv.setText(text);
        
    }
    
    
    
    
}
