package com.example.savingsbookmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.savingsbookmanagement.R;
import com.example.savingsbookmanagement.adapters.SpacesItemDecoration;
import com.example.savingsbookmanagement.adapters.MainFuncAdapter;
import com.example.savingsbookmanagement.models.MainFuncModel;

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

        adapter.setOnItemClick(new MainFuncAdapter.OnItemAdapterClickListener() {
            @Override
            public void OnItemClicked(MainFuncModel item) {
                Log.i("MainActivity", item.name);
            }
        });

        rvMainFunction.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvMainFunction.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

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