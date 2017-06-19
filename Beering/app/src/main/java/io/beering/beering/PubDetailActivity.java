package io.beering.beering;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Proxy.Proxy;

import static io.beering.beering.R.id.logo_text;

public class PubDetailActivity extends AppCompatActivity {

    Toolbar toolbar;


    JSONObject jsonStr;

    ///////////
    TextView pubKorNameText;
    TextView pubEngNameText;
    TextView pubAddressText;

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


        //intent pub_id 가져오기
        Intent intent = getIntent();
        int selectedPubId = intent.getIntExtra("pub_id", -1);

        pubKorNameText = (TextView) findViewById(R.id.pub_text_1);
        pubEngNameText = (TextView) findViewById(R.id.pub_text_2);
        pubAddressText = (TextView) findViewById(R.id.pub_address);

        Proxy.getPub("/pub/get", selectedPubId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("펍 디테일", "성공");
                String getJsonStr = "";

                try {
                    getJsonStr = new String(responseBody, "UTF-8");
                    jsonStr = new JSONObject(getJsonStr);

                    JSONArray jsonArr = jsonStr.getJSONArray("info");
                    JSONObject jsonObj = jsonArr.getJSONObject(0);

                    pubKorNameText.setText(jsonObj.getString("pub_korname"));
                    pubEngNameText.setText(jsonObj.getString("pub_engname"));
                    pubAddressText.setText(jsonObj.getString("pub_location"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("펍 디테일", "실패");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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


