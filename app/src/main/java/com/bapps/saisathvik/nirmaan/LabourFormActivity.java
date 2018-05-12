package com.bapps.saisathvik.nirmaan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import com.bapps.saisathvik.nirmaan.Fragment.SignUpFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LabourFormActivity extends AppCompatActivity {

    private MaterialEditText id_aadhar,id_labour_name,id_labour_address,id_phonenumber,id_skill,id_blood_labour;
    TextView id_agency_for_labour;

    private CheckBox id_skilled,id_semiskilled,id_male_labour,id_female_labour;

    private Button id_signUp;
    private String skill_status;

    private String gender;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_form);
        id_aadhar = (MaterialEditText)findViewById(R.id.id_aadhar);
        id_labour_name = (MaterialEditText)findViewById(R.id.id_labour_name);
        id_labour_address = (MaterialEditText)findViewById(R.id.id_labour_address);
        id_phonenumber = (MaterialEditText)findViewById(R.id.id_phonenumber);
        id_skill = (MaterialEditText)findViewById(R.id.id_skill);
        id_agency_for_labour = (TextView)findViewById(R.id.id_agency_for_labour);
        id_skilled=(CheckBox)findViewById(R.id.id_skilled);
        id_semiskilled=(CheckBox)findViewById(R.id.id_semiskilled);
        id_male_labour = (CheckBox)findViewById(R.id.id_male_labour);
        id_female_labour = (CheckBox)findViewById(R.id.id_female_labour);


        id_blood_labour = (MaterialEditText)findViewById(R.id.id_blood_labour);
        id_signUp =(Button)findViewById(R.id.id_signUp);
        progressDialog=new ProgressDialog(this);
        //id_agency_for_labour.setText("hii");

        Intent intent = getIntent();
        String independent_labour=intent.getStringExtra("independent_labour");
        String agency_labour=intent.getStringExtra("agency");

        if(sharedpreference_agencylogin.getInstance(this).isAgencylogedin())
        {
            id_agency_for_labour.setText(agency_labour);
        }
        else
        {
            id_agency_for_labour.setText(SignUpFragment.getINSTANCE().agencyid.getText());
        }




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


        id_male_labour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_female_labour.setChecked(false);
                    gender= "Male";
                }
            }
        });

        id_semiskilled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    id_male_labour.setChecked(false);
                    gender="Female";
                }

            }
        });




        id_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_labour();
            }
        });


    }
    private void add_labour()
    {
        final String getaadhar = id_aadhar.getText().toString().trim();
        final String getlabour_name = id_labour_name.getText().toString().trim();
        final String getlabour_address=id_labour_address.getText().toString().trim();
        final String getlabour_phone=id_phonenumber.getText().toString().trim();
        final String getlabour_skill = id_skill.getText().toString().trim();
        final String getagency_id = id_agency_for_labour.getText().toString().trim();
        final String getskill_level = skill_status;
        final String getgender=gender;
        final String getblood_group=id_blood_labour.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.LABOUR_ADDING_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    // Toast.makeText(LabourFormActivity.this, "You Just Added a Labour", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabourFormActivity.this,LabourFormActivity.class));
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LabourFormActivity.this, "Error", Toast.LENGTH_SHORT).show();

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
        requesthandler.getInstance(this).addToRequestQueue(stringRequest);

    }


}
