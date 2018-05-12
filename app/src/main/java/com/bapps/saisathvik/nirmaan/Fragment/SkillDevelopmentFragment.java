package com.bapps.saisathvik.nirmaan.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bapps.saisathvik.nirmaan.Common.Common;
import com.bapps.saisathvik.nirmaan.Interface.ItemClickListener_moment;
import com.bapps.saisathvik.nirmaan.Model.SkillDevelopmentItem;
import com.bapps.saisathvik.nirmaan.R;
import com.bapps.saisathvik.nirmaan.SkillDevelopmentDescription;
import com.bapps.saisathvik.nirmaan.ViewHolder.SkillDevelopmentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillDevelopmentFragment extends Fragment {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference Moments;

    //Firebase UI Adapter

    FirebaseRecyclerOptions<SkillDevelopmentItem> options;
    FirebaseRecyclerAdapter<SkillDevelopmentItem, SkillDevelopmentViewHolder> adapter;

    //View view
    RecyclerView recyclerView;


    public SkillDevelopmentFragment() {
        // Required empty public constructor



        database = FirebaseDatabase.getInstance();
        Moments = database.getReference(Common.STR_MOMENTS);

        options = new FirebaseRecyclerOptions.Builder<SkillDevelopmentItem>()
                .setQuery(Moments,SkillDevelopmentItem.class) //Select All
                .build();


        adapter  = new FirebaseRecyclerAdapter<SkillDevelopmentItem, SkillDevelopmentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final SkillDevelopmentViewHolder holder, int position, @NonNull final SkillDevelopmentItem model) {

                Picasso.with(getActivity())
                        .load(model.getImageLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.moment_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online
                                Picasso.with(getActivity())
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.moment_image, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {

                                                Log.e("ERROR_SAI","Could not fetch the image");

                                            }
                                        });


                            }
                        });

                holder.moment_name.setText(model.getName());


                holder.setItemClickListener_moment(new ItemClickListener_moment() {
                    @Override
                    public void onClick(View view, int position) {
                        //Code later

                        Common.MOMENT_ID_SELECTED = adapter.getRef(position).getKey();
                        Intent intent = new Intent(getActivity(), SkillDevelopmentDescription.class);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public SkillDevelopmentItem getItem(int position) {
                return super.getItem(getItemCount() - 1 - position);
            }

            @Override
            public SkillDevelopmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_skill_development_item,parent,false);

                return new SkillDevelopmentViewHolder(itemView) ;
            }
        };
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View view  = inflater.inflate(R.layout.fragment_skill_development,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_moments);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);






        setMoments();

        return view;
    }

    private void setMoments() {
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    }


