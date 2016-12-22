package com.example.anox.bondingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anox.bondingapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31-Oct-16.
 */

public class Edit_Profile_Fragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener  {
   // String EDIT_URL = "http://192.168.1.11/matrimonial/matrimonial/customer/";
    Spinner maritalstatus;
    //EditText ;
    final String KEY_HEIGHT_FEET = "heightfeet";
    final String KEY_HEIGHT_INCHES = "height_inches";
    final String KEY_BODYTYPE = "body type";
    final String KEY_SKINTONE = "skintone";
    final String KEY_HEALTHINFO = "health";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_profile, container, false);
        define(rootView);
        initListener();
        getActivity().setTitle("Sign Up");
        return rootView;

    }
    void define(View rootView) {
        maritalstatus=(Spinner) rootView.findViewById(R.id.marital_status);
        maritalstatus.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> status = new ArrayList<>();
        status.add("Never Married");
        status.add("Divorcee");
        status.add("Widow/Widower");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, status);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalstatus.setAdapter(dataAdapter);
    }
    void initListener() {

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
