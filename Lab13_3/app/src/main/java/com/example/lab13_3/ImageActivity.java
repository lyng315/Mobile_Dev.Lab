package com.example.lab13_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = findViewById(R.id.imageView);
        Button backButton = findViewById(R.id.backButton);

        int imageId = getIntent().getIntExtra("imageId", -1);
        if (imageId != -1) {
            imageView.setImageResource(imageId);
        }

        backButton.setOnClickListener(v -> finish());
    }
}