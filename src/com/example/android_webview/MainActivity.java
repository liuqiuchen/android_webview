package com.example.android_webview;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

//	private String url = "http://www.baidu.com";
	private WebView webview;
	
	//定义ProgressDialog
	private ProgressDialog dialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        
        /*Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        
        init();


    }

	private void init() {
		// TODO Auto-generated method stub
		webview = (WebView) findViewById(R.id.webView);
		//加载本地文件
		webview.loadUrl("http://www.tuziba.net/forum.php?mod=viewthread&tid=8386");
		//覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				//返回值为true的时候控制网页在WebView中打开
				return true;
			}
		});
		
		//启用支持JavaScript
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		
		//提示用户网页的加载信息
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				//newProgress 1-100之间的整数
				if(newProgress == 100){
					//网页加载完毕，关闭ProgressDialog
					closeDialog();
				}else{
					//网页正在加载，打开ProgressDialog
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if(dialog != null && dialog.isShowing()){
					dialog.dismiss();//取消显示
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(dialog == null){
					dialog = new ProgressDialog(MainActivity.this);
					dialog.setTitle("正在加载");
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.setIndeterminate(false);
					dialog.show();
				}else{
					dialog.setProgress(newProgress);
				}
			}
		});
		
	}
	
	//改写物理按钮返回的逻辑
	@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			
			if(keyCode == KeyEvent.KEYCODE_BACK){
				Toast.makeText(this, webview.getUrl(), Toast.LENGTH_LONG).show();
				if(webview.canGoBack()){
					webview.goBack();
					return true;
				}else{
					System.exit(0);//程序退出
				}
			}
		
			return super.onKeyDown(keyCode, event);
		}


    

}
























