package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.Cursor;
import android.os.Bundle;

import com.example.passbook.R;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.services.AppDatabase;
import com.levitnudi.legacytableview.LegacyTableView;

public class TestLibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_test_lib);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        LegacyTableView.insertLegacyTitle("Id", "Creation date", "Type", "Amount");

        for (PassBook passbook:
             db.passBookDAO().getItems()) {
            LegacyTableView.insertLegacyContent(String.valueOf(passbook.Id));
            LegacyTableView.insertLegacyContent(passbook.creationDate.toString());
            LegacyTableView.insertLegacyContent(passbook.passBookType.getText());
            LegacyTableView.insertLegacyContent(String.valueOf(passbook.amount));
        }

        LegacyTableView legacyTableView = (LegacyTableView)findViewById(R.id.legacy_table_view);
        legacyTableView.setTitle(LegacyTableView.readLegacyTitle());
        legacyTableView.setContent(LegacyTableView.readLegacyContent());

        //depending on the phone screen size default table scale is 100
        //you can change it using this method
        //legacyTableView.setInitialScale(100);//default initialScale is zero (0)

        //if you want a smaller table, change the padding setting
        legacyTableView.setTablePadding(7);

        //to enable users to zoom in and out:
        legacyTableView.setZoomEnabled(true);
        legacyTableView.setShowZoomControls(true);

        //remember to build your table as the last step
        legacyTableView.build();
    }
}