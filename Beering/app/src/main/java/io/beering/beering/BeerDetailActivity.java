package io.beering.beering;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class BeerDetailActivity extends AppCompatActivity {

    ImageView beerImage;
    Toolbar toolbar;
    TextView writeReviewBtn;

    ImageButton cancelBtn;
    Button completeBtn;
    EditText reviewText;
    RatingBar starRating;

    private String reviewTextContent;
    private Float reviewStarRating;

    JSONObject jsonStr;

    ///////////
    TextView beerNameText;
    TextView beerStyleText;
    TextView beerNationText;
    TextView beerAbvText;
    TextView beerIbuText;
    TextView beerKcalText;
    TextView beerHistory;

    ////////
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    ////
    ImageView comment_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.rgb(220, 90, 1));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarSetting();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //activity_beer_detail에서 가져오기
        beerNameText = (TextView) findViewById(R.id.beer_name);
        beerStyleText = (TextView) findViewById(R.id.beer_style);
        beerNationText = (TextView) findViewById(R.id.beer_nation);
        beerAbvText = (TextView) findViewById(R.id.beer_abv_num);
        beerIbuText = (TextView) findViewById(R.id.beer_ibu_num);
        beerKcalText = (TextView) findViewById(R.id.beer_kcal_num);
        beerHistory = (TextView) findViewById(R.id.beer_detail_desc_text);

        //intent beer_id 가져오기
        Intent intent = getIntent();
        int selectedBeerId = intent.getIntExtra("beer_id", -1);

        Proxy.getBeer("/beer/get", selectedBeerId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("비어 디테일", "성공");
                String getJsonStr = "";

                try {
                    getJsonStr = new String(responseBody, "UTF-8");
                    jsonStr = new JSONObject(getJsonStr);

                    JSONArray jsonArr = jsonStr.getJSONArray("info");
                    JSONObject jsonObj = jsonArr.getJSONObject(0);

                    beerNameText.setText(jsonObj.getString("beer_korname"));

                    beerStyleText.setText(jsonObj.getString("style_id"));
                    if(jsonObj.getString("style_id").equals("1")) {
                        beerStyleText.setText("India Pale Ale");
                    }

                    beerNationText.setText(jsonObj.getString("nation_id"));
                    if(jsonObj.getString("nation_id").equals("1")) {
                        beerNationText.setText("South Korea");
                    }

                    beerIbuText.setText(jsonObj.getString("beer_ibu"));
                    beerAbvText.setText(jsonObj.getString("beer_abv")+"%");
                    beerKcalText.setText(jsonObj.getString("beer_kcal"));
                    beerHistory.setText(jsonObj.getString("history"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("비어 디테일", "실패");
            }
        });


        // 맥주 이미지 원형으로
        beerImage = (ImageView) findViewById(R.id.beer_image_circle);
        beerImage.setBackground(new ShapeDrawable(new OvalShape()));
        beerImage.setClipToOutline(true);


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beer_review_dialog);

        // 리뷰쓰기 Dialog 창
        writeReviewBtn = (TextView) findViewById(R.id.write_review);
        writeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        cancelBtn = (ImageButton)dialog.findViewById(R.id.cancel_button);
        completeBtn = (Button)dialog.findViewById(R.id.complete_button);
        starRating = (RatingBar)dialog.findViewById(R.id.review_star_rating);
        reviewText = (EditText)dialog.findViewById(R.id.review_beer_text);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "리뷰 창 닫기", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewTextContent = reviewText.getText().toString();
                reviewStarRating = starRating.getRating();
                Toast.makeText(getApplicationContext(), "완료 누름: "+reviewStarRating, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // 맥주리뷰 불러오기
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_comment);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CommentRecyclerAdapter();
        recyclerView.setAdapter(adapter);


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
        logoText.setText("Beer");

        ImageView searchBtn = (ImageView) findViewById(R.id.search_btn);
        searchBtn.setVisibility(View.GONE);
    }


}
