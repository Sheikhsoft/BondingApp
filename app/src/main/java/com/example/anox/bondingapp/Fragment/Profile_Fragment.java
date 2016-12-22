package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anox.bondingapp.Adapter.Adapter_imageSwipe;
import com.example.anox.bondingapp.R;

/**
 * Created by user on 9/20/2016.
 */
public class Profile_Fragment extends Fragment {
    ViewPager viewPager;
    Adapter_imageSwipe vpadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        viewPager=(ViewPager)rootView.findViewById(R.id.view_pager);
        vpadapter=new Adapter_imageSwipe(getActivity());
        viewPager.setAdapter(vpadapter);
        return rootView;
    }
}
