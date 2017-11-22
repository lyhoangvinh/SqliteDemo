package com.example.demo.demosqlitemaneger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ADMIN on 5/15/2017.
 */

public class AdapterNhanVien extends BaseAdapter {
    Activity context;
    ArrayList<NhanVien> arrayList;

    public AdapterNhanVien(Activity context, ArrayList<NhanVien> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgHinh;
        TextView txtId, txtTen, txtSdt;
        Button btnSua, btnXoa;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.dong_nhanvien, null);
            holder = new ViewHolder();
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.txtId = (TextView) view.findViewById(R.id.textViewID);
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtSdt = (TextView) view.findViewById(R.id.textViewSdt);
            holder.btnSua = (Button) view.findViewById(R.id.buttonSua);
            holder.btnXoa = (Button) view.findViewById(R.id.buttonXoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(arrayList.get(position).anh, 0, arrayList.get(position).anh.length);
        holder.imgHinh.setImageBitmap(bmHinhDaiDien);
        holder.txtId.setText(arrayList.get(position).id + "");
        holder.txtTen.setText(arrayList.get(position).ten);
        holder.txtSdt.setText(arrayList.get(position).sdt);
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("ID", arrayList.get(position).id);
                context.startActivity(intent);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setCancelable(false);
                builder.setTitle("Xác Nhận Xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(arrayList.get(position).id);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

    private void delete(int idNhanVien) {
        SQLiteDatabase database = Database.initDatabase(context, "EmployeeDB.sqlite");
        database.delete("NhanVien", "Id = ?", new String[]{idNhanVien + ""});
        arrayList.clear();
        Cursor curor = database.rawQuery("SELECT * FROM NhanVien", null);
        while (curor.moveToNext()){
            int id = curor.getInt(0);
            String ten = curor.getString(1);
            String sdt = curor.getString(2);
            byte[] anh = curor.getBlob(3);

           arrayList.add(new NhanVien(id, ten, sdt, anh));
           // arrayList.add(new NhanVien(curor.getInt(0), curor.getString(1), curor.getString(2), curor.getBlob(3)));
        }
        notifyDataSetChanged();
    }
}
