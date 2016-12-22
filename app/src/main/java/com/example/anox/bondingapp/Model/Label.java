package com.example.anox.bondingapp.Model;

import com.example.anox.bondingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20-Oct-16.
 */

public class Label {

    private static final String[] titles = {"Mr.Sunday","Mr.Monday",
            "Mr.Tuesday" ,"Mr.Wednesday","Mr.Thursday", "Mr.Friday",
            "Mr.Saturday",
    };
    private static final String[] subTitles = {"ID:1000",
            "ID:1001",
            "ID:1002",
            "ID:1003",
            "ID:1004",
            "ID:1005",
            "ID:1006"

    };
    private static final int icon = R.drawable.profile1;

    public static List<Profile_Label> getListData() {
        List<Profile_Label> data = new ArrayList<>();



        //create ListItem with dummy data, then add them to our List
        for (int i = 0; i < titles.length; i++) {
            Profile_Label item = new Profile_Label();
            item.setProfile(titles[i]);
            item.setSubTitle(subTitles[i]);
            data.add(item);
        }

        return data;
    }
}
