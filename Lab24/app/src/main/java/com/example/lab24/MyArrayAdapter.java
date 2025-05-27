package com.example.lab24;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Tygia> {
    Activity context;
    int resource;
    List<Tygia> objects;

    public MyArrayAdapter(Activity context, int resource, List<Tygia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            item = inflater.inflate(this.resource, parent, false);
        }

        Tygia tygia = this.objects.get(position);

        ImageView imgHinh = item.findViewById(R.id.imgPhoto);
        TextView txtType = item.findViewById(R.id.txtType);
        TextView txtMuaTM = item.findViewById(R.id.txtBuyCash);
        TextView txtBanTM = item.findViewById(R.id.txtSellCash);
        TextView txtMuaCK = item.findViewById(R.id.txtBuybyTransfer);
        TextView txtBanCK = item.findViewById(R.id.txtSellbyTransfer);

        // Set ảnh nếu có, nếu không dùng ảnh mặc định
        if (tygia.getBitmap() != null) {
            imgHinh.setImageBitmap(tygia.getBitmap());
        } else {
            imgHinh.setImageResource(R.drawable.ic_launcher_foreground);
        }

        txtType.setText(tygia.getType());
        txtMuaTM.setText(tygia.getMuatienmat());
        txtBanTM.setText(tygia.getBantienmat());
        txtMuaCK.setText(tygia.getMuack());
        txtBanCK.setText(tygia.getBanck());

        return item;
    }
}
