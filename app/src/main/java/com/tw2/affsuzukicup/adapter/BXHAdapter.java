package com.tw2.affsuzukicup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.model.BXH;
import com.tw2.affsuzukicup.model.LichThiDau;

import java.util.List;

public class BXHAdapter extends RecyclerView.Adapter<BXHAdapter.MyViewHolder> {
    private List<BXH> list;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStt, tvName, tvTran, tvHeSo, tvDiem;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            tvDiem = (TextView) v.findViewById(R.id.tv_diem);
            tvStt = (TextView) v.findViewById(R.id.tv_stt);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvTran = (TextView) v.findViewById(R.id.tv_tran);
            tvHeSo = (TextView) v.findViewById(R.id.tv_heso);
            imageView = (ImageView) v.findViewById(R.id.img_logo);
        }
    }

    public BXHAdapter(List<BXH> list) {
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BXHAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bxh, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BXH bxh = list.get(position);

        holder.tvHeSo.setText(bxh.getHeso());
        holder.tvStt.setText(bxh.getStt());
        holder.tvName.setText(bxh.getName());
        holder.tvTran.setText(bxh.getTran());
        holder.tvDiem.setText(bxh.getDiem());

        Picasso.get().load(bxh.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}