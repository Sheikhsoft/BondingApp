package com.example.anox.bondingapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anox.bondingapp.Fragment.Chat_Fragment;
import com.example.anox.bondingapp.Fragment.Edit_Profile_Fragment;
import com.example.anox.bondingapp.Fragment.Sign_up_Fragment;
import com.example.anox.bondingapp.Fragment.TabFragment_Mailbox;
import com.example.anox.bondingapp.Fragment.Profile_Fragment;
import com.example.anox.bondingapp.Fragment.Shortlisted_Fragment;
import com.example.anox.bondingapp.Fragment.TabFragment_Home;
import com.example.anox.bondingapp.Utilities.Utilities;

/**
 * Created by user on 14-Oct-16.
 */

public class Home_Activity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SharedPreferences u_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences u_preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                String Uname = u_preferences.getString("username", "");
                String pass = u_preferences.getString("password", "");
                Toast.makeText(Home_Activity.this,Uname, Toast.LENGTH_SHORT).show();
                Toast.makeText(Home_Activity.this,pass, Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, new Chat_Fragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.stuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment_Home as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment_Home()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_myprofile) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Profile_Fragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                if (menuItem.getItemId() == R.id.nav_item_shortlisted) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Shortlisted_Fragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_edit) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Edit_Profile_Fragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_mailbox) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment_Mailbox());
                    xfragmentTransaction.addToBackStack(null);
                    xfragmentTransaction.commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setCancelable(false);
                    builder.setMessage("Do you want to logout?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user pressed "yes", then he is taken back to Login page
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user select "No", just cancel this dialog and continue with app
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */



    }

}
