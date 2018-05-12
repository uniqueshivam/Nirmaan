package com.bapps.saisathvik.nirmaan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bapps.saisathvik.nirmaan.Adapter.MyAdapter;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    double lattitude;
    double longitude;

    private MaterialEditText id_agencysearch_id;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listing;
    ImageView search_img_btn,location_img_btn;
    private ProgressDialog progressDialog;
    private LocationManager locationManager;
    private LocationListener locationListener;
    static  final int REQUEST_LOCATION=1;
    private TextView textView;

    private  static  final String ur_link="http://uniqueideas.in/hackathon/v1/search_agency.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        id_agencysearch_id = (MaterialEditText)findViewById(R.id.id_agencysearch_id);
        search_img_btn = (ImageView)findViewById(R.id.search_img_btn) ;
        location_img_btn=(ImageView)findViewById(R.id.google_maps);
        progressDialog = new ProgressDialog(this);
        textView=(TextView)findViewById(R.id.demo);

        recyclerView=(RecyclerView)findViewById(R.id.dashboard_search_agencylist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listing=new ArrayList<>();
        search_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadRecyclerviewData();

            }
        });
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        location_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();

            }
        });




    }

    void getlocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }
        else
        {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if(location !=null)
            {
                lattitude = location.getLatitude();
                longitude = location.getLongitude();

//                Log.i("latitude",String.valueOf(lattitude));
//                Log.i("longitude",String.valueOf(longitude));
                textView.setText(getCompleteAddressString(lattitude,longitude));
//

            }
            else
            {
//                id_agency_areas_servied.setText("error");
                Log.i("error lattitude","error in lattitude");
                Log.i("error longi","eror in longitude");
            }
        }


    }

    private void loadRecyclerviewData() {
        final String city_name =id_agencysearch_id.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ur_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i("in response",response);
                try {
                    //progressDialog.dismiss();
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem obj = new ListItem(o.getString("agency_name"),o.getString("agency_owner"),o.getString("agency_phone"),o.getString("agency_id"),o.getString("agency_email"),
                                o.getString("agency_service_area"));
                        listing.add(obj);

                    }adapter= new MyAdapter(listing,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error Loading", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("city",city_name);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current", strReturnedAddress.toString());
                // id_agency_areas_servied.setText(strReturnedAddress.toString());
            } else {
                Log.w("My Current loc", "No Address returned!");
                //id_agency_areas_servied.setText("No address");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loc", "Canont get Address!");
            // id_agency_areas_servied.setText("Canont get Address!");
        }
        return strAdd;
    }
}
