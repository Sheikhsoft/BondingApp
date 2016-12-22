package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anox.bondingapp.Adapter.Recycler_Adapter_Shortlist;
import com.example.anox.bondingapp.Adapter.Recycler_Adapter_recommend;
import com.example.anox.bondingapp.Model.Label;
import com.example.anox.bondingapp.R;

/**
 * Created by user on 22-Oct-16.
 */

public class Shortlisted_Fragment extends Fragment {
    private RecyclerView recViewshortlist;
    private Recycler_Adapter_Shortlist adapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_shortlisted, container, false);
        define(rootView);
        return rootView;
    }

    void define(View rootView) {
        recViewshortlist = (RecyclerView) rootView.findViewById(R.id.recview2);
        //recView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(getContext()); // (Context context)
        mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.VERTICAL);// LinearLayoutManager.VERTICAL
        recViewshortlist.setLayoutManager(mLinearLayoutManagerHorizontal);
        adapter2 = new Recycler_Adapter_Shortlist(Label.getListData(), getContext());
        recViewshortlist.setAdapter(adapter2);
    }
}
