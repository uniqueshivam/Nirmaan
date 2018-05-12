package com.bapps.saisathvik.nirmaan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bapps.saisathvik.nirmaan.Fragment.AgencyListFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContractorHireActivity extends AppCompatActivity {


    private Button id_submit;
    private Button action_hire_btn;
    private MaterialEditText id_contractor_id,id_company_name,id_contractor_owner,id_mail_contractor,id_contractor_phone,id_project_name,
            id_hire_date,id_contractor_hireduration,id_agencyId_Autofill;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_hire);
        id_contractor_id = (MaterialEditText)findViewById(R.id.id_contractor_id);
        id_company_name = (MaterialEditText)findViewById(R.id.id_contractor_name);
        id_contractor_owner  = (MaterialEditText)findViewById(R.id.id_contractor_owner) ;
        id_mail_contractor = (MaterialEditText)findViewById(R.id.id_mail_contractor);
        id_contractor_phone = (MaterialEditText)findViewById(R.id.id_contractor_phone);
        id_project_name = (MaterialEditText)findViewById(R.id.id_project_name);
        id_hire_date = (MaterialEditText)findViewById(R.id.id_hire_date);
        id_contractor_hireduration = (MaterialEditText)findViewById(R.id.id_contractor_hireduration) ;
        id_agencyId_Autofill = (MaterialEditText)findViewById(R.id.id_agencyId_Autofill);
        progressDialog=new ProgressDialog(this);


        id_submit = (Button)findViewById(R.id.id_submit);


        Intent intent = getIntent();
         final String name=intent.getStringExtra("agency_name");
          final String phone=intent.getStringExtra("agency_phone");
        final String owner=intent.getStringExtra("agency_owner");
        final String email=intent.getStringExtra("agency_email");
        final String service_area=intent.getStringExtra("agency_service_area");
        final String agency_id=intent.getStringExtra("agency_id");
        final String pic=intent.getStringExtra("pic");


        //setting autofill
        id_agencyId_Autofill.setText(agency_id);



        id_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_contractor();
                Intent idhiresubmit = new Intent(ContractorHireActivity.this,AgencyProfileActivity.class);
                idhiresubmit.putExtra("name",name);
                idhiresubmit.putExtra("agency phone",phone);
                idhiresubmit.putExtra("agency owner",owner);
                idhiresubmit.putExtra("agency_email",email);
                idhiresubmit.putExtra("agency service area",service_area);
                idhiresubmit.putExtra("agency id",agency_id);
                idhiresubmit.putExtra("pic",pic);
                startActivity(idhiresubmit);
                finish();


            }
        });
    }

    private void register_contractor()
    {
        final String getcontractor_id=id_contractor_id.getText().toString().trim();
        final String getcompany_name=id_company_name.getText().toString().trim();
        final String getcontractor_owner = id_contractor_owner.getText().toString().trim();
        final String getcontractor_mail = id_mail_contractor.getText().toString().trim();
        final String getcontractor_phone = id_contractor_phone.getText().toString().trim();
        final String getcontractor_projetct_name = id_project_name.getText().toString().trim();
        final String getcontractor_hire_date=id_hire_date.getText().toString().trim();
        final String getcontractor_hireduration=id_contractor_hireduration.getText().toString().trim();
        final String getagency_id=id_agencyId_Autofill.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest =new StringRequest(Request.Method.POST, constants.CONTRACTOR_FORM_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ContractorHireActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("contractor_id",getcontractor_id);
                params.put("contractor_name",getcontractor_owner);
                params.put("company_name",getcompany_name);
                params.put("contractor_email",getcontractor_mail);
                params.put("contractor_phone",getcontractor_phone);
                params.put("project_name",getcontractor_projetct_name);
                params.put("hire_date",getcontractor_hire_date);
                params.put("hire_duration",getcontractor_hireduration);
                params.put("agency_id",getagency_id);

                return params;
            }
        };
        requesthandler.getInstance(this).addToRequestQueue(stringRequest);



    }
}
