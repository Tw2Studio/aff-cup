package com.tw2.affsuzukicup.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.adapter.ContainerKetQuaAdapter;
import com.tw2.affsuzukicup.model.ContainerKetQua;
import com.tw2.affsuzukicup.model.KetQua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KetQuaFragment extends Fragment {
    private View view;
    private List<ContainerKetQua> list;
    private RecyclerView recyclerView;
    private ContainerKetQuaAdapter adapter;
    private DatabaseReference mReference;
    private AdView banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ketqua, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initAds();
        initData();
        return view;
    }

    private void initAds() {
        MobileAds.initialize(getContext().getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
        banner = (AdView) view.findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }

    private void initData() {
        list = new ArrayList<>();
        list.clear();

        mReference.child("ketqua").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ContainerKetQua containerKetQua = dataSnapshot.getValue(ContainerKetQua.class);
                list.add(containerKetQua);

                Collections.sort(list, new Comparator<ContainerKetQua>() {
                    @Override
                    public int compare(final ContainerKetQua object1, final ContainerKetQua object2) {
                        return Integer.parseInt(object2.getNumber()) - Integer.parseInt(object1.getNumber());
                    }
                });

                adapter = new ContainerKetQuaAdapter(list, true);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initData();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_container_ketqua);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onPause() {
        if (banner != null) {
            banner.pause();
        }

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.resume();
        }

    }

    @Override
    public void onDestroy() {
        if (banner != null) {
            banner.destroy();
        }

        super.onDestroy();
    }
}
