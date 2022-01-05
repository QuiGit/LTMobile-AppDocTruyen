package com.example.doctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctruyen.R;
import com.example.doctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int i) {
        return listTruyen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void filteredList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen, null);

        viewHolder.txtTenTruyen = convertView.findViewById(R.id.textviewTenTruyenNew);
        viewHolder.imgtruyen = convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(viewHolder);

        Truyen truyen = (Truyen) getItem(i);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgtruyen);

        return convertView;
    }
}
