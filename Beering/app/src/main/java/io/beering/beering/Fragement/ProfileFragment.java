package io.beering.beering.Fragement;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.beering.beering.ProfileViewPagerAdapter;
import io.beering.beering.R;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    ImageView profile_image;
    private TabLayout tabLayout;
    private ViewPager profileViewPager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fv = inflater.inflate(R.layout.fragment_profile, container, false);

        return fv;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile_image = (ImageView) getView().findViewById(R.id.profile_image);

        // 프로필 사진 원형으로
        profile_image.setBackground(new ShapeDrawable(new OvalShape()));
        profile_image.setClipToOutline(true);


        tabLayout = (TabLayout) getView().findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Stamp"));
        tabLayout.addTab(tabLayout.newTab().setText("Badge"));
        tabLayout.addTab(tabLayout.newTab().setText("My Taste"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        profileViewPager = (ViewPager) getView().findViewById(R.id.profile_view_pager);

        ProfileViewPagerAdapter adapter = new ProfileViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        profileViewPager.setAdapter(adapter);
        profileViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                profileViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}


