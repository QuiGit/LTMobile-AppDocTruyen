package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongTinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongTinapp = findViewById(R.id.textviewthongtin);

        String thongtin = " Ứng dụng này được phát hành bởi 'Duy Lada'\n"+
                " Uy tín đạt 100%\n"+" Ok";

        txtThongTinapp.setText(thongtin);
    }
}