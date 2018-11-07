package com.tw2.affsuzukicup.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.tw2.affsuzukicup.adapter.CauThuAdapter;
import com.tw2.affsuzukicup.model.BXH;
import com.tw2.affsuzukicup.model.CauThu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BangXepHangFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView recyclerView;
    private List<BXH> list;
    private BXHAdapter adapter;
    private TextView tvBangA, tvBangB;
    private DatabaseReference mReference;
    private AdView banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bangxephang, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initAds();
        initData("banga");
        return view;
    }

    private void initAds() {
        MobileAds.initialize(getContext().getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
        banner = (AdView) view.findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }

    private void initData(final String bang) {
        list = new ArrayList<>();
        list.clear();

        mReference.child("bangxephang").child(bang).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BXH bxh = dataSnapshot.getValue(BXH.class);
                list.add(bxh);

                if (list.size() == 5) {
                    Collections.sort(list, new Comparator<BXH>() {
                        @Override
                        public int compare(final BXH object1, final BXH object2) {
                            return Integer.parseInt(object1.getStt()) - Integer.parseInt(object2.getStt());
                        }
                    });

                    adapter = new BXHAdapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initData(bang);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_bxh);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        tvBangA = (TextView) view.findViewById(R.id.txt_bang_a);
        tvBangB = (TextView) view.findViewById(R.id.txt_bang_b);

        tvBangA.setOnClickListener(this);
        tvBangB.setOnClickListener(this);

        setBG(tvBangA, tvBangB);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_bang_a:
                initData("banga");
                setBG(tvBangA, tvBangB);
                break;
            case R.id.txt_bang_b:
                initData("bangb");
                setBG(tvBangB, tvBangA);
                break;

        }
    }

    private void setBG(TextView tvA, TextView tvB){
        tvA.setTextColor(Color.WHITE);
        tvA.setBackgroundColor(Color.BLACK);
        tvB.setTextColor(Color.BLACK);
        tvB.setBackgroundColor(Color.WHITE);

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
