package com.example.savingsbookmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import com.example.savingsbookmanagement.R;
import com.example.savingsbookmanagement.adapters.MainFuncAdapter;
import com.example.savingsbookmanagement.models.MainFuncModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMainFunction;
    private MainFuncAdapter adapter;
    private List<MainFuncModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init() {
        items = new ArrayList<>();
        rvMainFunction = findViewById(R.id.rvMainFunction);
        adapter = new MainFuncAdapter(getApplicationContext(), items);
        rvMainFunction.setAdapter(adapter);
        rvMainFunction.setLayoutManager(new GridLayoutManager(this, 3));

        items.add(new MainFuncModel("fsdf", "function 1"));
        items.add(new MainFuncModel("fsdf", "function 2"));
        items.add(new MainFuncModel("fsdf", "function 3"));
        items.add(new MainFuncModel("fsdf", "function 4"));
        items.add(new MainFuncModel("fsdf", "function 5"));
        items.add(new MainFuncModel("fsdf", "function 6"));

        adapter.notifyDataSetChanged();
    }
}