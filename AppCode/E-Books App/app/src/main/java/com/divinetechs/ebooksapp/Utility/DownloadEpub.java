package com.divinetechs.ebooksapp.Utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.divinetechs.ebooksapp.R;
import com.folioreader.FolioReader;
import com.folioreader.model.HighLight;
import com.folioreader.util.OnHighlightListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadEpub {

    private Activity activity;

    public DownloadEpub(Activity activity) {
        this.activity = activity;
    }

    public void pathEpub(String path, String id) {
        // declare the dialog as a member field of your activity
        new DownloadTaskEpub().execute(path, id);
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadTaskEpub extends AsyncTask<String, Integer, String> implements OnHighlightListener {

        private ProgressDialog progressDialog;
        private String iconsStoragePath;
        private File sdIconStorageDir;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(activity.getResources().getString(R.string.please_wait));
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {

        }

        @Override
        protected String doInBackground(String... stringsUrl) {
            Log.e("url_data_ar", "" + stringsUrl[0]);
            int count;
            try {
                URL url = new URL(stringsUrl[0]);
                Log.e("url_data", "" + url);
                Log.e("url_data_id", "" + stringsUrl[1]);
                String id = stringsUrl[1];
                iconsStoragePath = activity.getExternalCacheDir().getAbsolutePath();
                String filePath = "file" + id + ".epub";
                sdIconStorageDir = new File(iconsStoragePath, filePath);

                //create storage directories, if they don't exist
                if (sdIconStorageDir.exists()) {

                } else {
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    // getting file length
                    int lenghtOfFile = conection.getContentLength();
                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    // Output stream to write file
                    OutputStream output = new FileOutputStream(sdIconStorageDir);
                    byte data[] = new byte[1024];
                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush(); // flushing output
                    output.close();// closing streams
                    input.close();
                }

            } catch (Exception e) {
                Log.e("Error:-Download-epub", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {

            progressDialog.hide();
            try {
                Log.e("file_url", "" + file_url);
                Log.e("sdIconStorageDir", "" + sdIconStorageDir.toString());

                if (sdIconStorageDir.toString() != null) {
                    FolioReader folioReader = FolioReader.get();
                    folioReader.setOnHighlightListener(new OnHighlightListener() {
                        @Override
                        public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {
                        }
                    });
                    folioReader.openBook(sdIconStorageDir.toString());
                }else{
                    Toast.makeText(activity,"This Book not available",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Exception-down", "" + e.getMessage());
            }
        }
    }
}
