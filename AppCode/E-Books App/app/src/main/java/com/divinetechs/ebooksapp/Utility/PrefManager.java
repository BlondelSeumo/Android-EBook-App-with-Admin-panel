package com.divinetechs.ebooksapp.Utility;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.divinetechs.ebooksapp.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.DOWNLOAD_SERVICE;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;
    public static String pushRID = "0";
    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String LOGIN_ID = "LOGIN";
    public static String Type = "image";

    public static Typeface scriptable;

    public static final String NIGHT_MODE = "NIGHT_MODE";
    private boolean isNightModeEnabled = false;
    private String filename;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        scriptable = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_medium.ttf");
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setLoginId(String id) {
        editor.putString(LOGIN_ID, id);
        editor.commit();
    }

    public String getLoginId() {
        return pref.getString(LOGIN_ID, "0");
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


    public void setBool(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBool(String key) {
        return pref.getBoolean(key, true);
    }

    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        return pref.getString(key, "0");
    }

    //network check
    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //rtl
    public static void forceRTLIfSupported(Window window, Activity activity) {
        /*if (activity.getResources().getString(R.string.isRTL).equals("true")){}*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.e("local_data", "" + LocaleUtils.getSelectedLanguageId());
            if ("ar".equals(LocaleUtils.getSelectedLanguageId())) {
                window.getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            } else {
                window.getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        } else {
            window.getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }
    }

    public boolean isNightModeEnabled() {
        return pref.getBoolean(NIGHT_MODE, true);
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.commit();
    }

    public void download(String id, String bookName, String bookImage, String bookAuthor, String bookUrl, String type) {

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EBook_divine/");
        if (!root.exists()) {
            root.mkdirs();
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(bookUrl));
        request.setDescription(_context.getResources().getString(R.string.downloading) + bookName);
        request.setTitle(_context.getResources().getString(R.string.app_name));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        filename = "filename-" + id;
        if (type.equals("epub")) {
            request.setDestinationInExternalPublicDir("/EBook_divine/", filename + ".epub");
        } else {
            request.setDestinationInExternalPublicDir("/EBook_divine/", filename + ".pdf");
        }

        Log.d("bookNmae", bookName);
        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) _context.getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);

        new DownloadImage().execute(bookImage, id, bookName, bookAuthor, type);

    }

    public class DownloadImage extends AsyncTask<String, String, String> {

        private String id, bookName, bookAuthor, type;
        Bitmap bitmapDownload;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                id = params[1];
                bookName = params[2];
                bookAuthor = params[3];
                type = params[4];
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmapDownload = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                // Log exception
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            downloadImage(bitmapDownload, id, bookName, bookAuthor, type);

            super.onPostExecute(s);
        }

    }

    public void downloadImage(Bitmap bitmap, String id, String bookName, String bookAuthor, String type) {

        String filePath = null;

        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/EBook_divine/";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            String fname = "Image-" + id;
            filePath = sdIconStorageDir.toString() + "/" + fname + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            //choose another format if PNG doesn't suit you
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
        }
    }


}
