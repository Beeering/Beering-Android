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
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Proxy.Proxy;
import io.beering.beering.Pub;
import io.beering.beering.PubRecyclerAdapter;
import io.beering.beering.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class PubFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    Button sortbotton;

    public PubFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pub, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view_pub);
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        sortbotton = (Button) getView().findViewById(R.id.pub_filter_btn);

        sortbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "서비스 준비 중 입니다.", Toast.LENGTH_LONG).show();
            }
        });

        //Pub 리스트
        final List<Pub> pubList = new ArrayList<Pub>();

        //Pub 리스트 서버에서 불러오기
        Proxy.getPubList("/pub/list", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String getPubJsonStr = "";
                Log.d("펍리스트-----", "성공");

                try {
                    getPubJsonStr = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                JSONObject jp = null;
                try {
                    jp = new JSONObject(getPubJsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    // info 내부에 정보 있음
                    JSONArray jpubList = jp.getJSONArray("info");
                    // json 데이터 속 맥주 갯수만큼 Pub객체 만듦
                    for(int i = 0; i < jpubList.length(); i++) {
                        JSONObject jpub = (JSONObject) jpubList.getJSONObject(i);
                        String jpubKorName = jpub.getString("pub_korname");
                        String jpubEngName = jpub.getString("pub_engname");
                        String jpubImageUrl = jpub.getString("pub_image");

                        Pub pub = new Pub();
                        pub.setPubKorName(jpubKorName);
                        pub.setPubEngName(jpubEngName);
                        pub.setPubImage(jpubImageUrl);

                        pubList.add(pub);

                        adapter = new PubRecyclerAdapter(pubList);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("펍 리스트~~~~", "실패");
            }
        });
    }
}
