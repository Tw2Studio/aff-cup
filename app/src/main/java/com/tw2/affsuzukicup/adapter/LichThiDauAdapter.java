package com.tw2.affsuzukicup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.model.KetQua;
import com.tw2.affsuzukicup.model.LichThiDau;

import java.util.List;

public class LichThiDauAdapter extends RecyclerView.Adapter<LichThiDauAdapter.MyViewHolder> {
    private List<KetQua> list;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameA, tvNameB, tvTime;
        public ImageView imageViewA, imageViewB;
        public MyViewHolder(View v) {
            super(v);
            tvNameA = (TextView) v.findViewById(R.id.tv_name_a);
            tvNameB = (TextView) v.findViewById(R.id.tv_name_b);
            tvTime = (TextView) v.findViewById(R.id.tv_time);
            imageViewA = (ImageView) v.findViewById(R.id.image_a);
            imageViewB = (ImageView) v.findViewById(R.id.image_b);
        }
    }

    public LichThiDauAdapter(List<KetQua> list) {
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LichThiDauAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lich_thi_dau, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        KetQua ketQua = list.get(position);

        holder.tvNameA.setText(ketQua.getNameA());
        holder.tvNameB.setText(ketQua.getNameB());
        holder.tvTime.setText(ketQua.getTime());

        if (!ketQua.getImageA().equals("") && !ketQua.getImageB().equals("")) {
            Picasso.get().load(ketQua.getImageA()).into(holder.imageViewA);
            Picasso.get().load(ketQua.getImageB()).into(holder.imageViewB);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}