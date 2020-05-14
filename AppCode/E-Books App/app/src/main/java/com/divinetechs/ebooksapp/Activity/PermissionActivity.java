package com.divinetechs.ebooksapp.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;

public class PermissionActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int REQUEST_WRITE_PERMISSION = 11;
    private static final int REQUEST_READ_PERMISSION = 111;
    private CardView card_view_allow_permission;
    private PrefManager prefManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

//                    Intent intent_status  =  new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent_status);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }
                return;
            }
            case REQUEST_READ_PERMISSION : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

//                    Intent intent_status  =  new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent_status);
//                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        PrefManager.forceRTLIfSupported(getWindow(), PermissionActivity.this);

        prefManager= new PrefManager(getApplicationContext());

        this.card_view_allow_permission=(CardView) findViewById(R.id.card_view_allow_permission);
        this.card_view_allow_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(PermissionActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);
        }
    }



}
