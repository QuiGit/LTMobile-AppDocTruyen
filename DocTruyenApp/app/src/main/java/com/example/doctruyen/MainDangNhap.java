package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctruyen.database.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    //tạo biến cho màn hình đăng nhập
    EditText edtTaiKhoan, editMatKhau;
    Button btnDangNhap, btnDangKy;

    //để tạo đối tượng cho databasedoctruyen
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        Anhxa();

        //đối tượng databasedoctruyen
        databasedoctruyen = new databasedoctruyen(this);

        //tạo sự kiện click button chuyển sang màn hình đăng ký vs Intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gán các biến là giá trị nhập vào từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();

                //sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lấy tất cả tài khoản ở database
                Cursor cursor = databasedoctruyen.getData();

                //thực hiện vòng lập để lấy dữ liệu từ cursor với moviToNext() di chuyển tiếp
                while (cursor.moveToNext()){
                    //lấy dữ liệu và gán vào biến, dữ liệu tài khoản ở ô 1 vá mật khẩu ở ô 0 là idtaikhoan, ô 3 là email và 4 là phanquyen
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    //nếu tài khoản và mật khẩu nhập vào từ bàn phím khớp với ở database
                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        //lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        //chuyển qua màn hình MainActivity
                        Intent intent = new Intent(MainDangNhap.this, MainActivity.class);

                        //gửi dữ liệu qua Activity vá MainActivity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                        Toast.makeText(MainDangNhap.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    }

                }
                //thực hiện trả curson về đầu
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();
            }
        });
    }

    private void Anhxa() {

        editMatKhau = findViewById(R.id.edit_matkhau);
        edtTaiKhoan = findViewById(R.id.edit_taikhoan);
        btnDangKy = findViewById(R.id.button_dangky);
        btnDangNhap = findViewById(R.id.button_dangnhap);
    }
}