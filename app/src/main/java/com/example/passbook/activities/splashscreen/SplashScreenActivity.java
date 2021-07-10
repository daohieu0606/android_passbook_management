package com.example.passbook.activities.splashscreen;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;

import com.example.passbook.R;
import com.example.passbook.activities.base.BaseActivity;
import com.example.passbook.activities.main.MainActivity;

public class SplashScreenActivity extends BaseActivity implements SplashScreenContract.View {
    private SplashScreenContract.Presenter presenter;
    private ProgressBar progressBar;
    private TextView txtMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);
        txtMessage = findViewById(R.id.txtMessage);

        this.presenter = new SplashScreenPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setProgressBarColor();
        progressBar.setProgress(0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadingData();
    }

    @Override
    public void setLoadingProgress(int progress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress, true);
        } else {
            progressBar.setProgress(progress);
        }
    }

    @Override
    public void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        moveToAnotherActivity(intent);
    }

    @Override
    public void setMessage(String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtMessage.setText(message);
            }
        });
    }

    private void setProgressBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
        } else {
            Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
            progressDrawable.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
            progressBar.setProgressDrawable(progressDrawable);
        }
    }
}
