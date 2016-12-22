package com.example.anox.bondingapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.anox.bondingapp.R;

/**
 * Created by user on 9/20/2016.
 */
public class Adapter_imageSwipe extends PagerAdapter {

    private int[] imageresources = {R.drawable.platinumweddingring, R.drawable.bg_pinknblu, R.drawable.bgwhite};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public Adapter_imageSwipe(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return imageresources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == (LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_image, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageView_swipe);

        imageView.setImageResource(imageresources[position]);

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
