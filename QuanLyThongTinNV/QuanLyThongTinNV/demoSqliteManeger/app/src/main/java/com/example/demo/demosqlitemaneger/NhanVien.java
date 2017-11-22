package com.example.demo.demosqlitemaneger;

/**
 * Created by ADMIN on 5/15/2017.
 */

public class NhanVien {
    public int id;
    public String ten;
    public String sdt;
    public byte[] anh;

    public NhanVien(int id, String ten, String sdt, byte[] anh) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.anh = anh;
    }
}
