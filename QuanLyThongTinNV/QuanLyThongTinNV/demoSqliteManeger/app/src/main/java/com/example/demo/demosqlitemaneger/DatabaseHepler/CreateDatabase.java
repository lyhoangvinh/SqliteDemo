package com.example.demo.demosqlitemaneger.DatabaseHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ADMIN on 11/23/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_NHANVIEN = "NhanVien";
    public static String TB_NHANVIEN_ID = "Id";
    public static String TB_NHANVIEN_TEN = "Ten";
    public static String TB_NHANVIEN_SDT = "SDT";
    public static String TB_NHANVIEN_ANH = "ANH";

    public CreateDatabase(Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNhanVien = "CREATE TABLE " + TB_NHANVIEN + " ( "+ TB_NHANVIEN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TB_NHANVIEN_TEN + " TEXT NOT NULL, " + TB_NHANVIEN_SDT + " TEXT NOT NULL, " + TB_NHANVIEN_ANH + " BLOB)";
        db.execSQL(tbNhanVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
