package io.beering.beering.Fragement;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.beering.beering.R;

public class ProfileFragment extends Fragment {

    ImageView profile_image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile_image = (ImageView) getView().findViewById(R.id.profile_image);

        // 프로필 사진 원형으로
        profile_image.setBackground(new ShapeDrawable(new OvalShape()));
        profile_image.setClipToOutline(true);

    }


}


