package essential;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastControl {
	private final String LOG_FILTER = "ESSENTIAL"; // To see all this class
													// method use this filter
													// name at LogCat
	private final Context context;
	private Toast toast;

	public ToastControl(Context context) {
		this.context = context;
	}

	public boolean setToast(String text, int duration) {
		try {
			destroyToast();
			toast = Toast.makeText(context, text, duration);
			toast.show();
		} catch (NullPointerException e) {
			Log.e(LOG_FILTER, "On SetToast => Error at setToast! 1a");
			return false; // Error on this method
		}
		return true;
	}

	public boolean setToast(String text, int duration, int gravity, int x, int y) {
		try {
			destroyToast();
			toast.setGravity(gravity, x, y);
			toast = Toast.makeText(context, text, duration);
			toast.show();
		} catch (NullPointerException e) {
			Log.e(LOG_FILTER, "On SetToast => Error at setToast! 1b");
			return false; // Error on this method
		}
		return true; // Method executed with success
	}

	public boolean createToast(Context context) {
		Log.d(LOG_FILTER, "createToast Called...");
		if (isToastAlreadyCreated()) {
			Log.i(LOG_FILTER, "Toast is already created !");
			return false;
		}

		if (context == null) {
			Log.e(LOG_FILTER, "createToast => <params> context is null...");
			return false;
		}

		toast = new Toast(context);
		Log.i(LOG_FILTER, "Toast created.");
		return true;
	}

	private boolean isToastAlreadyCreated() {
		return toast != null;
	}

	public void destroyToast() {
		Log.d(LOG_FILTER, "destroyToast called !");

		try {
			toast.cancel();
		} catch (NullPointerException e) {
			Log.i(LOG_FILTER, "Erro at destroyToast...");
		}
	}
}
