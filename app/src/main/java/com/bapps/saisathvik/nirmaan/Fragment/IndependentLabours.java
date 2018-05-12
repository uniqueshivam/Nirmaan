package com.bapps.saisathvik.nirmaan.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bapps.saisathvik.nirmaan.Home;
import com.bapps.saisathvik.nirmaan.R;
import com.bapps.saisathvik.nirmaan.constants;
import com.bapps.saisathvik.nirmaan.requesthandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndependentLabours extends Fragment {

    private MaterialEditText id_aadhar,id_labour_name,id_labour_address,id_phonenumber,id_skill,id_blood_id;
    private TextView id_agency_id;

    private CheckBox id_skilled,id_semiskilled,id_male,id_female;
    // private ProgressDialog progressDialog;


    private Button id_signUp;
    private String skill_status;
    private String gender;

    private static IndependentLabours INSTANCE = null;


    public IndependentLabours() {
        // Required empty public constructor
    }

    public static IndependentLabours getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new IndependentLabours();
        return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_independent_labours, container, false);
        id_aadhar = (MaterialEditText)view.findViewById(R.id.id_aadhar);
        id_labour_name = (MaterialEditText)view.findViewById(R.id.id_labour_name);
        id_labour_address = (MaterialEditText)view.findViewById(R.id.id_labour_address);
        id_phonenumber = (MaterialEditText)view.findViewById(R.id.id_phonenumber);
        id_skill =  (MaterialEditText)view.findViewById(R.id.id_skill);
        id_agency_id = (TextView) view.findViewById(R.id.id_agency_id);
        id_skilled=(CheckBox)view.findViewById(R.id.id_skilled);
        id_semiskilled=(CheckBox)view.findViewById(R.id.id_semiskilled);
        id_male = (CheckBox)view.findViewById(R.id.id_male);
        id_female = (CheckBox)view.findViewById(R.id.id_female);
        id_blood_id = (MaterialEditText)view.findViewById(R.id.id_blood_id);
        // progressDialog = new ProgressDialog(getActivity().getApplicationContext());

        id_agency_id.setText("Independent");



        id_skilled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_semiskilled.setChecked(false);
                    skill_status= "skilled";
                }
            }
        });

        id_semiskilled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_skilled.setChecked(false);
                    skill_status="semi-skilled";
                }

            }
        });


        id_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_female.setChecked(false);
                    gender= "male";
                }
            }
        });

        id_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_male.setChecked(false);
                    gender="female";
                }

            }
        });






        id_signUp = (Button)view.findViewById(R.id.id_signUp);
        id_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_labour();

            }
        });

        return view;
    }

    private void add_labour()
    {
        final String getaadhar = id_aadhar.getText().toString().trim();
        final String getlabour_name = id_labour_name.getText().toString().trim();
        final String getlabour_address=id_labour_address.getText().toString().trim();
        final String getlabour_phone=id_phonenumber.getText().toString().trim();
        final String getlabour_skill = id_skill.getText().toString().trim();
        final String getagency_id = id_agency_id.getText().toString().trim();
        final String getskill_level = skill_status;
        final String getgender = gender;
        final String getblood_group = id_blood_id.getText().toString().trim();
        // progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.LABOUR_ADDING_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    // progressDialog.dismiss();
                    Toast.makeText(getActivity().getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
                    getActivity().finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("aadhar",getaadhar);
                params.put("labour_name",getlabour_name);
                params.put("labour_address",getlabour_address);
                params.put("labour_phone",getlabour_phone);
                params.put("skill",getlabour_skill);
                params.put("skill_level",getskill_level);
                params.put("agency_id",getagency_id);
                params.put("gender",getgender);
                params.put("blood_group",getblood_group);
                return params;
            }
        };
        requesthandler.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);

    }


}
