package com.example.passbook.activities.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.R;
import com.example.passbook.data.enums.LanguageType;
import com.example.passbook.data.enums.ThemeType;
import com.example.passbook.services.AppDatabase;
import com.example.passbook.services.ICurrentStateService;
import com.example.passbook.services.ServiceLocator;
import com.example.passbook.utils.ThemeHelper;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    private LanguageType currentLanguageType;
    protected AppDatabase appDatabase;
    protected BasePresenter presenter;
    protected ICurrentStateService currentStateService;

    protected static final String MyTag = "MyTag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(MyTag, "oncreate: " + this.getClass().getName());
        ThemeHelper.setTheme(this);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        presenter = new BasePresenter(this);
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        currentStateService = ServiceLocator.getInstance().getService(ICurrentStateService.class);

        currentLanguageType = currentStateService.getCurrentLanguageType();
    }

    @Override
    protected void onStart() {
        super.onStart();

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);

        if(!ThemeType.fromTextString(outValue.string.toString()).equals(currentStateService.getCurrentThemeType())) {
            ThemeHelper.reloadActivity(this);
            Log.i(MyTag, "have changed theme");
        }

        LanguageType newLanguageType = currentStateService.getCurrentLanguageType();

        if(!newLanguageType.equals(currentLanguageType)) {
            currentLanguageType = newLanguageType;

            ThemeHelper.reloadActivity(this);
            Log.i(MyTag, "have changed language");
        }

        Log.i(MyTag, "onstart: " + this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(MyTag, "onresume: " + this.getClass().getName());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        String language = ServiceLocator.getInstance()
                .getService(ICurrentStateService.class)
                .getCurrentLanguageType().toString();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    @Override
    public void moveToAnotherActivity(Intent intent) {
        if(intent != null){
            this.startActivity(intent);
        }
    }
}
