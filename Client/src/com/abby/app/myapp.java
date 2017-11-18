package com.abby.app;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;

public class myapp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String html = "You are currently offline";
      

        setContentView(R.layout.main);
        

        
		WebView wv;        
        wv = (WebView) findViewById(R.id.wv1);	

		if(this.haveInternet()){
        	HttpClient client = new DefaultHttpClient();
        	HttpGet request = new HttpGet("http://abby.zkiz.com/novel.php");
        	HttpResponse response;
			try {
				response = client.execute(request);
				html = EntityUtils.toString(response.getEntity());
				
				
				//catch server error
			} catch (ClientProtocolException e) {
				html = "Server Unreachable";
				e.printStackTrace();
			} catch (IOException e) {
				html = "Server Unreachable";
				e.printStackTrace();
			}
			

			
		}else{
			
			findViewById(R.id.button1).setEnabled(false);
		}
			
		wv.loadData(html, "text/html", "utf-8");
		
		
        ImageView myImageView = (ImageView)findViewById(R.id.imageView1);
        myImageView.setImageResource(R.drawable.title);			
		


        findViewById(R.id.button1).setOnClickListener(new novelsButtonListener());
        findViewById(R.id.button2).setOnClickListener(new recentButtonListener()); 
        findViewById(R.id.button3).setOnClickListener(new downloadedButtonListener()); 

		
		

		
    };
    class novelsButtonListener implements OnClickListener{
        public void onClick(View v) {

        	
            Intent intent=new Intent();
            intent.setClass(myapp.this, Novel.class);
             
            myapp.this.startActivity(intent);

        }
    };
    class recentButtonListener implements OnClickListener{
        public void onClick(View v) {
        	
            Intent intent=new Intent();
            intent.setClass(myapp.this, Recent.class);
            //intent.putExtra("val", "test"); 
            myapp.this.startActivity(intent);

        }
    }; 
    class downloadedButtonListener implements OnClickListener{
        public void onClick(View v) {
        	
            Intent intent=new Intent();
            intent.setClass(myapp.this, Downloaded.class);
            //intent.putExtra("val", "test"); 
            myapp.this.startActivity(intent);

        }
    }; 
    private boolean haveInternet()
    {
    	boolean result = false;
    	ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); 
    	NetworkInfo info=connManager.getActiveNetworkInfo();
    	if (info == null || !info.isConnected())
    	{
    		result = false;
    	}
    	else 
    	{
    		if (!info.isAvailable())
    		{
    			result =false;
    		}
    		else
    		{
    			result = true;
    		}
    	}
    	
    	return result;
    }
    
}