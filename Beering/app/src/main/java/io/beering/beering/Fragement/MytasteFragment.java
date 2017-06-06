package io.beering.beering.Fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.beering.beering.R;

public class MytasteFragment extends Fragment {
    TextView ibu_circle;
    TextView abu_circle;
    TextView kcal_circle;


    public MytasteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mytaste, container, false);

        

        return rootView;
    }

}


