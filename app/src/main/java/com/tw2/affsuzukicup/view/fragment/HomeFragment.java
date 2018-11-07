package com.tw2.affsuzukicup.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.adapter.CauThuAdapter;
import com.tw2.affsuzukicup.adapter.ContainerKetQuaAdapter;
import com.tw2.affsuzukicup.adapter.HomeViewPagerAdapter;
import com.tw2.affsuzukicup.adapter.LichThiDauAdapter;
import com.tw2.affsuzukicup.model.CauThu;
import com.tw2.affsuzukicup.model.ContainerKetQua;
import com.tw2.affsuzukicup.model.ItemHomePager;
import com.tw2.affsuzukicup.model.KetQua;
import com.tw2.affsuzukicup.model.LichThiDau;
import com.tw2.affsuzukicup.model.Top;
import com.tw2.affsuzukicup.view.activity.LichThiDauActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private List<LichThiDau> list;
    private LichThiDauAdapter adapter;
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private List<ItemHomePager> pagerList;
    private HomeViewPagerAdapter viewPagerAdapter;
    private Handler handler;
    private int delay = 5000;
    private int page = 0;
    private DatabaseReference mReference;
    private boolean isLoad = true;
    private TextView tvTime;
    private int index = 1;
    private ImageView imgPhaLuoi, imgKienTao;
    private TextView tvNamePhaLuoi, tvNameKienTao, tvBanPhaLuoi, tvBanKienTao;
    private AdView banner;

    Runnable runnable = new Runnable() {
        public void run() {
            if (viewPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };


    public HomeFragment() {
        handler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initAds();
        initLichThiDau();
        initViewPager();
        initTop();
        return view;
    }

    private void initAds() {
        MobileAds.initialize(getContext().getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
        banner = (AdView) view.findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }

    private void initTop() {
        mReference.child("top").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Top top = dataSnapshot.getValue(Top.class);
                if (index==1) {
                    tvNamePhaLuoi.setText(top.getName());
                    tvBanPhaLuoi.setText(top.getBan());
                    Picasso.get().load(top.getImage()).into(imgPhaLuoi);
                } else if (index==2){
                    tvNameKienTao.setText(top.getName());
                    tvBanKienTao.setText(top.getBan());
                    Picasso.get().load(top.getImage()).into(imgKienTao);
                }

                index++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    private void initViewPager() {
        pagerList = new ArrayList<>();
        pagerList.clear();

        mReference.child("tinnoibat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ItemHomePager itemHomePager = dataSnapshot.getValue(ItemHomePager.class);
                pagerList.add(itemHomePager);
                viewPagerAdapter = new HomeViewPagerAdapter(getContext(), pagerList);
                viewPager.setAdapter(viewPagerAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    private void initLichThiDau() {
        list = new ArrayList<>();
        list.clear();

        mReference.child("lichthidau").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (isLoad) {
                    ContainerKetQua containerKetQua = dataSnapshot.getValue(ContainerKetQua.class);
                    tvTime.setText(containerKetQua.getNgay());
                    List<KetQua> ketQuaList = containerKetQua.getTran();
                    for (int i = 0; i<ketQuaList.size();i++){
                        if (ketQuaList.get(i)==null){
                            ketQuaList.remove(i);
                        }
                    }
                    adapter = new LichThiDauAdapter(ketQuaList);
                    recyclerView.setAdapter(adapter);
                    isLoad = false;
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                initLichThiDau();
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

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_home);
        viewPager = (ViewPager) view.findViewById(R.id.home_viewpager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvBanKienTao = (TextView) view.findViewById(R.id.tv_ban_vua_kien_tao);
        tvBanPhaLuoi = (TextView) view.findViewById(R.id.tv_ban_vua_pha_luoi);
        tvNameKienTao = (TextView) view.findViewById(R.id.tv_vua_kien_tao);
        tvNamePhaLuoi = (TextView) view.findViewById(R.id.tv_vua_pha_luoi);
        imgKienTao = (ImageView) view.findViewById(R.id.img_vua_kien_tao);
        imgPhaLuoi = (ImageView) view.findViewById(R.id.img_vua_pha_luoi);

        view.findViewById(R.id.view_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_all:
                Intent intent = new Intent(getActivity(), LichThiDauActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewPagerAdapter!=null){
            viewPagerAdapter.onResumeAds();
        }
        if (banner != null) {
            banner.resume();
        }
        handler.postDelayed(runnable, delay);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.pause();
        }
        if (viewPagerAdapter!=null){
            viewPagerAdapter.onPauseAds();
        }
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        if (viewPagerAdapter!=null){
            viewPagerAdapter.onDestroyAds();
        }
        if (banner != null) {
            banner.destroy();
        }
        super.onDestroy();
    }
}
