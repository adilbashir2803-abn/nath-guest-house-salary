package com.nathguesthouse.salary;
import android.app.*; import android.os.*; import android.webkit.*; import android.content.*; import android.telephony.SmsManager;
public class MainActivity extends Activity {
 WebView web;
 @Override public void onCreate(Bundle b){super.onCreate(b); web=new WebView(this); web.getSettings().setJavaScriptEnabled(true); web.getSettings().setDomStorageEnabled(true); web.setWebViewClient(new WebViewClient()); web.addJavascriptInterface(new Bridge(this),"Android"); web.loadUrl("file:///android_asset/index.html"); setContentView(web);}
 public static class Bridge { Context c; Bridge(Context x){c=x;} @JavascriptInterface public void sms(String phone,String msg){try{SmsManager.getDefault().sendTextMessage(phone,null,msg,null,null);}catch(Exception e){Intent i=new Intent(Intent.ACTION_SENDTO);i.setData(android.net.Uri.parse("smsto:"+phone));i.putExtra("sms_body",msg);c.startActivity(i);}}}
}