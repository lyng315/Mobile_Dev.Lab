package com.example.lab13_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int[] imageIds = {
            R.drawable.lyng, R.drawable.z2994232169514_cb1fea27087adec45e530f0c9893cfe4, R.drawable.z2994232170972_68ab39eab32090556385f4b63cbcfd26,
            R.drawable.z2994233628576_16033f842421c113f3adae510d31d124, R.drawable.z2994233630354_26823b590a06fad27b346e0caf6eb430, R.drawable.z3141430438178_122f27cd482e24596af46db28222898d, R.drawable.z3141430438178_122f27cd482e24596af46db28222898d, R.drawable.z3141430863680_827777a740c4660a8f05ed5b7ae7f0ed, R.drawable.z3141430863680_827777a740c4660a8f05ed5b7ae7f0ed, R.drawable.z3438404483877_6ea72546c8a99e4f7e0806238ff2ce04
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this, imageIds));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra("imageId", imageIds[position]);
                startActivity(intent);
            }
        });
    }
}