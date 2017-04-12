package com.example.juanh.unammobileexample.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.juanh.unammobileexample.Fragments.FeedFragment;
import com.example.juanh.unammobileexample.Fragments.NegociosFragment;
import com.example.juanh.unammobileexample.Fragments.UserFragment;
import com.example.juanh.unammobileexample.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildUI();
    }

    /**
     * Auxiliar method for building the UI.
     */
    private void buildUI() {
        toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                                                                        drawerLayout,
                                                                        toolbar,
                                                                        R.string.drawer_open,
                                                                        R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView = (NavigationView) findViewById(R.id.nav_main);
        navView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getSupportFragmentManager();
        try {
            fm.beginTransaction().add(R.id.flContent, FeedFragment.class.newInstance()).commit();
            setTitle("Feed");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_feed_fragment:
                fragmentClass = FeedFragment.class;
                break;
            case R.id.nav_negocios_fragment:
                fragmentClass = NegociosFragment.class;
                break;
            case R.id.nav_user_fragment:
                fragmentClass = UserFragment.class;
                break;
            default:
                fragmentClass = FeedFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flContent,fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return true;
    }
}
