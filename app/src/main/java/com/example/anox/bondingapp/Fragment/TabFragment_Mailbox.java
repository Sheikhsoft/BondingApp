package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anox.bondingapp.R;

public class TabFragment_Mailbox extends Fragment {

    public static TabLayout tabLayout_mail;
    public static ViewPager viewPager_mail;
    public static int int_items = 2 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout_home and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout_mailbox,null);
        tabLayout_mail = (TabLayout) x.findViewById(R.id.tabs);
        viewPager_mail = (ViewPager) x.findViewById(R.id.viewpager_mail);

        /**
         *Set an Adapter for the View Pager
         */
        viewPager_mail.setAdapter(new MyAdapter_mail(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout_mail.post(new Runnable() {
            @Override
            public void run() {
                tabLayout_mail.setupWithViewPager(viewPager_mail);
            }
        });

        return x;

    }

    class MyAdapter_mail extends FragmentPagerAdapter {

        public MyAdapter_mail(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Inbox_Fragment();
                case 1 : return new Outbox_Fragment();
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
                    return "Received";
                case 1 :
                    return "Sent";
            }
            return null;
        }
    }
}