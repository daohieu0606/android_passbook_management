package com.example.passbook.activities.about;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.passbook.BuildConfig;
import com.example.passbook.R;
import com.example.passbook.activities.base.TabBarActivity;

public class AboutActivity extends TabBarActivity implements AboutContract.View {
    private AboutContract.Presenter presenter;

    private TextView txtProductName;
    private TextView txtVersion;
    private TextView txtMinAndroidVersion;
    private TextView txtSourceCode;
    private TextView txtDescription;
    private TextView txtAuthorName;
    private TextView txtEmail;
    private TextView txtGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        title = getResources().getString(R.string.about);
        containerLayout = R.layout.activity_about;
        presenter = new AboutPresenter(this);

        super.onCreate(savedInstanceState);

        txtProductName = findViewById(R.id.txtProductName);
        txtVersion = findViewById(R.id.txtVersion);
        txtMinAndroidVersion = findViewById(R.id.txtMinAndroidVersion);
        txtSourceCode = findViewById(R.id.txtSourceCode);
        txtDescription = findViewById(R.id.txtDescription);

        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtEmail = findViewById(R.id.txtEmail);
        txtGithub = findViewById(R.id.txtGithub);
    }

    @Override
    protected void onStart() {
        super.onStart();

        txtProductName.setText(getResources().getString(R.string.app_name));

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            txtVersion.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        txtMinAndroidVersion.setText("5.0");
        txtSourceCode.setText("https://github.com/daohieu0606/passbook_management");
        txtDescription.setText(getResources().getString(R.string.application_for_managing_passbooks));

        txtAuthorName.setText("Dao Van Hieu");
        txtEmail.setText("damryo06@gmail.com");
        txtGithub.setText("https://github.com/daohieu0606");
    }
}
