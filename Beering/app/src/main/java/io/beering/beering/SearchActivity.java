package io.beering.beering;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static io.beering.beering.R.id.logo_text;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.rgb(220, 90, 1));
        }

        // 툴바 로고 이미지 제거 및 타이틀 설정
        ImageView logoImage = (ImageView) findViewById(R.id.logo);
        logoImage.setVisibility(View.GONE);

        ImageView image = (ImageView) findViewById(R.id.search_btn);
        image.setVisibility(View.GONE);

        TextView logoText = (TextView) findViewById(logo_text);
        logoText.setText("검색");

        // 툴바 뒤로가기 버튼 활성화
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 툴바 기존의 title을 제거
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
