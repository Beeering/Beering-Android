package io.beering.beering;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static io.beering.beering.R.id.logo_text;

public class PubDetailActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_detail);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.rgb(220, 90, 1));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarSetting();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toolbarSetting() {
        // 상단 로고 가운데로 하기위해서 타이틀 없앰
        getSupportActionBar().setTitle(null);

        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        logoImage.setVisibility(View.GONE);

        TextView logoText = (TextView) findViewById(logo_text);
        logoText.setVisibility(View.VISIBLE);
        logoText.setText("Pub");

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchBtn.setVisibility(View.GONE);
    }
}
