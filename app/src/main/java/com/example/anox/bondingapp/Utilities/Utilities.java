package com.example.anox.bondingapp.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.anox.bondingapp.R;

/**
 * Created by user on 10/11/2016.
 */

public class Utilities {
    private static Utilities mUtilities;
    public static int admin_id = 5;
    private static Context mContext;
    public static Utilities getInstance(Context context) {

        mContext = context;
        if (mUtilities == null) {
            mUtilities = new Utilities();

        }
        return mUtilities;
    }
    public void changeChildFragment(Fragment fragment, String tag,
                                    FragmentActivity act) {
        if (fragment != null) {


            FragmentManager fragmentManager = act.getSupportFragmentManager();
           // fragmentManager.beginTransaction().addToBackStack(null);
            fragmentManager.beginTransaction().replace(R.id.frame_login, fragment, null)
                    .commit();

        } else {
            // error in creating fragment
            Log.e("LoginACT",
                    "Error in creating fragment");
        }
    }

    public Boolean isNetAvailable() {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiInfo.isConnected() || mobileInfo.isConnected()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
