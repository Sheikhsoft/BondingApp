package com.example.anox.bondingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.anox.bondingapp.R;
import com.example.anox.bondingapp.Utilities.Utilities;


public class Forgot_Password extends AppCompatActivity {
    EditText email;
    Button sendpassword;
    final String KEY_USERNAME = "username";
    String SIGNUP_URL = "http://192.168.1.6/matrimonial/matrimonial/customer/forgotpassword.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        email=(EditText)findViewById(R.id.edt_forgotfield);
        sendpassword=(Button)findViewById(R.id.btn_forgot_ok);
        sendpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=email.getText().toString();
                /*if (Utilities.getInstance(getActivity()).isNetAvailable()) {
                    HttpRequestHelperForgotPassword helperForforgotpassword = new HttpRequestHelperForgotPassword();
                    helperForforgotpassword.forgotpassword(getActivity(), edt_forgotpassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "network error", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}
