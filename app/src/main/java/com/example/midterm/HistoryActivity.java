package com.example.midterm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

    private ListView historyListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);

        ArrayList<String> historyItems = getIntent().getStringArrayListExtra("history");
        if (historyItems == null) {
            historyItems = new ArrayList<>();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                historyItems
        );

        historyListView.setAdapter(adapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}