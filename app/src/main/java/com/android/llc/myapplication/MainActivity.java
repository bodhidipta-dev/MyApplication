package com.android.llc.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.llc.myapplication.adapter.VerificationOptionModeAdapter;
import com.android.llc.myapplication.utils.ImageTakerActivityCamera;
import com.android.llc.myapplication.utils.PermissionController;
import com.android.llc.myapplication.utils.ProConstant;
import com.android.llc.myapplication.viewmod.CropCircleTransformation;
import com.android.llc.myapplication.viewmod.NavigationHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Spinner verification_option;
    private ArrayList<String> option_mode;
    private VerificationOptionModeAdapter adapter;
    private ImageView optionImage;
    private static final int REQUEST_IMAGE_CAPTURE = 5;
    private static final int PICK_IMAGE = 3;
    private String mCurrentPhotoPath = "";
    private ImageView back_image, nav_camera, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        nav_camera = (ImageView) findViewById(R.id.nav_camera);

        settings = (ImageView) findViewById(R.id.settings);
        back_image = (ImageView) findViewById(R.id.back_image);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
        nav_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PermissionController.class);
                intent.setAction(PermissionController.ACTION_READ_STORAGE_PERMISSION);
                startActivityForResult(intent, 200);
            }
        });

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
                    case "Preferences":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "Database":
                        startActivity(new Intent(MainActivity.this, LisTActivity.class));
                        break;
                    case "Masterdeta":
                        startActivity(new Intent(MainActivity.this, MasterData.class));
                        break;
                    case "Activation":
                        Toast.makeText(MainActivity.this, "On click " + tag, Toast.LENGTH_SHORT).show();
                        break;
                    case "About":
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
                    //findViewById(R.id.verification_mode).setVisibility(View.GONE);
                    Glide.with(MainActivity.this)
                            .load(R.drawable.boy)
                            .fitCenter()
                            .crossFade()
//                            .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                            .into((ImageView) findViewById(R.id.image_c));
                } else {
                    findViewById(R.id.enrolment_mode).setVisibility(View.GONE);
                    //  findViewById(R.id.verification_mode).setVisibility(View.VISIBLE);
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

    private void showImagePickerOption() {
        new AlertDialog.Builder(MainActivity.this)
                .setCancelable(true)
                .setTitle("Background image")
                .setMessage("please choose image source type.")
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                        }
                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ProConstant.cameraRequested = true;
                        startActivityForResult(new Intent(MainActivity.this, ImageTakerActivityCamera.class), REQUEST_IMAGE_CAPTURE);
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        try {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            mCurrentPhotoPath = data.getExtras().get("data").toString();
                            Glide.with(MainActivity.this).load("file://" + mCurrentPhotoPath).into(new GlideDrawableImageViewTarget(back_image) {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                    super.onResourceReady(resource, animation);
                                }
                            });
                        }
                    }
                }, 800);
            } else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    File dataFile = new File(getRealPathFromURI(uri));
                    if (!dataFile.exists())
                        mCurrentPhotoPath = dataFile.getAbsolutePath();
                    Glide.with(MainActivity.this).load(uri).fitCenter().into(new GlideDrawableImageViewTarget(back_image) {
                        /**
                         * {@inheritDoc}
                         * If no {@link GlideAnimation} is given or if the animation does not set the
                         * {@link Drawable} on the view, the drawable is set using
                         * {@link ImageView#setImageDrawable(Drawable)}.
                         *
                         * @param resource  {@inheritDoc}
                         * @param animation {@inheritDoc}
                         */
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == 200 && resultCode == RESULT_OK) {
                showImagePickerOption();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

}
