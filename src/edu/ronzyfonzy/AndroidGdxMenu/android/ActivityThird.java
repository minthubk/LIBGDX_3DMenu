package edu.ronzyfonzy.AndroidGdxMenu.android;

import android.app.Activity;
import android.os.Bundle;

public class ActivityThird extends Activity{
	Application3D app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app = (Application3D) getApplication();
		setContentView(R.layout.main3);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		app.position=0;
	}
}
