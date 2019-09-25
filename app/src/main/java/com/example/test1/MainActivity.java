package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String URL_DATA = "https://api.myjson.com/bins/u0tdl";
    private static final String URL_DATA2 = "https://api.github.com/search/users?q=language:java+location:lagos";

    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();

    private ArrayList<String> mNames3 = new ArrayList<>();
    private ArrayList<String> mImageUrls3 = new ArrayList<>();

    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImages();
        getImages2();
        getImages3();
        loadUrlData();
        loadsliderView();
        loadBannerView();
        loadBannerView2();

        //loadFragment(new Fragmenthome());

        // getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(this);

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//
//        switch (item.getItemId()) {
//            case R.id.nav_account:
//                fragment = new Fragmenthome();
//                break;
//
//            case R.id.nav_cart:
//                fragment = new FragmentCart();
//                break;
//
//        }
//
//        return loadFragment(fragment);
//    }
//
//    private boolean loadFragment(Fragment fragment) {
//        //switching fragment
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }
//}


    void loadBannerView() {
        ImageButton simpleImageButtonHome = (ImageButton) findViewById(R.id.imgBtn1);
        simpleImageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Image Offer Button Clicked", Toast.LENGTH_SHORT).show();// display the toast on home button click
            }
        });
    }

    void loadBannerView2() {
        ImageButton simpleImageButtonHome = (ImageButton) findViewById(R.id.imgBtn2);
        simpleImageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Image Offer Button2 Clicked", Toast.LENGTH_SHORT).show();// display the toast on home button click
            }
        });
    }


    void loadsliderView() {
        sliderView = findViewById(R.id.imageSlider);
        final SliderAdapterExample adapter = new SliderAdapterExample(this);
        adapter.setCount(5);
        sliderView.setSliderAdapter(adapter);

//        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        sliderView.setIndicatorSelectedColor(Color.WHITE);
//        sliderView.setIndicatorUnselectedColor(Color.GRAY);
//        sliderView.startAutoCycle();

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
    }

    private void getImages() {
        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/potatoes.png");
        mNames.add("Potato");

        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/onion.png");
        mNames.add("Onions");

        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/carrot.png");
        mNames.add("Carrot");

        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/salad.png");
        mNames.add("Salad");

        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/diet.png");
        mNames.add("Diet");

        mImageUrls.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/olives.png");
        mNames.add("Olive");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "onCreate called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyle);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        CategoryAdapter adapter = new CategoryAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }


    private void getImages2() {
        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/potato.jpg");
        mNames2.add("Potato");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/tomato.jpg");
        mNames2.add("Tomato");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/onion.jpg");
        mNames2.add("Onions");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/gobi.jpg");
        mNames2.add("Gobi");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/cauli.jpg");
        mNames2.add("Cauli");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/brinjal.jpg");
        mNames2.add("Brinjal");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/capcicum.jpg");
        mNames2.add("Capcicum");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/redmirchi.jpg");
        mNames2.add("Mirchi");

        mImageUrls2.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/salad2.png");
        mNames2.add("Salad");

        initRecyclerView2();
    }

    private void initRecyclerView2() {
        Log.d(TAG, "onCreate called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView2 = findViewById(R.id.recyle2);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames2, mImageUrls2);
        recyclerView2.setAdapter(adapter);
    }


    private void getImages3() {
        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban1.jpg");
        mNames3.add("Apples");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban2.jpg");
        mNames3.add("Exotic");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban3.jpg");
        mNames3.add("Fresh Vegs");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban4.jpg");
        mNames3.add("Herbs & Cut");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban2.jpg");
        mNames3.add("Cauli");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban3.jpg");
        mNames3.add("Brinjal");

        mImageUrls3.add("http://146.66.68.148/~flightfa/demo/vegiApp/cat_images/ban4.jpg");
        mNames3.add("Capcicum");

        initRecyclerView3();
    }

    private void initRecyclerView3() {
        Log.d(TAG, "initRecyclerView3 called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView3 = findViewById(R.id.recyle3);
        recyclerView3.setNestedScrollingEnabled(false);
        recyclerView3.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames3, mImageUrls3);
        recyclerView3.setAdapter(adapter);
    }


    private void loadUrlData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("user");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jo = array.getJSONObject(i);

                        String abc = jo.getString("user_contact");
                        String bcd = jo.getString("user_name");
                        String password = jo.getString("user_password");

                        Log.d("abc", abc);
                        Log.d("bcd", bcd);
                        Log.d("bce", password);


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Activity activity = null;

            switch (item.getItemId()) {
                case R.id.nav_account:
                    System.out.println("pressed1");
                    Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(i);
                    break;

                case R.id.nav_cart:
                    System.out.println("pressed2");
                    Intent m = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(m);
                    break;

                case R.id.nav_fav:
                    System.out.println("pressed3");
                    Intent j = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(j);
                    break;

                case R.id.nav_Offer:
                    System.out.println("pressed4");
                    Intent k = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(k);
                    break;

                case R.id.nav_store:
                    System.out.println("pressed5");
                    Intent e = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(e);
                    break;

            }

            return true;
        }


    }


