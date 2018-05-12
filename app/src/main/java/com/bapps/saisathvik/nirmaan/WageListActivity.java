package com.bapps.saisathvik.nirmaan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bapps.saisathvik.nirmaan.Adapter.MyAdapter;
import com.bapps.saisathvik.nirmaan.Adapter.WageAdapter;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.bapps.saisathvik.nirmaan.Model.wagesmodel;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WageListActivity extends AppCompatActivity {

    private ImageView search_wages_btn;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<wagesmodel> listing;
    ImageView search;

    private  static  final String ur_link="http://uniqueideas.in/hackathon/v1/wageapi.php";
    private MaterialEditText city_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage_list);
        city_name=(MaterialEditText)findViewById(R.id.id_wagesList);
        search=(ImageView)findViewById(R.id.search_wages_btn);
        recyclerView=(RecyclerView)findViewById(R.id.dashboard_search_wageslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listing=new ArrayList<>();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadRecyclerviewData();
            }
        });
    }


    private void loadRecyclerviewData() {
        final String city=city_name.getText().toString().trim();
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ur_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // progressDialog.dismiss();
                Log.i("in response",response);
                try {
                    //progressDialog.dismiss();
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        wagesmodel obj = new wagesmodel(o.getString("skilled_wages"),o.getString("no_skilled_wages"));
                        listing.add(obj);

                    }adapter= new WageAdapter(listing,getApplicationContext());
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
                params.put("state",city);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
