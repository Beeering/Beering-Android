package io.beering.beering;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

        // 검색창 Edittext의 엔터(검색)
        EditText search = (EditText)findViewById(R.id.search_bar);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Enter Key 액션
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Enter키눌렀을때 처리
                    return true;
                }
                return false;
            }
        });

        // 검색 창 내부 텍스트 바뀌는대로 즉시 검색 결과 보여 줌
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
