package io.beering.beering;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static io.beering.beering.R.id.logo_text;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_beer:
                    mTextMessage.setText(R.string.main_beer);
                    return true;
                case R.id.main_pub:
                    mTextMessage.setText(R.string.main_pub);
                    return true;
                case R.id.my_profile:
                    mTextMessage.setText(R.string.my_profile);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.rgb(220, 90, 1));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 상단 로고 가운데로 하기위해서 타이틀 없애고 이미지 넣음
        getSupportActionBar().setTitle(null);
        // toolbar.setLogo(R.drawable.logo);

        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        logoImage.setImageResource(R.drawable.logo);

        TextView logoText = (TextView) findViewById(logo_text);
        logoText.setVisibility(View.GONE);

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchBtn.setImageResource(R.drawable.search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

}
