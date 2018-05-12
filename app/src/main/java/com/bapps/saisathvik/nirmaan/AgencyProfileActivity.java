package com.bapps.saisathvik.nirmaan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgencyProfileActivity extends AppCompatActivity {
    private TextView acc_name, acc_ownerName, acc_email_txt, acc_phoneNo_txt, acc_add_txt,acc_id_txt;
    private LinearLayout labours_onClick;
    private CircleImageView acc_dp;
    private Button action_call_btn,action_hire_btn;
    String agency_phone, pic;
    String agency_id;
    private String ROOT_URL = "http://uniqueideas.in/hackathon/v1/upload/";
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_profile);
        acc_name = (TextView) findViewById(R.id.acc_name);
        acc_dp = (CircleImageView) findViewById(R.id.acc_dp);
        acc_ownerName = (TextView) findViewById(R.id.acc_owner);
        acc_email_txt = (TextView) findViewById(R.id.acc_email_txt);
        acc_phoneNo_txt = (TextView) findViewById(R.id.acc_phoneNo_txt);
        acc_add_txt = (TextView) findViewById(R.id.acc_add_txt);
        action_call_btn = (Button) findViewById(R.id.action_call_btn);
        action_hire_btn = (Button) findViewById(R.id.action_hire_btn);
        progressDialog=new ProgressDialog(this);
        acc_id_txt = (TextView)findViewById(R.id.acc_id_txt);
        labours_onClick = (LinearLayout)findViewById(R.id.labours_onClick);
        labours_onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent muIntent = new Intent(AgencyProfileActivity.this,AgencyLaboursTextViewActivity.class);
                muIntent.putExtra("agency_current_id",acc_id_txt.getText());
                startActivity(muIntent);
                progressDialog.show();
                finish();
            }
        });

        Intent intent = getIntent();
        String agency_name = intent.getStringExtra("name");
         pic = intent.getStringExtra("pic");
        String agency_owner = intent.getStringExtra("agency owner");
        String agency_email = intent.getStringExtra("agency_email");
        agency_phone = intent.getStringExtra("agency phone");
        String agency_service_area = intent.getStringExtra("agency service area");
         agency_id = intent.getStringExtra("agency id");

        acc_add_txt.setText(agency_service_area);
        acc_phoneNo_txt.setText(agency_phone);
        acc_email_txt.setText(agency_email);
        acc_ownerName.setText(agency_owner);
        acc_name.setText(agency_name);
        acc_id_txt.setText(agency_id);
        action_call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(agency_phone);
            }
        });




        ImageRequest imageRequest = new ImageRequest(ROOT_URL + pic + ".jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                acc_dp.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
        Mysingleton.getInstance(this).addToRequestQueue(imageRequest);



        action_hire_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intenthire = new Intent(AgencyProfileActivity.this,ContractorHireActivity.class);
                intenthire.putExtra("agency_name",acc_name.getText().toString().trim());
                intenthire.putExtra("agency_owner",acc_ownerName.getText().toString().trim());
                intenthire.putExtra("agency_email",acc_email_txt.getText().toString().trim());
                intenthire.putExtra("agency_phone",acc_phoneNo_txt.getText().toString().trim());
                intenthire.putExtra("agency_service_area",acc_add_txt.getText().toString().trim());
                intenthire.putExtra("agency_id",agency_id);
                intenthire.putExtra("pic",pic);
                finish();
                startActivity(intenthire);
                action_hire_btn.setText("Requesting");
            }
        });

//        Intent intent2 = getIntent();
//        acc_name.setText(intent2.getStringExtra("name"));
//        acc_phoneNo_txt.setText(intent2.getStringExtra("phone"));
//        acc_ownerName.setText(intent2.getStringExtra("owner"));
//        acc_email_txt.setText(intent2.getStringExtra("email"));
//        acc_add_txt.setText(intent2.getStringExtra("service_area"));
//








    }

    private void dialPhoneNumber(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
