package com.sadmi.project.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sadmi.project.fragment.BlankFragment;
import com.sadmi.project.fragment.MainFragment;
import com.sadmi.project.R;
import com.sadmi.project.fragment.ListFragment;
import com.sadmi.project.model.UserSharedPref;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getFragmentManager().beginTransaction()
                .replace(R.id.user_fragment,
                        new ListFragment()).commit();


        /************************************************************/
            BottomNavigationViewClicked();
        /************************************************************/

    }


    void BottomNavigationViewClicked(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        Toast.makeText(UserActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        /*************************************************************/
                        getFragmentManager().beginTransaction()
                                .replace(R.id.user_fragment,
                                        new ListFragment()).commit();

                        /*************************************************************/
                        break;
                    case R.id.action_dashboard:
                        Toast.makeText(UserActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                        /*************************************************************/
                        getFragmentManager().beginTransaction()
                                .replace(R.id.user_fragment,
                                        new MainFragment()).commit();

                        /*************************************************************/
                        break;
                    case R.id.action_notifications:
                        Toast.makeText(UserActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                        /*************************************************************/
                        getFragmentManager().beginTransaction()
                                .add(R.id.user_fragment,
                                        new BlankFragment()).commit();

                        /*************************************************************/
                        break;

                }

                return true;

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment fragment;

        if (id == R.id.nav_user) {
            fragment = new MainFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.user_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.nav_gallery) {

            fragment = new ListFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.user_fragment, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            UserSharedPref userSharedPref = new UserSharedPref(this);
            userSharedPref.disconnectUser();
            Intent intent = new Intent(UserActivity.this,HomeActivity.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
