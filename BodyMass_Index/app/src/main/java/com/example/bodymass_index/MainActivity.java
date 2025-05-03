package com.example.bodymass_index;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnChuanDoan;
    EditText edtTen, edtChieuCao, edtCanNang, edtBMI, edtChuanDoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChuanDoan = findViewById(R.id.btnBMI);
        edtTen = findViewById(R.id.edtTen);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtBMI = findViewById(R.id.edtBMI);
        edtChuanDoan = findViewById(R.id.edtChuanDoan);
        btnChuanDoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtChieuCao.getText()+"");
                double W = Double.parseDouble(edtCanNang.getText()+"");
                double BMI = W/Math.pow(H,2);
                String chuandoan = "";
                if(BMI<18){
                    chuandoan="Bạn gầy";
                }else if(BMI<=24.9){
                    chuandoan="Bạn bình thường";
                }else if(BMI<=29.9){
                    chuandoan="Bạn béo phì độ 1";
                }else if(BMI<=34.9){
                    chuandoan="Bạn béo phì độ 2";
                }else{
                    chuandoan="Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtChuanDoan.setText(chuandoan);
            }
        });
    }
}