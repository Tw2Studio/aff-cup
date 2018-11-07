package com.tw2.affsuzukicup.adapter;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.model.ContainerKetQua;
import com.tw2.affsuzukicup.model.KetQua;

import java.util.List;

public class ContainerKetQuaAdapter extends RecyclerView.Adapter<ContainerKetQuaAdapter.MyViewHolder> {
    private List<ContainerKetQua> list;
    private boolean isKetQua;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public RecyclerView recyclerView;
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            view = v;
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_child_ketqua);
            LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    layoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);

            textView = (TextView) view.findViewById(R.id.txt_title);
        }
    }

    public ContainerKetQuaAdapter(List<ContainerKetQua> list, boolean isKetQua) {
        this.list = list;
        this.isKetQua = isKetQua;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContainerKetQuaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_ketqua, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ContainerKetQua containerKetQua = list.get(position);
        List<KetQua> ketQuaList = containerKetQua.getTran();

        for (int i = 0; i<ketQuaList.size();i++){
            if (ketQuaList.get(i)==null){
                ketQuaList.remove(i);
            }
        }

        ChildKetQuaAdapter adapter = new ChildKetQuaAdapter(ketQuaList, isKetQua);
        holder.recyclerView.setAdapter(adapter);
        holder.textView.setText(containerKetQua.getNgay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}