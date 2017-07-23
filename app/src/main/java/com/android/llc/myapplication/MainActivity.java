package com.android.llc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.llc.myapplication.adapter.VerificationOptionModeAdapter;
import com.android.llc.myapplication.viewmod.CropCircleTransformation;
import com.android.llc.myapplication.viewmod.NavigationHandler;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Spinner verification_option;
    private ArrayList<String> option_mode;
    private VerificationOptionModeAdapter adapter;
    private ImageView optionImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        findViewById(R.id.drawer_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        NavigationHandler.getInstance(MainActivity.this).init(drawer, new NavigationHandler.OnHandleInput() {
            @Override
            public void onClickItem(String tag) {
                drawer.closeDrawer(GravityCompat.END);
                switch (tag) {
                    case "List":
                        startActivity(new Intent(MainActivity.this, LisTActivity.class));
                        break;
                    case "two":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Three":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Four":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Five":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Six":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Seven":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Eight":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        optionImage = (ImageView) findViewById(R.id.optionImage);
        verification_option = (Spinner) findViewById(R.id.options);

        option_mode = new ArrayList<>();
        option_mode.add("Enroll");
        option_mode.add("Verified Mode");

        adapter = new VerificationOptionModeAdapter(MainActivity.this, 0, option_mode);


        verification_option.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.actionmenuspiner);
        verification_option.setDropDownVerticalOffset(100);

        verification_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    optionImage.setBackgroundResource(R.drawable.ic_account);
                    findViewById(R.id.enrolment_mode).setVisibility(View.VISIBLE);
                    findViewById(R.id.verification_mode).setVisibility(View.GONE);
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.image_c));
                } else {
                    findViewById(R.id.enrolment_mode).setVisibility(View.GONE);
                    findViewById(R.id.verification_mode).setVisibility(View.VISIBLE);
                    optionImage.setBackgroundResource(R.drawable.ic_check);
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.verified_image1));
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.verified_image2));
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.verified_image3));
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.verified_image4));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
