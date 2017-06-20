package io.beering.beering.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Beer;
import io.beering.beering.BeerRecyclerAdapter;
import io.beering.beering.Proxy.Proxy;
import io.beering.beering.R;


public class RecommendFragement extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public RecommendFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //beer recyclerview 생성
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_beer_recommend);
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        //Beer 리스트
        final List<Beer> beerList = new ArrayList<Beer>();

        //Beer 리스트 서버에서 불러오기
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

                    // json 데이터 속 맥주 갯수만큼 Beer객체 만듦
                    for(int i = 20; i < 25; i++) {
                        JSONObject jbeer = (JSONObject) jbeerList.getJSONObject(i);
                        String jbeerNation = jbeer.getString("nation_id");
                        String jbeerKorName = jbeer.getString("beer_korname");
                        String jbeerImageUrl = jbeer.getString("beer_image");
                        String jbeerHistory = jbeer.getString("history");
                        String jbeerAbv = jbeer.getString("beer_abv");
                        String jbeerIbu = jbeer.getString("beer_ibu");
                        String jbeerKcal = jbeer.getString("beer_kcal");
                        String jbeerSrm = jbeer.getString("beer_srm");
                        String jbeerStyle = jbeer.getString("style_id");
                        if (jbeerStyle.equals("1")) {
                            jbeerStyle = "India Pale Ale";
                        }

                        if (jbeerNation.equals("1")) {
                            jbeerNation = "South Korea";
                        }

                        Beer beer = new Beer();
                        beer.setBeerName(jbeerKorName);
                        beer.setBeerNation(jbeerNation);
                        beer.setBeerImage(jbeerImageUrl);
                        beer.setBeerHistory(jbeerHistory);
                        beer.setBeerAbv(jbeerAbv);
                        beer.setBeerIbu(jbeerIbu);
                        beer.setBeerKcal(jbeerKcal);
                        beer.setBeerSrm(jbeerSrm);
                        beer.setBeerStyle(jbeerStyle);

                        beerList.add(beer);

                        adapter = new BeerRecyclerAdapter(beerList);
                        recyclerView.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("비어추천리스트-----", "실패");
            }
        });
    }
}
