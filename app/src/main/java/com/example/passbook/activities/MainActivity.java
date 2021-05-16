package com.example.passbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.passbook.R;
import com.example.passbook.adapters.SpacesItemDecoration;
import com.example.passbook.adapters.MainFuncAdapter;
import com.example.passbook.models.MainFuncModel;
import com.example.passbook.utils.Constant;

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

        loadDataToAdapter();

        adapter.setOnItemClick(new MainFuncAdapter.OnItemAdapterClickListener() {
            @Override
            public void OnItemClicked(MainFuncModel item) {
                handleItemClick(item);
            }
        });

        rvMainFunction.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvMainFunction.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        rvMainFunction.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void handleItemClick(MainFuncModel item) {
        Intent intent = new Intent(MainActivity.this, TestForm.class);
        intent.putExtra(Constant.layout, R.layout.activity_test_form);
        intent.putExtra(Constant.title, "test app");
        startActivity(intent);
    }

    private void loadDataToAdapter() {
        items.add(new MainFuncModel("fsdf", "function 1"));
        items.add(new MainFuncModel("fsdf", "function 2"));
        items.add(new MainFuncModel("fsdf", "function 3"));
        items.add(new MainFuncModel("fsdf", "function 4"));
        items.add(new MainFuncModel("fsdf", "function 5"));
        items.add(new MainFuncModel("fsdf", "function 6"));
    }
}