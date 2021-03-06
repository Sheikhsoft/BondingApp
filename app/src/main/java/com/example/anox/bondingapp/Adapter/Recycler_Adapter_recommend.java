package com.example.anox.bondingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anox.bondingapp.Fragment.Recommend_Fragment;
import com.example.anox.bondingapp.Model.Profile_Label;
import com.example.anox.bondingapp.R;
import com.example.anox.bondingapp.Utilities.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20-Oct-16.
 */

public class Recycler_Adapter_recommend extends RecyclerView.Adapter<Recycler_Adapter_recommend.MyHolder> {
    private int prevPosition = 0;
    private List<Profile_Label> listData;        //if lstData then
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);

        void onSecondaryIconClick(int p);
    }


    public void setItemClickCallback(final Recommend_Fragment itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }
    public Recycler_Adapter_recommend(List<Profile_Label> listData, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;

        //lstData=listData;// both can be listData.... bt just to knw... so (this) is used other wise it can be avoided.

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.profile_card, parent, false);
        Log.i("Anox", "CREATED A VIEW");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Profile_Label item = listData.get(position);
        holder.title.setText(item.getProfile());
        holder.subtitle.setText(item.getSubTitle());
       if (item.isFav()) {
            holder.secondary_icon.setImageResource(R.mipmap.ic_shortlisted);
        } else {
            holder.secondary_icon.setImageResource(R.mipmap.ic_shortlist);
        }

       if (position > prevPosition) {      // scrolling down
            AnimationUtil.animate(holder, true);
        } else {// scrolling up
            AnimationUtil.animate(holder, false);

        }
        Log.i("Anox", "ON THE POSITION " + position);
    }

    public void setListData(ArrayList<Profile_Label> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView icon;
        private TextView subtitle;
        private ImageView secondary_icon;
        private View container;
        private Button load;

        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_text);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);
            subtitle = (TextView) itemView.findViewById(R.id.item_id);
             secondary_icon = (ImageView) itemView.findViewById(R.id.shortlist);
             secondary_icon.setOnClickListener(this);
           //We'll need the container later on, when we add an View.OnClickListener
            container = itemView.findViewById(R.id.item_root);
            container.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_root) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                 itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }
}
