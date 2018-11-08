package com.tw2.affsuzukicup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;
import com.tw2.affsuzukicup.model.ItemHomePager;
import com.tw2.affsuzukicup.view.activity.ReadNewsActivity;

import java.util.List;

public class HomeViewPagerAdapter extends PagerAdapter {
    private List<ItemHomePager> list;
    private Context context;
    private LayoutInflater inflater;
    private AdView banner;

    public HomeViewPagerAdapter(Context context, List<ItemHomePager> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object.equals(view);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = null;

        if (position == 2) {
            itemView = inflater.inflate(R.layout.fragment_2, container, false);

            MobileAds.initialize(context.getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
            banner = (AdView) itemView.findViewById(R.id.banner);
            AdRequest adRequest = new AdRequest.Builder().build();
            banner.loadAd(adRequest);

        } else {
            itemView = inflater.inflate(R.layout.fragment_1, container, false);
            final ItemHomePager itemHomePager = list.get(position);

            itemView.findViewById(R.id.linear_tin_noi_bat).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReadNewsActivity.class);
                    intent.putExtra("TITLE", itemHomePager.getName());
                    intent.putExtra("IMAGE", itemHomePager.getImage());
                    intent.putExtra("TEXT", itemHomePager.getLink());
                    context.startActivity(intent);
                }
            });
            ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager);
            TextView textView = (TextView) itemView.findViewById(R.id.tv_name_pager);

            Picasso.get().load(itemHomePager.getImage()).into(imageView);
            textView.setText(itemHomePager.getName());

        }
        // ThÃªm itemView vÃ o viewPager
        container.addView(itemView);
        return itemView;
    }

    public void onPauseAds() {
        if (banner != null) {
            banner.pause();
        }
    }

    public void onResumeAds() {
        if (banner != null) {
            banner.resume();
        }

    }

    public void onDestroyAds() {
        if (banner != null) {
            banner.destroy();
        }
    }

}

