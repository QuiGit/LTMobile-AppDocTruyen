package com.example.doctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctruyen.R;
import com.example.doctruyen.model.ChuyenMuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ChuyenMuc> chuyenMucList;

    public adapterchuyenmuc(Context context, int layout, List<ChuyenMuc> chuyenMucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenMucList = chuyenMucList;
    }

    @Override
    public int getCount() {
        return chuyenMucList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgChuyenMuc);

        TextView txt = (TextView) convertView.findViewById(R.id.textviewTenchuyenmuc);

        ChuyenMuc cm = chuyenMucList.get(i);

        txt.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);

        return convertView;
    }
}
