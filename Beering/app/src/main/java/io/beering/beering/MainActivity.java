package io.beering.beering;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Fragement.BeerFragment;
import io.beering.beering.Fragement.ProfileFragment;
import io.beering.beering.Fragement.PubFragment;
import io.beering.beering.Proxy.Proxy;

import static io.beering.beering.R.id.logo_text;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    BeerFragment beerFragment;
    ProfileFragment profileFragment;
    PubFragment pubFragment;
    MenuItem prevMenuItem;
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_beer:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.main_pub:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.my_profile:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Proxy.getBeer("/beer/get", 2, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Toast.makeText(getApplicationContext(), "비어 겟 성공.....", Toast.LENGTH_SHORT).show();
                String str = "";
                Log.d("비어겟-----", "성공");
                try {
                    str = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("성공----------", str);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("비어겟-----", "실패");
            }
        });





        viewPager = (ViewPager)findViewById(R.id.viewpager);

        final BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.rgb(220, 90, 1));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 스와이프 기능을 위한 viewpager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0) toolbarWithLogoImage();
                if (viewPager.getCurrentItem() == 1) toolbarWithLogoImage();
                if (viewPager.getCurrentItem() == 2) toolbarProfile();
            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        beerFragment = new BeerFragment();
        pubFragment = new PubFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(beerFragment);
        adapter.addFragment(pubFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }

    public void toolbarWithLogoImage() {
        // 상단 로고 가운데로 하기위해서 타이틀 없애고 이미지 넣음
        getSupportActionBar().setTitle(null);
        // toolbar.setLogo(R.drawable.logo);

        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        logoImage.setVisibility(View.VISIBLE);
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

    public void toolbarProfile() {
        // 상단 로고 가운데로 하기위해서 타이틀 없앰
        getSupportActionBar().setTitle(null);

        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        logoImage.setVisibility(View.GONE);

        TextView logoText = (TextView) findViewById(logo_text);
        logoText.setVisibility(View.VISIBLE);
        logoText.setText("마이페이지");

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchBtn.setImageResource(R.drawable.setting_icon);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }

}
