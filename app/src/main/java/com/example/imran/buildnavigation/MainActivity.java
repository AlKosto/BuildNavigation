package com.example.imran.buildnavigation;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    Button imgBtn;
    LinearLayout drawer;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        imgBtn = findViewById(R.id.imageBtn);
        drawer = (LinearLayout)findViewById(R.id.drawer);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (drawer.getVisibility() == View.GONE) {
                    showDrawer();
                }

                else if (drawer.getVisibility() == View.VISIBLE) {

                   hideDrawer();
                }
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = website.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = Location.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = aboutFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, website.newInstance());
        transaction.commit();

    }


    void hideDrawer()  {


        Animation slideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down);

        drawer.setVisibility(View.GONE);
        drawer.startAnimation(slideDown);

        ObjectAnimator anim = ObjectAnimator.ofFloat(imgBtn,"x",480,0).setDuration(520);
        anim.start();
        imgBtn.setImageResource(R.drawable.open_drawer);


        Animation frameShow = AnimationUtils.loadAnimation(MainActivity.this, R.anim.frame_show);
        frameLayout.setVisibility(View.VISIBLE);
        frameLayout.setAnimation(frameShow);

    }


    void showDrawer(){

        Animation framHide = AnimationUtils.loadAnimation(MainActivity.this, R.anim.frasme_hide);
        frameLayout.setVisibility(View.INVISIBLE);
        frameLayout.setAnimation(framHide);


        Animation slideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up);
        drawer.setVisibility(View.VISIBLE);
        drawer.startAnimation(slideUp);

        ObjectAnimator anim = ObjectAnimator.ofFloat(imgBtn,"x",0,480).setDuration(520);
        anim.start();
        imgBtn.setImageResource(R.drawable.close_drawer);
    }


    @Override
    public void onBackPressed() {
        if(drawer.getVisibility() == View.VISIBLE){
            hideDrawer();
        } else {
            super.onBackPressed();
        }
    }
}

















