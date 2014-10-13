/*
package premium;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Premium {
    public static final String PREFS_NAME = "premium_prefers";
    private final Context context;
    private final String mString[] = {"Pro", "WakeLock", "Graphic", "Porcentage"};
    private final int mSize = 4;
    private boolean mSave;
    private int mQtdeKeys;

    public Premium(Context context) {
        this.context = context;
    }

    public int restoreInt(String key, int defValue) {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(key, defValue);
    }

    public void saveInt() {
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("Pro", mSave);

        // Commit the edits!
        editor.commit();
    }

    public boolean restoreBoolean() {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean("Pro", false);
    }

    public void saveBoolean() {
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("Pro", mSave);

        // Commit the edits!
        editor.commit();
    }


}*/
