package com.bapps.saisathvik.nirmaan.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bapps.saisathvik.nirmaan.Adapter.MyAdapter;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.bapps.saisathvik.nirmaan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgencyListFragment extends Fragment {

    private TextView welcome;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listing;


    private  static  final String ur_link="http://uniqueideas.in/hackathon/v1/agency_dashboard.php";


    private static AgencyListFragment INSTANCE = null;


    public AgencyListFragment() {
        // Required empty public constructor
    }

    public static AgencyListFragment getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new AgencyListFragment();
        return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_agency_list, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.dashboard_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listing=new ArrayList<>();
        loadRecyclerviewData();
        return v;

    }

    public void loadRecyclerviewData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ur_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response","hii");
                try {
                    progressDialog.dismiss();
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem obj = new ListItem(o.getString("agency_name"),o.getString("agency_owner"),o.getString("agency_phone"),o.getString("agency_id"),o.getString("agency_email"),
                                o.getString("agency_service_area"));
                        listing.add(obj);

                    }adapter= new MyAdapter(listing,getActivity().getApplicationContext());
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }





}
