package com.divinetechs.ebooksapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.divinetechs.ebooksapp.Model.LoginRegister.LoginRegiModel;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.divinetechs.ebooksapp.Webservice.AppAPI;
import com.divinetechs.ebooksapp.Webservice.BaseURL;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class Registration extends AppCompatActivity {

    String str_fullname, str_email, str_password, str_phone;
    ProgressDialog progressDialog;
    private PrefManager prefManager;
    TextView txt_registration, txt_signup;
    EditText et_fullname, et_email, et_password, et_phone;
    ImageView iv_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registration);
        PrefManager.forceRTLIfSupported(getWindow(), Registration.this);

        Init();

        Picasso.with(Registration.this).load(BaseURL.Image_URL + "" + prefManager.getValue("app_logo"))
                .priority(HIGH).into(iv_icon);

        txt_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        Registration.this, LoginActivity.class));
            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_fullname = et_fullname.getText().toString();
                str_email = et_email.getText().toString();
                str_password = et_password.getText().toString();
                str_phone = et_phone.getText().toString();

                if (TextUtils.isEmpty(str_fullname)) {
                    Toast.makeText(Registration.this, "Enter FullName", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(str_email)) {
                    Toast.makeText(Registration.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(str_password)) {
                    Toast.makeText(Registration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(str_phone)) {
                    Toast.makeText(Registration.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                SignUp();
            }
        });
    }


    public void Init() {
        prefManager = new PrefManager(this);
        progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        txt_registration = (TextView) findViewById(R.id.txt_registration);
        et_fullname = (EditText) findViewById(R.id.et_fullname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phone = (EditText) findViewById(R.id.et_phone);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        iv_icon=findViewById(R.id.iv_icon);
    }

    public void SignUp() {
        progressDialog.show();
        AppAPI bookNPlayAPI = BaseURL.getVideoAPI();
        Call<LoginRegiModel> call = bookNPlayAPI.Registration(str_fullname, str_email, str_password, str_phone);
        call.enqueue(new Callback<LoginRegiModel>() {
            @Override
            public void onResponse(Call<LoginRegiModel> call, Response<LoginRegiModel> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Toast.makeText(Registration.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getStatus() == 200) {
                        prefManager.setLoginId("" + response.body().getUserid());
                        startActivity(new Intent(Registration.this, MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRegiModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
