package edu.ronzyfonzy.AndroidGdxMenu.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;


public class ActionResolved implements ActionResolver {
	Application3D app;
	Handler uiThread;
	Context appContext;


	public ActionResolved(Context appContext, Application3D app) {
		uiThread = new Handler();
		this.appContext = appContext;
		this.app = app;
	}


	public void showShortToast(final CharSequence toastMessage) {
		uiThread.post(new Runnable() {
			public void run() {
				Toast.makeText(appContext, toastMessage, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}


	public void showLongToast(final CharSequence toastMessage) {
		uiThread.post(new Runnable() {
			public void run() {
				Toast.makeText(appContext, toastMessage, Toast.LENGTH_LONG)
				.show();
			}
		});
	}


	public void showAlertBox(final String alertBoxTitle,
			final String alertBoxMessage, final String alertBoxButtonText) {
		uiThread.post(new Runnable() {
			public void run() {
				new AlertDialog.Builder(appContext)
				.setTitle(alertBoxTitle)
				.setMessage(alertBoxMessage)
				.setNeutralButton(alertBoxButtonText,
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
					}
				}).create().show();
			}
		});
	}


	public void openUri(String uri) {
		Uri myUri = Uri.parse(uri);
		Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
		appContext.startActivity(intent);
	}


	@Override
	public void showToast(CharSequence toastMessage, int toastDuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMyList() {
		switch(app.showActivity){
			case 1:
				appContext.startActivity(new Intent(this.appContext, ActivityFirst.class));
				break;
			case 2:
				appContext.startActivity(new Intent(this.appContext, ActivitySecond.class));
				break;
			case 3:
				appContext.startActivity(new Intent(this.appContext, ActivityForth.class));
				break;
			case 4:
				appContext.startActivity(new Intent(this.appContext, ActivityThird.class));
				break;
			case 0:
				showLongToast("Postavite se na pravilno pozicijo!");
		}
		
	}
}