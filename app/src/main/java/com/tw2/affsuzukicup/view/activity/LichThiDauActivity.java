package com.tw2.affsuzukicup.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.adapter.BXHAdapter;
import com.tw2.affsuzukicup.adapter.ContainerKetQuaAdapter;
import com.tw2.affsuzukicup.model.BXH;
import com.tw2.affsuzukicup.model.ContainerKetQua;
import com.tw2.affsuzukicup.model.KetQua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LichThiDauActivity extends AppCompatActivity implements View.OnClickListener {
    private List<ContainerKetQua> list;
    private RecyclerView recyclerView;
    private ContainerKetQuaAdapter adapter;
    private DatabaseReference mReference;
    private AdView banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_thi_dau);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.color_status_bar));
        }
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initAds();
        initData();
    }

    private void initAds() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
        banner = (AdView) findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }

    private void initData() {
        list = new ArrayList<>();
        list.clear();

        mReference.child("lichthidau").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ContainerKetQua containerKetQua = dataSnapshot.getValue(ContainerKetQua.class);

                list.add(containerKetQua);
                adapter = new ContainerKetQuaAdapter(list, false);
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
        recyclerView = (RecyclerView) findViewById(R.id.recycle_container_ketqua);
        LinearLayoutManager layoutManager = new LinearLayoutManager(LichThiDauActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.btn_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                onBackPressed();
                break;
        }
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
