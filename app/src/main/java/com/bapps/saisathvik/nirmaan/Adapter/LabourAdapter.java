package com.bapps.saisathvik.nirmaan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bapps.saisathvik.nirmaan.AgencyProfileActivity;
import com.bapps.saisathvik.nirmaan.LabourProfileActivity;
import com.bapps.saisathvik.nirmaan.Model.LaboursItem;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.bapps.saisathvik.nirmaan.Mysingleton;
import com.bapps.saisathvik.nirmaan.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sai Sathvik on 3/26/2018.
 */

public class LabourAdapter extends RecyclerView.Adapter<LabourAdapter.ViewHolder> {

    private List<LaboursItem> listItems;
    private Context context;
  //  private String ROOT_URL="http://uniqueideas.in/hackathon/v1/upload/";

    public LabourAdapter(List<LaboursItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_labour,parent,false);
        return  new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        LaboursItem listItem = listItems.get(position);
        holder.textView_labour_name.setText(listItem.getLabour_name());
        holder.textView_labour_address_db.setText(listItem.getLabour_address());
        holder.textView_labour_skill_db.setText(listItem.getLabour_skill());
//        ImageRequest imageRequest = new ImageRequest(ROOT_URL + listItem.getAgency_id()+".jpg", new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                holder.circleImageView.setImageBitmap(response);
//
//            }
//        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Toast.makeText(context, "Error Loading", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        Mysingleton.getInstance(context).addToRequestQueue(imageRequest);
//

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView_labour_name,textView_labour_address_db,textView_labour_skill_db;
       // private CircleImageView circleImageView;

        List<LaboursItem> listItems = new ArrayList<>();
        Context ctx;



        public ViewHolder(View itemView, Context ctx, List<LaboursItem> listItems) {
            super(itemView);
            this.listItems=listItems;
            this.ctx=ctx;
            itemView.setOnClickListener(this);

            textView_labour_name=(TextView)itemView.findViewById(R.id.labour_name_db);
            textView_labour_address_db=(TextView)itemView.findViewById(R.id.labour_address_db);
            textView_labour_skill_db=(TextView)itemView.findViewById(R.id.labour_skill_db);
           // circleImageView = (CircleImageView)itemView.findViewById(R.id.article_image);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            LaboursItem listItem= this.listItems.get(position);
            Intent i = new Intent(this.ctx,LabourProfileActivity.class);
            i.putExtra("aadhar",listItem.getAadhar());
            i.putExtra("name",listItem.getLabour_name());
            i.putExtra("labour_address",listItem.getLabour_address());
            i.putExtra("labour_phone",listItem.getLabour_phone());
            i.putExtra("labour_skill",listItem.getLabour_skill());
            i.putExtra("labour_skilled",listItem.getLabour_skillset());
            i.putExtra("labour_gender",listItem.getgender());
            i.putExtra("labour-blood",listItem.getBlood());
            //i.putExtra("agency id",listItem.getAgency_id());
            this.ctx.startActivity(i);


        }
    }


}
