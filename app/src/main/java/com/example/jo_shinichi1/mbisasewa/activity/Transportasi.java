package com.example.jo_shinichi1.mbisasewa.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentMotor;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentTransportasi;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentUmum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Transportasi extends AppCompatActivity {
    RecyclerView recyclerViewTrans;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private DrawerLayout drawerLayout;
    LinearLayout linearLayout;
    private ViewPager viewPager;
    private NavigationView navigationView;
    Toolbar toolbar;
    AppBarLayout mAppbar;
    public String josn_url = "http://192.168.0.38/user_picture/quantaone.jpg";
    ImageView transportasiImageView;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);
        toolbar = (Toolbar) findViewById(R.id.toolbarTrans);
        setSupportActionBar(toolbar);
        transportasiImageView = (ImageView) findViewById(R.id.imageTransportasiHeader);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayputTrans);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        linearLayout = (LinearLayout) findViewById(R.id.container);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //find reosurce in navigationview
       // image = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageNav);
        mAppbar = (AppBarLayout) findViewById(R.id.mappbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#03A9F4"));
//        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#000000")));
        tabLayout.setupWithViewPager(viewPager);
        //turn off scrolling
//        AppBarLayout.LayoutParams toolbarLayoutParams = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//        toolbarLayoutParams.setScrollFlags(0);
//        toolbar.setLayoutParams(toolbarLayoutParams);

        Intent intent = getIntent();
        String trans = intent.getStringExtra("keyimageheaderkategori");

        Picasso.with(this).load(trans).into(transportasiImageView);

        CoordinatorLayout.LayoutParams appBarLayoutParams = (CoordinatorLayout.LayoutParams) mAppbar.getLayoutParams();
        appBarLayoutParams.setBehavior(null);
        mAppbar.setLayoutParams(appBarLayoutParams);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);


        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msgString = "";

                switch (menuItem.getItemId()) {
                    case R.id.iconpengaturan:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconbantuan:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconlaporkanmasalah:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconkeluar:
                        msgString = (String) menuItem.getTitle();
                        break;
                }

                // Menu item clicked on, and close Drawerlayout
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTransportasi(), "Mobil");
        adapter.addFragment(new FragmentMotor(),"Motor");
        adapter.addFragment(new FragmentUmum(),"Umum");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_photo) {
            Toast.makeText(this, "You clicked on buka sewa", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
