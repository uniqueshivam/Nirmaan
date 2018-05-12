package com.bapps.saisathvik.nirmaan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LabourProfileActivity extends AppCompatActivity {

    TextView acc_aadhar_text,acc_labourname_txt,acc_phoneNo_txt,acc_add_txt,id_skill,id_skilllevel,number_labours,gender,blood;
    private Button action_call_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_profile);
        acc_aadhar_text = (TextView)findViewById(R.id.acc_aadhar_text);
        //acc_add_txt.setVisibility(View.GONE);
        acc_labourname_txt = (TextView)findViewById(R.id.acc_labourname_txt);
        acc_phoneNo_txt = (TextView)findViewById(R.id.acc_phoneNo_txt);
        acc_add_txt =  (TextView)findViewById(R.id.acc_add_txt);
        id_skill = (TextView)findViewById(R.id.id_skill);
        id_skilllevel = (TextView)findViewById(R.id.id_skilllevel);
        action_call_btn=(Button)findViewById(R.id.action_call_btn_labour);
        number_labours = (TextView)findViewById(R.id.number_labours);
        gender=(TextView) findViewById(R.id.acc_gender_txt);
        blood=(TextView)findViewById(R.id.number_labours);




        Intent intent=getIntent();
        String labour_aadhar=intent.getStringExtra("aadhar");
        String labour_name = intent.getStringExtra("name");
        String labour_address=intent.getStringExtra("labour_address");
        final String labour_phone=intent.getStringExtra("labour_phone");
        String labour_skill = intent.getStringExtra("labour_skill");
        String labour_skilled=intent.getStringExtra("labour_skilled");
        String labour_gender=intent.getStringExtra("labour_gender");
        String labour_blood=intent.getStringExtra("labour-blood");

        acc_aadhar_text.setText(labour_aadhar);
        acc_labourname_txt.setText(labour_name);
        acc_phoneNo_txt.setText(labour_phone);
        acc_add_txt.setText(labour_address);
        id_skill.setText(labour_skill);
        id_skilllevel.setText(labour_skilled);
        gender.setText(labour_gender);
        number_labours.setText(labour_blood);

        action_call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(labour_phone);
            }
        });





    }

    private void dialPhoneNumber(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
