package com.bapps.saisathvik.nirmaan;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bapps.saisathvik.nirmaan.Adapter.LabourAdapter;
import com.bapps.saisathvik.nirmaan.Model.LaboursItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewYourAgencylaboursActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LaboursItem> listing;
    private static final String ur_link = "http://uniqueideas.in/hackathon/v1/labour_dashboard.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_agencylabours);
        recyclerView=(RecyclerView)findViewById(R.id.view_Your_agency_labours);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listing = new ArrayList<>();



        loadRecyclerviewData();
    }



    private void loadRecyclerviewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ur_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response", "hii");
                try {
                    progressDialog.dismiss();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        LaboursItem obj = new LaboursItem(o.getString("aadhar"), o.getString("labour_name"), o.getString("labour_address"), o.getString("labour_phone"), o.getString("skill"),
                                o.getString("skill_level"),o.getString("gender"),o.getString("blood_group"));
                        listing.add(obj);

                    }
                    adapter = new LabourAdapter(listing, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error Loading", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("agency_id", sharedpreference_agencylogin.getInstance(getApplicationContext()).get_agency_id());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
