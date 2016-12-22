package com.example.anox.bondingapp.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anox.bondingapp.LoginActivity;
import com.example.anox.bondingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 10/1/2016.
 */

public class Sign_up_Fragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String SIGNUP_URL = "http://192.168.1.11/matrimonial/matrimonial/customer/signup.php";
    EditText signup_phone, signup_firstname, signup_lastname, signup_addresss, signup_email, signup_password, signup_confirm_password, signup_dob;
    final String KEY_FIRSTNAME = "firstname";
    final String KEY_LASTNAME = "lastname";
    final String KEY_PLACE = "place";
    final String KEY_ADDRESS = "address";
    final String KEY_PHONE = "phone";
    final String KEY_EMAIL = "email";
    final String KEY_PASSWORD = "password";
    final String KEY_DOB = "dob";
    final String KEY_GENDER = "gender";
    RadioGroup radioGroup;

    RadioButton radio_male, radio_female;
    Spinner signup_ProfileManager;
    String user_type, manager;
    AutoCompleteTextView signup_location;
    Button signupconfirm, signupcancel;
    int genderid = 0;
    String gender, place, address;
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        define(rootView);
        initListener();
        getActivity().setTitle("Sign Up");
        return rootView;

    }

    void define(View rootView) {
        signup_phone = (EditText) rootView.findViewById(R.id.signup_phone);
        signup_firstname = (EditText) rootView.findViewById(R.id.signup_firstname);
        signup_lastname = (EditText) rootView.findViewById(R.id.signup_lastname);
        signup_addresss = (EditText) rootView.findViewById(R.id.signup_address);
        signup_email = (EditText) rootView.findViewById(R.id.signup_email);
        signup_location = (AutoCompleteTextView) rootView.findViewById(R.id.signup_loc);
        signup_password = (EditText) rootView.findViewById(R.id.signup_password);
        signup_confirm_password = (EditText) rootView.findViewById(R.id.signup_confirm_password);
        signupconfirm = (Button) rootView.findViewById(R.id.btn_signuppage);
        signupcancel = (Button) rootView.findViewById(R.id.btn_signupcancel);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup1);
        radio_female = (RadioButton) rootView.findViewById(R.id.radioButtonfemale);
        radio_male = (RadioButton) rootView.findViewById(R.id.radioButtonmale);
        radio_female.setOnClickListener((View.OnClickListener) this);
        radio_male.setOnClickListener((View.OnClickListener) this);
        signup_dob = (EditText) rootView.findViewById(R.id.signup_dob);
        signup_ProfileManager = (Spinner) rootView.findViewById(R.id.signup_profilemanager);
        signup_ProfileManager.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> managers = new ArrayList<>();
        managers.add("Profile Managed by:");
        managers.add("Self");
        managers.add("Parent");
        managers.add("Friend");
        managers.add("Relative");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, managers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signup_ProfileManager.setAdapter(dataAdapter);

    }

    void initListener() {

        signupconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatesignup()) {
                    Assign_Signup();
                } else {
                    Toast.makeText(getActivity(), "Password mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signupcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setMessage("Do you want to cancel Signup?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is taken back to Login page
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    private void Assign_Signup() {


        final String firstname = signup_firstname.getText().toString().trim();
        final String lastname = signup_lastname.getText().toString().trim();
        final String address = signup_addresss.getText().toString().trim();
        final String phone = signup_phone.getText().toString().trim();
        final String email = signup_email.getText().toString().trim();
        final String password = signup_password.getText().toString().trim();
        final String place = signup_location.getText().toString().trim();
        final String dob = signup_dob.getText().toString().trim();
        final String str_gender = gender;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SIGNUP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_FIRSTNAME, firstname);
                params.put(KEY_LASTNAME, lastname);
                params.put(KEY_PLACE, place);
                params.put(KEY_ADDRESS, address);
                params.put(KEY_PHONE, phone);
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD, password);
                params.put(KEY_DOB, dob);
                params.put(KEY_GENDER, str_gender);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private boolean validatesignup() {

        String phone = signup_phone.getText().toString();
        String firstname = signup_firstname.getText().toString();
        String lastname = signup_lastname.getText().toString();
        String address = signup_addresss.getText().toString();
        String email = signup_email.getText().toString();
        String place = signup_location.getText().toString();
        String password = signup_password.getText().toString();
        String confirmpassword = signup_confirm_password.getText().toString();


        boolean cancel = false;
        View focusView = null;
        if (gender == null) {
            Toast.makeText(getActivity(), "Please select Mr. or Ms.", Toast.LENGTH_LONG).show();
            radio_male.setError("Field Required");
            radio_female.setError("Field Required");
            focusView = radio_female;
            focusView = radio_male;
            cancel = true;

        } else {

            Toast.makeText(getActivity(), gender, Toast.LENGTH_LONG).show();
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(phone)) {
            signup_phone.setError(getString(R.string.error_field_required));
            focusView = signup_phone;
            cancel = true;
        }
        if (TextUtils.isEmpty(firstname)) {
            signup_firstname.setError(getString(R.string.error_field_required));
            focusView = signup_firstname;
            cancel = true;
        }
        if (TextUtils.isEmpty(address)) {
            signup_addresss.setError(getString(R.string.error_field_required));
            focusView = signup_addresss;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            signup_email.setError(getString(R.string.error_field_required));
            focusView = signup_email;
            cancel = true;
        }
        if (TextUtils.isEmpty(place)) {
            signup_location.setError(getString(R.string.error_field_required));
            focusView = signup_location;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {

            signup_password.setError(getString(R.string.error_field_required));
            focusView = signup_password;
            cancel = true;
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            signup_confirm_password.setError(getString(R.string.error_field_required));
            focusView = signup_confirm_password;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();
        } else {

            return cancel;
        }
        return cancel;
    }

    @Override
    public void onClick(View view) {
        int genderid = radioGroup.getCheckedRadioButtonId();
        switch (genderid) {
            case R.id.radioButtonfemale:
                if (radio_female.isChecked()) {
                    gender = "female";
                    // Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.radioButtonmale:
                if (radio_male.isChecked()) {
                    gender = "male";
                    //Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (signup_ProfileManager.getSelectedItem() == "Profile Managed by:") {

            Toast.makeText(parent.getContext(), "Please Select Profile Manager", Toast.LENGTH_LONG).show();
        } else {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
