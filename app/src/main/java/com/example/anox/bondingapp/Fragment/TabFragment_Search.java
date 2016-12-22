package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anox.bondingapp.R;

/**
 * Created by user on 02-Nov-16.
 */

public class TabFragment_Search extends Fragment{
    public static TabLayout tabLayout_search;
    public static ViewPager viewPager_search;
    public static int int_items = 2 ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         *Inflate tab_layout_home and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout_search,null);
        tabLayout_search = (TabLayout) x.findViewById(R.id.tabsearch);
        viewPager_search = (ViewPager) x.findViewById(R.id.viewpager_search);

        /**
         *Set an Adapter for the View Pager
         */
        viewPager_search.setAdapter(new MyAdapter_search(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout_search.post(new Runnable() {
            @Override
            public void run() {
                tabLayout_search.setupWithViewPager(viewPager_search);
            }
        });

        return x;
    }
    class MyAdapter_search extends FragmentPagerAdapter {

        public MyAdapter_search(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Fragment_Preferences();
                case 1 : return new Search_Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Search Info";
                case 1 :
                    return "Search ID";
            }
            return null;
        }
    }
}


