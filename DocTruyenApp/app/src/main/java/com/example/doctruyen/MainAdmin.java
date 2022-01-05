package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doctruyen.adapter.adapterTruyen;
import com.example.doctruyen.database.databasedoctruyen;
import com.example.doctruyen.model.Truyen;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonThemTruyen);

        initList();
        //bắt sự kiện
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lấy id tài khoản để biết admin nào đã vào chỉnh sửa
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id",0);
                //tiếp tục gửi id qua màn hình thêm truyện
                Intent intent = new Intent(MainAdmin.this,MainDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent);

            }
        });
        //click item long sẽ xóa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                DialogDelete(position);
                return false;
            }
        });
    }

    //phương thức Dialog hiển thị cửa sổ xóa
    private void DialogDelete(int position){

        //tạo đối tượng dialog
        Dialog dialog = new Dialog(this);
        //nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdelete);
        //tắt click ra ngoài là đóng, chỉ click no mới đóng
        dialog.setCanceledOnTouchOutside(false);

        //ánh xạ
        Button btnyes = dialog.findViewById(R.id.buttonyes);
        Button btnno = dialog.findViewById(R.id.buttonno);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtruyen = TruyenArrayList.get(position).getID();
                //xóa dữ liệu
                databasedoctruyen.Delete(idtruyen);
                //cập nhật lại Activity
                Intent intent = new Intent(MainAdmin.this,MainAdmin.class);
                finish();;
                startActivity(intent);
                Toast.makeText(MainAdmin.this,"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    //gán dữ liệu cho listView
    private void initList() {
        TruyenArrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor1 = databasedoctruyen.getData2();

        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}