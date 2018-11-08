package com.tw2.affsuzukicup.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.tw2.affsuzukicup.R;

public class ReadNewsActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    private String title, text, image;
    private AdView banner;
    private TextView tvTitle, tvText;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_new);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.color_status_bar));
        }
        text = getIntent().getStringExtra("TEXT");
        title = getIntent().getStringExtra("TITLE");
        image = getIntent().getStringExtra("IMAGE");
        initView();
        initAds();
    }

    private void initAds() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2328589623882503~5399431142");
        banner = (AdView) findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_news);
        tvText = (TextView) findViewById(R.id.tv_text_news);
        imageView = (ImageView) findViewById(R.id.img_news);

        tvTitle.setText(title);
        tvText.setText("   "+text);
        Picasso.get().load(image).into(imageView);

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
