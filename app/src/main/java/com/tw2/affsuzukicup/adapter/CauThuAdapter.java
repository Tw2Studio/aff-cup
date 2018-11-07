package com.tw2.affsuzukicup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.model.CauThu;
import com.tw2.affsuzukicup.model.LichThiDau;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CauThuAdapter extends RecyclerView.Adapter<CauThuAdapter.MyViewHolder> {
    private List<CauThu> list;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageView;
        public TextView tvName, tvVitri;

        public MyViewHolder(View v) {
            super(v);
            imageView = (CircleImageView) v.findViewById(R.id.img_image);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvVitri = (TextView) v.findViewById(R.id.tv_vitri);
        }
    }

    public CauThuAdapter(List<CauThu> list) {
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CauThuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cauthu, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CauThu cauThu = list.get(position);

        Picasso.get().load(cauThu.getImage()).into(holder.imageView);
        holder.tvName.setText(cauThu.getName());
        holder.tvVitri.setText(cauThu.getVitri());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}