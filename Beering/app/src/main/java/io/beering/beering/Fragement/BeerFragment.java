package io.beering.beering.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.beering.beering.R;


public class BeerFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public BeerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //beer recyclerview 생성
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_beer);
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        //Beer 리스트 서버에서 불러오기
//        Proxy.getBeerList("/beer/list", new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
////                Toast.makeText(getApplicationContext(), "비어 겟 성공.....", Toast.LENGTH_SHORT).show();
//                String str = "";
//                Log.d("비어리스트-----", "성공");
//                try {
//                    str = new String(responseBody, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                Log.d("성공!!!!----------", str);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.d("비어리스트-----", "실패");
//            }
//        });



//        adapter = new BeerRecyclerAdapter();
//        recyclerView.setAdapter(adapter);


    }
}
