package com.bapps.saisathvik.nirmaan;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bapps.saisathvik.nirmaan.Common.Common;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SkillDevelopmentDescription extends AppCompatActivity {


    Query query;

    private ImageView mimage;
    private TextView mTitle;
    private TextView mDesc;
    private TextView mSum;

    private FloatingActionButton fabSpeak;

    TextToSpeech ttsobject;
    int result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_development_description);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);



        mimage = (ImageView)findViewById(R.id.image);
        mTitle = (TextView)findViewById(R.id.moment_desc_title);
        mDesc = (TextView)findViewById(R.id.moment_desc_desc);
        mSum = (TextView)findViewById(R.id.moment_desc_Sum);
        fabSpeak = (FloatingActionButton)findViewById(R.id.fabSpeak);



        loadMomentDesc();

        ttsobject = new TextToSpeech(SkillDevelopmentDescription.this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if (status == TextToSpeech.SUCCESS){

                            result = ttsobject.setLanguage(Locale.UK);

                        }
                        else {

                            Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        fabSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ttsobject.isSpeaking() == false){

                    //image
                    fabSpeak.setImageResource(R.drawable.ic_action_ofvolume);



                    if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {

                        Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();


                    }
                    else{


                        String title = mTitle.getText().toString();
                        String desc = mDesc.getText().toString();
                        String sum = mSum.getText().toString();


                        ttsobject.speak(title + desc + sum, TextToSpeech.QUEUE_FLUSH, null);

                    }

                }else{

                    //image
                    fabSpeak.setImageResource(R.drawable.ic_volume_name);





                    ttsobject.stop();

                }

            }
        });
    }


    @Override
    protected void onDestroy(){

        super.onDestroy();

        if (ttsobject != null)
            ttsobject.stop();
        ttsobject.shutdown();
    }


    private void loadMomentDesc() {



        query = FirebaseDatabase.getInstance().getReference(Common.STR_MOMENT_STORIES)
                .orderByChild("categoryId").equalTo(Common.MOMENT_ID_SELECTED);

        query.keepSynced(true);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String image = dataSnapshot.child("imageLink").getValue().toString();
                String title = dataSnapshot.child("momentTitle").getValue().toString();
                String desc = dataSnapshot.child("momentDesc").getValue().toString();
                String sum = dataSnapshot.child("momentSum").getValue().toString();


                mTitle.setText(title);
                mDesc.setText(desc);
                mSum.setText(sum);

                Picasso.with(SkillDevelopmentDescription.this).
                        load(image).
                        error(R.drawable.ic_terrain_black_24dp).
                        into(mimage);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }
}
