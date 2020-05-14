package com.divinetechs.ebooksapp.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.divinetechs.ebooksapp.Activity.AboutUs;
import com.divinetechs.ebooksapp.Activity.DownloadedBooks;
import com.divinetechs.ebooksapp.Activity.LoginActivity;
import com.divinetechs.ebooksapp.Activity.MainActivity;
import com.divinetechs.ebooksapp.Activity.MyDownloadBooks;
import com.divinetechs.ebooksapp.Activity.Privacypolicy;
import com.divinetechs.ebooksapp.Activity.Profile;
import com.divinetechs.ebooksapp.BuildConfig;
import com.divinetechs.ebooksapp.R;
import com.divinetechs.ebooksapp.Utility.LocaleUtils;
import com.divinetechs.ebooksapp.Utility.PrefManager;
import com.onesignal.OneSignal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Settings extends Fragment {
    SwitchCompat switch_push, switch_theme;
    PrefManager prefManager;
    Spinner spinner;
    TextView txt_profile, txt_my_download_book, txt_about_us, txt_share_app, txt_rate_app, txt_login, txt_privacy_policy,txt_my_downloaded_book;
    String currentLanguage = "en", currentLang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            getActivity().setTheme(R.style.darktheme);
        } else {
            getActivity().setTheme(R.style.AppTheme);
        }
        View root = inflater.inflate(R.layout.settings, container, false);


        prefManager = new PrefManager(getActivity());
        spinner = root.findViewById(R.id.spinner);
        switch_push = (SwitchCompat) root.findViewById(R.id.switch_push);
        switch_theme = (SwitchCompat) root.findViewById(R.id.switch_theme);
        ImageView iv_clear = (ImageView) root.findViewById(R.id.iv_clear);

        txt_profile = (TextView) root.findViewById(R.id.txt_profile);
        txt_my_download_book = (TextView) root.findViewById(R.id.txt_my_download_book);
        txt_my_downloaded_book = (TextView) root.findViewById(R.id.txt_my_downloaded_book);
        txt_about_us = root.findViewById(R.id.txt_about_us);
        txt_share_app = (TextView) root.findViewById(R.id.txt_share_app);
        txt_rate_app = (TextView) root.findViewById(R.id.txt_rate_app);
        txt_login = (TextView) root.findViewById(R.id.txt_login);
        txt_privacy_policy = root.findViewById(R.id.txt_privacy_policy);

        if (prefManager.getBool("PUSH")) {
            switch_push.setChecked(true);
        } else {
            switch_push.setChecked(false);
        }

        txt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.getLoginId().equalsIgnoreCase("0")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), Profile.class));
                }
            }
        });
        txt_my_download_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.getLoginId().equalsIgnoreCase("0")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MyDownloadBooks.class));
                }
            }
        });

        txt_my_downloaded_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prefManager.getLoginId().equalsIgnoreCase("0")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), DownloadedBooks.class));
                }
            }
        });

        switch_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    OneSignal.setSubscription(true);
                } else {
                    OneSignal.setSubscription(false);
                }
                prefManager.setBool("PUSH", isChecked);
            }
        });

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switch_theme.setChecked(true);
        }

        switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // prefManager.setIsNightModeEnabled(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    //prefManager.setIsNightModeEnabled(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
       /* switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prefManager.setIsNightModeEnabled(true);
                    Intent intent = getActivity().getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getActivity().finish();
                    startActivity(intent);

                } else {
                    prefManager.setIsNightModeEnabled(false);
                    Intent intent = getActivity().getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getActivity().finish();
                    startActivity(intent);
                }

            }
        });*/

        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String root = getActivity().getExternalCacheDir().getAbsolutePath();
                File file = new File(root);
                if (file.isDirectory()) {
                    String[] children = file.list();
                    for (String aChildren : children) {
                        new File(file, aChildren).delete();
                    }
                    Toast.makeText(getActivity(), getResources().getString(R.string.locally_cached_data), Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Privacypolicy.class));
            }
        });
        txt_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUs.class));
            }
        });

        txt_share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "" + getResources().getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        txt_rate_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
            }
        });

        if (!prefManager.getLoginId().equalsIgnoreCase("0"))
            // txt_login.setText("Logout");
            txt_login.setText(getActivity().getResources().getString(R.string.logout));
        else
            //txt_login.setText("Login");
            txt_login.setText(getActivity().getResources().getString(R.string.login));

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.getLoginId().equalsIgnoreCase("0")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    logout();
                }
            }
        });

        spinner_onclick();
        currentLanguage = prefManager.getValue("select_language");
        Log.e("lan_currentLan", "" + currentLanguage);

//        currentLanguage = prefManager.getValue("select_language");
        currentLanguage = LocaleUtils.getSelectedLanguageId();
        Log.e("currentLanguage", "" + currentLanguage);
        return root;
    }

    private void spinner_onclick() {
        List<String> list = new ArrayList<String>();
        list.add("English");
        list.add("Arabic");
        list.add("French");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (LocaleUtils.getSelectedLanguageId().equalsIgnoreCase("en")) {
            Log.e("selected_eng", "english");
            spinner.setSelection(0);
        }
        if (LocaleUtils.getSelectedLanguageId().equalsIgnoreCase("ar")) {
            Log.e("select_Arabic", "Arabic");
            spinner.setSelection(1);
        }
        if (LocaleUtils.getSelectedLanguageId().equalsIgnoreCase("fr")) {
            Log.e("select_Franch", "fr");
            spinner.setSelection(2);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Log.e("pos", "" + position);
                switch (position) {
                    case 0:
                        setLocale("en");
                        break;
                    case 1:
                        setLocale("ar");
                        break;
                    case 2:
                        setLocale("fr");
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setLocale(String localeName) {
        try {
            Log.e("lan_name", "" + localeName);
            Log.e("currentLanguage2", "" + currentLanguage);
            if (!localeName.equals(currentLanguage)) {
                LocaleUtils.setSelectedLanguageId(localeName);
                Intent i = getActivity().getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                startActivity(i);
                getActivity().finish();
            } else {
//                Toast.makeText(getActivity(), "Language already selected!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("error_msg", "" + e.getMessage());
        }
    }


    public void logout() {
        new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogDanger))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        prefManager.setLoginId("0");
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
