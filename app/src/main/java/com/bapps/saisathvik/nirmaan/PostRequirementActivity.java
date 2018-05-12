package com.bapps.saisathvik.nirmaan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bapps.saisathvik.nirmaan.Model.Ad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class PostRequirementActivity extends AppCompatActivity {

     MaterialEditText ad;
     Button sendBtn;
    DatabaseReference databaseAds;

    ListView listViewPosts;

    List<Ad> adList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_requirement);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        ad = (MaterialEditText)findViewById(R.id.ad);
        listViewPosts = (ListView)findViewById(R.id.listViewPosts);
        adList = new ArrayList<>();
        databaseAds = FirebaseDatabase.getInstance().getReference("Post");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addPost();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adList.clear();

                for (DataSnapshot artistSnapShot :dataSnapshot.getChildren()){

                    Ad ad = artistSnapShot.getValue(Ad.class);
                    adList.add(ad);
                }

                AdList adapter = new AdList(PostRequirementActivity.this,adList);
                listViewPosts.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    private void addPost() {
   String adver = ad.getText().toString().trim();

   if (!TextUtils.isEmpty(adver))
   {

       String id = databaseAds.push().getKey();
       Ad ad = new Ad(id,adver);

       databaseAds.child(id).setValue(ad);

       Toast.makeText(this,"Your Post is updated",Toast.LENGTH_SHORT).show();
   }
   else {

       Toast.makeText(this,"You should enter the ad",Toast.LENGTH_SHORT).show();
   }


    }
}
