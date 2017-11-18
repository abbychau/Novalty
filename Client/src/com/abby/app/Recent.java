package com.abby.app;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Recent extends Activity {
	

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloaded);


		//Get the text file
		File file = new File("/sdcard/novel/novelty_record");

		//Read text from file
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line2;

		    while ((line2 = br.readLine()) != null) {
		        text.append(line2);
		    }
		    
            Intent intent=new Intent();
            intent.setClass(Recent.this, Reading.class);
            intent.putExtra("file",text.toString());  
            Recent.this.startActivity(intent);
		    
		    
		}
		catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		

        
    }
    
    
    
    
}
