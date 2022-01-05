package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctruyen.database.databasedoctruyen;
import com.example.doctruyen.model.Truyen;

public class MainDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung,edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);

        edtAnh = findViewById(R.id.dbanh);
        edtNoiDung = findViewById(R.id.dbnoidung);
        edtTenTruyen = findViewById(R.id.dbTenTruyen);
        btnDangBai = findViewById(R.id.dbdangbai);

        databasedoctruyen = new databasedoctruyen(this);

        //butto đăng bài
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreatTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(MainDangBai.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("ERR :","Nhập đầy đủ thông tin");
                }
                //nếu nhập đầy đủ thông tin thì thực hiện thêm truyện
                else {
                    databasedoctruyen.AddTruyen(truyen);
                    //chuyển qua màn hình admin và cập nhật lại dữ liệu
                    Intent intent = new Intent(MainDangBai.this,MainAdmin.class);
                    finish();
                    startActivity(intent);
                }

            }
        });

    }
    //phương thức tạo truyện
    private Truyen CreatTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        Truyen truyen = new Truyen(tentruyen,noidung,img,id);
        return truyen;
    }
}