package com.example.anox.bondingapp.Model;

/**
 * Created by user on 20-Oct-16.
 */

public class Profile_Label {
    private String profile;
    private int imageResId;
    private String subTitle;
    private boolean fav = false;


    public boolean isShortlist() {
        return shortlist;
    }

    public void setShortlist(boolean shortlist) {
        this.shortlist = shortlist;
    }
    public boolean isFav() {
        return fav;
    }
    public void setFav(boolean fav) {
        this.fav = fav;
    }
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    private boolean shortlist = false;

}
