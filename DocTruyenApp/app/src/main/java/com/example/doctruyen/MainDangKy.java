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
import com.example.doctruyen.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {

    EditText editDKTaiKhoan, editDKMatKhau, editDKEmail;
    Button btnDKDangNhap, btnDKDangky;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        Anhxa();

        databasedoctruyen = new databasedoctruyen(this);

        //click cho button đăng ký
        btnDKDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = editDKTaiKhoan.getText().toString();
                String matkhau = editDKMatKhau.getText().toString();
                String email = editDKEmail.getText().toString();

                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Log.e("Thông báo : ","Chưa nhập đầy đủ thông tin");
                }
                else {

                    //nếu đầy đủ thông tin nhập vào thì add tài khoản vào database
                    databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });
        //trở về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = editDKTaiKhoan.getText().toString();
        String matkhau = editDKMatKhau.getText().toString();
        String email = editDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, email, phanquyen);

        return tk;
    }

    private void Anhxa() {
        editDKEmail = findViewById(R.id.edit_dkemail);
        editDKMatKhau = findViewById(R.id.edit_dkmatkhau);
        editDKTaiKhoan = findViewById(R.id.edit_dktaikhoan);
        btnDKDangky = findViewById(R.id.button_dkdangky);
        btnDKDangNhap = findViewById(R.id.button_trove);
    }
}