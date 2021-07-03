package com.example.passbook.activities.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.utils.ThemeExtension;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    protected AppDatabase appDatabase;
    protected BasePresenter presenter;
    protected ICurrentStateService currentStateService;

    protected static final String MyTag = "MyTag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(MyTag, "oncreate: " + this.getClass().getName());
        ThemeExtension.setTheme(this);
        super.onCreate(savedInstanceState);

        presenter = new BasePresenter(this);
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        currentStateService = ServiceLocator.getInstance().getService(ICurrentStateService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);

        if(!ThemeType.fromTextString(outValue.string.toString()).equals(currentStateService.getCurrentThemeType())) {
            ThemeExtension.reloadActivity(this);
            Log.i(MyTag, "have changed theme");
        }

        Log.i(MyTag, "onstart: " + this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(MyTag, "onresume: " + this.getClass().getName());
    }

    @Override
    public void moveToAnotherActivity(Intent intent) {
        if(intent != null){
            this.startActivity(intent);
        }
    }
}
