package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anox.bondingapp.Adapter.Recycler_Adapter_recommend;
import com.example.anox.bondingapp.Model.Label;
import com.example.anox.bondingapp.Model.Profile_Label;
import com.example.anox.bondingapp.R;

import java.util.ArrayList;

public class Recommend_Fragment extends Fragment implements Recycler_Adapter_recommend.ItemClickCallback {
    private RecyclerView recView;
    private Recycler_Adapter_recommend adapter;
    FragmentManager mFragmentManager;
    private ArrayList listData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.recommend_fragment, container, false);
        define(rootView);
        return rootView;



    }
    void define(View rootView) {
        recView = (RecyclerView)rootView.findViewById(R.id.rec_list);
        //recView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(getContext()); // (Context context)
        mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.VERTICAL);// LinearLayoutManager.VERTICAL
        recView.setLayoutManager(mLinearLayoutManagerHorizontal);
        adapter = new Recycler_Adapter_recommend(Label.getListData(),getContext());
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    @Override
    public void onItemClick(int p) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new Profile_Fragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onSecondaryIconClick(int p) {
        Toast.makeText(getContext(),"Profile Shortlisted", Toast.LENGTH_SHORT).show();
        Profile_Label label = (Profile_Label) listData.get(p);
        //update our data
        if (label.isFav()) {
            label.setFav(false);
        } else {
            label.setFav(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }
}

