package com.example.anox.bondingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anox.bondingapp.Fragment.Sign_up_Fragment;
import com.example.anox.bondingapp.Utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    String LOGIN_URL = "http://192.168.1.11/matrimonial/matrimonial/customer/logincheck.php";
    final String KEY_USERNAME = "username";
    final String KEY_PASSWORD = "password";
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView forgotPassword;
    private Button buttonLogin;
    private Button buttonSignup;
    private CheckBox remember;

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private  Boolean islogin;
    String username;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        defineView();
    }

    private void defineView() {
        editTextUsername = (EditText) findViewById(R.id.edittext_email);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        buttonLogin = (Button) findViewById(R.id.btnsignin);
        buttonSignup = (Button) findViewById(R.id.btnsignup);
        forgotPassword = (TextView) findViewById(R.id.forgot_pass);
        remember=(CheckBox)findViewById(R.id.remembercheckbox);
        preferences=getSharedPreferences("loginPrefs",MODE_PRIVATE);
        preferencesEditor=preferences.edit();
        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        islogin=preferences.getBoolean("islogin",false);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            userLogin();
        }

        if (view == buttonSignup) {
            Fragment fragment = new Sign_up_Fragment();
            Utilities.getInstance(this).changeChildFragment(
                    fragment, "Sign_up_Fragment", this);


        }
        if (view == forgotPassword) {

            Intent i = new Intent(this, Forgot_Password.class);
            startActivity(i);


        }
    }

    private void userLogin() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if (remember.isChecked()) {
            preferencesEditor.putBoolean("islogin", true);
            preferencesEditor.putString("username", username);
           preferencesEditor.putString("password", password);
           preferencesEditor.commit();
            Toast.makeText(this, "Will Remember You", Toast.LENGTH_SHORT).show();
        } else {
            preferencesEditor.clear();
            preferencesEditor.commit();
            Toast.makeText(this, "Will not Remember You", Toast.LENGTH_SHORT).show();;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String flag = obj.getString("errorCode");
                            if (flag.equals("Success")) {
                                openprofile();
                            } else {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openprofile() {
        Intent i = new Intent(this, Home_Activity.class);
        i.putExtra(KEY_USERNAME, username);
        startActivity(i);
        finish();
    }
}