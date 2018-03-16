package com.example.nilo.dublinbikeir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Location;
    Button Station;
    Button About;
    ListView listView;
    String API_URL = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin";
    String API_KEY = "&apiKey=f59933513e3702dda0a77571748c1a83d18ba43e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bikes Locator App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Location  = (Button) findViewById(R.id.btnMap);
        About =(Button) findViewById(R.id.btnAboout);
        Station=findViewById(R.id.btnStation);
        listView=findViewById(R.id.listView);

        Station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,About.class);
                startActivity(i);


            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });


    }

    public void loadData(){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL + API_KEY,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<LocationContract> arrayLocation = new ArrayList<LocationContract>();

                for (int i = 0; i < response.length(); i++)

                    try {
                        JSONObject object = response.getJSONObject(i);
                        String location = object.getString("name");
                        String status = object.getString("status");
                        String bikeStand = object.getString("bike_stands");
                        String availabebikestand = object.getString("available_bike_stands");
                        String availableBikes = object.getString("available_bikes");

                        JSONObject childObject = object.getJSONObject("position");
                        String lat = childObject.getString("lat");
                        String lng = childObject.getString("lng");

                        LocationContract locationContract = new LocationContract(location, status, bikeStand, availabebikestand, availableBikes, lat, lng);
                        arrayLocation.add(locationContract);

                        CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.custom_row, arrayLocation);
                        listView.setAdapter(adapter);

                        //  progressBar.setVisibility(View.GONE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //progressBar.setVisibility(View.GONE);
                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                if (networkInfo == null) {
                    Toast.makeText(MainActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem searchBar = menu.findItem(R.id.menuSearch);
//
//        SearchView searchView = (SearchView) searchBar.getActionView();
//        searchView.setQueryHint("Search...");
//
//    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//
//            Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
//
//            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL + query + API_KEY,
//                    null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    ArrayList<LocationContract> arrayLocation = new ArrayList<LocationContract>();
//
//                    for (int i = 0; i < response.length(); i++)
//
//                        try {
//                            JSONObject object = response.getJSONObject(i);
//                            String location = object.getString("name");
//                            String status = object.getString("status");
//                            String bikeStand = object.getString("bike_stands");
//                            String availabebikestand = object.getString("available_bike_stands");
//                            String availableBikes = object.getString("available_bikes");
//
//                            JSONObject childObject = object.getJSONObject("position");
//                            String lat = childObject.getString("lat");
//                            String lng = childObject.getString("lng");
//
//                            LocationContract locationContract = new LocationContract(location, status, bikeStand, availabebikestand, availableBikes, lat, lng);
//                            arrayLocation.add(locationContract);
//
//                            CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.custom_row, arrayLocation);
//                            listView.setAdapter(adapter);
//
//                          //  progressBar.setVisibility(View.GONE);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    //progressBar.setVisibility(View.GONE);
//                    ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//                    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//                    if (networkInfo == null) {
//                        Toast.makeText(MainActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//            );
//
//            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//            queue.add(request);
//
//
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            return false;
//        }
//    });
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
}




