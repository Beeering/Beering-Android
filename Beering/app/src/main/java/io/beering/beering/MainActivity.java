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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.beering.beering.Fragement.BeerFragment;
import io.beering.beering.Fragement.ProfileFragment;
import io.beering.beering.Fragement.PubFragment;

import static io.beering.beering.R.id.logo_text;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ViewPager viewPager;

    BeerFragment beerFragment;
    ProfileFragment profileFragment;
    PubFragment pubFragment;
    MenuItem prevMenuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_beer:
                    viewPager.setCurrentItem(0);
                    mTextMessage.setText(R.string.main_beer);
                    return true;
                case R.id.main_pub:
                    viewPager.setCurrentItem(1);
                    mTextMessage.setText(R.string.main_pub);
                    return true;
                case R.id.my_profile:
                    viewPager.setCurrentItem(2);
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
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        final BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
        profileFragment = new ProfileFragment();
        pubFragment = new PubFragment();
        adapter.addFragment(beerFragment);
        adapter.addFragment(profileFragment);
        adapter.addFragment(pubFragment);
        viewPager.setAdapter(adapter);
    }

}
