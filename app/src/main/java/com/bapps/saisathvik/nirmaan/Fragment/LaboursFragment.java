package com.bapps.saisathvik.nirmaan.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bapps.saisathvik.nirmaan.Adapter.LabourAdapter;
import com.bapps.saisathvik.nirmaan.Adapter.MyAdapter;
import com.bapps.saisathvik.nirmaan.LabourFormActivity;
import com.bapps.saisathvik.nirmaan.Model.LaboursItem;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.bapps.saisathvik.nirmaan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaboursFragment extends Fragment {

    com.github.clans.fab.FloatingActionButton ad_labours;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LaboursItem> listing;
    private Button id_signUp;

    private static final String ur_link = "http://uniqueideas.in/hackathon/v1/labour_dashboard.php";


    private static LaboursFragment INSTANCE = null;

    public LaboursFragment() {
        // Required empty public constructor
    }

    public static LaboursFragment getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new LaboursFragment();
        return INSTANCE;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_labours, container, false);
        id_signUp = (Button) v.findViewById(R.id.id_signUp);
        recyclerView = (RecyclerView) v.findViewById(R.id.dashboard_labours_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listing = new ArrayList<>();
//        for(int i =0;i<10;i++)
//        {
//            LaboursItem laboursItem=new LaboursItem("1234567890","hiii","oiii","ll",
//                    "ji","jiii","loo");
//            listing.add(laboursItem);
//        }
//
        loadRecyclerviewData();
//        adapter=new LabourAdapter(listing,getActivity().getApplicationContext());
//        recyclerView.setAdapter(adapter);
        return v;
    }


    private void loadRecyclerviewData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                    adapter = new LabourAdapter(listing, getActivity().getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error Loading", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("agency_id", "independent");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }
}
