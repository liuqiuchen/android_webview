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
	
	//����ProgressDialog
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
		//���ر����ļ�
		webview.loadUrl("http://www.tuziba.net/forum.php?mod=viewthread&tid=8386");
		//����WebViewĬ��ͨ��������������ϵͳ���������ҳ����Ϊ��ʹ����ҳ������WebView�д�
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				//����ֵΪtrue��ʱ�������ҳ��WebView�д�
				return true;
			}
		});
		
		//����֧��JavaScript
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		
		//��ʾ�û���ҳ�ļ�����Ϣ
		webview.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				//newProgress 1-100֮�������
				if(newProgress == 100){
					//��ҳ������ϣ��ر�ProgressDialog
					closeDialog();
				}else{
					//��ҳ���ڼ��أ���ProgressDialog
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if(dialog != null && dialog.isShowing()){
					dialog.dismiss();//ȡ����ʾ
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(dialog == null){
					dialog = new ProgressDialog(MainActivity.this);
					dialog.setTitle("���ڼ���");
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
	
	//��д����ť���ص��߼�
	@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			
			if(keyCode == KeyEvent.KEYCODE_BACK){
				Toast.makeText(this, webview.getUrl(), Toast.LENGTH_LONG).show();
				if(webview.canGoBack()){
					webview.goBack();
					return true;
				}else{
					System.exit(0);//�����˳�
				}
			}
		
			return super.onKeyDown(keyCode, event);
		}


    

}
























