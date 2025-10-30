package com.example.midterm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText inputNumber;
    private Button generateButton, historyButton;
    private Button clearButton;
    private ListView tableListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tableItems = new ArrayList<>();
    private ArrayList<String> historyItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        generateButton = findViewById(R.id.generateButton);
        historyButton = findViewById(R.id.historyButton);
        tableListView = findViewById(R.id.tableListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableItems);
        tableListView.setAdapter(adapter);

        generateButton.setOnClickListener(v -> {
            String input = inputNumber.getText().toString();
            if (!input.isEmpty()) {
                int number = Integer.parseInt(input);
                tableItems.clear();
                for (int i = 1; i <= 10; i++) {
                    String row = number + " × " + i + " = " + (number * i);
                    tableItems.add(row);
                    historyItems.add(row);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            }
        });

        tableListView.setOnItemClickListener((parent, view, position, id) -> {
            String item = tableItems.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Delete Row")
                    .setMessage("Delete \"" + item + "\"?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        tableItems.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Deleted: " + item, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putStringArrayListExtra("history", historyItems);
            startActivity(intent);
        });

        // Clear all button
        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyItems.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "History cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}