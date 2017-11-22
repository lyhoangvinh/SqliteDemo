package com.example.demo.demosqlitemaneger;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Tạo folder assets bằng cách chuột pãi java-> New-> Folder-> AssetsFolder
    final String DATABASE_NAME = "EmployeeDB.sqlite";
    SQLiteDatabase database;
    ListView myListView;
    ArrayList<NhanVien> arrayList;
    AdapterNhanVien adapterNhanVien;
    Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readData();
    }
    private void addControls(){
        myListView =(ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapterNhanVien = new AdapterNhanVien(this, arrayList);
        myListView.setAdapter(adapterNhanVien);
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);
        arrayList.clear();
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            arrayList.add(new NhanVien(id, ten, sdt, anh));
        }
        adapterNhanVien.notifyDataSetChanged();
    }
}
