package com.herokuapp.aqueous_spire_22637.notifyteacher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener,
        PageFragment.OnFragmentInteractionListener {

    private RecyclerView recyclerView;
    public static final String DOMAIN = "aqueous-spire-22637.herokuapp.com";
    private SimpleStringAdapter simpleStringAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setFloatButton();
        setNav(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //RecycleViewをセット
        setupView();
        //Tabをセット
        setTab();
    }

    private void setTab(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final String[] pageTitle = {"DMM", "RAREJOB"};

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount() {
                return pageTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(position + 1);
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupView(){
        recyclerView = (RecyclerView) findViewById(R.id.simple_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        simpleStringAdapter = new SimpleStringAdapter();
        getListTeacher();
        recyclerView.setAdapter(simpleStringAdapter);

    }


    public void displayPage(MenuItem item){
        Log.d("displayPage", "test");
        Intent intent = new Intent(this, PageActivity.class);
        startActivity(intent);
    }

    private void getListTeacher(){
        Retrofit retrofit;
        //Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API_LOG", message);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://" + DOMAIN + "/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        TeacherService teacherService = retrofit.create(TeacherService.class);
        Observable<TeacherService.TeacherList> observable = teacherService.getListDmmTeacher();

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<TeacherService.TeacherList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TeacherService.TeacherList teacherList) {
                simpleStringAdapter.setItemsAndRefresh(teacherList.teachers);
            }
        });
    }

    /**
     * FloatButton
     */
    private void setFloatButton(){
        FloatingActionButton register_teacher = (FloatingActionButton) findViewById(R.id.register);
        register_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherActivity.startActivity(MainActivity.this);
            }
        });
    }

    private void setNav(Toolbar toolbar){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_service_explanation) {
            // Handle the camera action
        } else if (id == R.id.nav_notice) {

        } else if (id == R.id.nav_license) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
