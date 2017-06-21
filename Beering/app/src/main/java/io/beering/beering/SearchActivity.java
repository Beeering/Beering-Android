package io.beering.beering;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Proxy.Proxy;

import static io.beering.beering.R.id.logo_text;

public class SearchActivity extends AppCompatActivity {

    Intent intent;

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
        final String searchText = search.getText().toString();

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(final View v, int keyCode, KeyEvent event) {
                // Enter키눌렀을때 처리
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    intent = new Intent(getApplicationContext(), BeerDetailActivity.class);

                    Proxy.getBeerList("/beer/list", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String getBeerJsonStr = "";
                            Log.d("비어리스트-----", "성공");

                            try {
                                getBeerJsonStr = new String(responseBody, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            JSONObject jb = null;
                            try {
                                jb = new JSONObject(getBeerJsonStr);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                // info 내부에 정보 있음
                                JSONArray jbeerList = jb.getJSONArray("info");

                                for(int i = 0; i < jbeerList.length(); i++) {
                                    JSONObject jbeer = (JSONObject) jbeerList.getJSONObject(i);
                                    String jbeerName = jbeer.getString("beer_korname");

                                    if(jbeerName.equals(searchText)) {
                                        intent.putExtra("beer_id", i+1);
                                        Toast.makeText(getApplicationContext(), i+1, Toast.LENGTH_SHORT).show();
                                        v.getContext().startActivity(intent);
                                        return;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });

        // 검색 창 내부 텍스트 바뀌는대로 즉시 검색 결과 보여 줌
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

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
