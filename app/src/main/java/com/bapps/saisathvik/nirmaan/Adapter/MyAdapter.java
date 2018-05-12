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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bapps.saisathvik.nirmaan.AgencyProfileActivity;
import com.bapps.saisathvik.nirmaan.Home;
import com.bapps.saisathvik.nirmaan.Model.ListItem;
import com.bapps.saisathvik.nirmaan.Mysingleton;
import com.bapps.saisathvik.nirmaan.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sai Sathvik on 3/23/2018.
 */

 public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;
    private String ROOT_URL="http://uniqueideas.in/hackathon/v1/upload/";

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_under_recycle,parent,false);
        return new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.textView_agency_name.setText(listItem.getAgency_name());
        holder.textView_agency_owner_db.setText(listItem.getAgency_owner());
        holder.textView_agency_phone_db.setText(listItem.getAgency_phone());
        ImageRequest imageRequest = new ImageRequest(ROOT_URL + listItem.getAgency_id()+".jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.circleImageView.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
        Mysingleton.getInstance(context).addToRequestQueue(imageRequest);


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView_agency_name,textView_agency_owner_db,textView_agency_phone_db;
        private CircleImageView circleImageView;

        List<ListItem> listItems = new ArrayList<ListItem>();
        Context ctx;



        public ViewHolder(View itemView,Context ctx,List<ListItem> listItems) {
            super(itemView);
            this.listItems=listItems;
            this.ctx=ctx;
            itemView.setOnClickListener(this);

            textView_agency_name=(TextView)itemView.findViewById(R.id.agency_name_db);
            textView_agency_owner_db=(TextView)itemView.findViewById(R.id.agency_owner_db);
            textView_agency_phone_db=(TextView)itemView.findViewById(R.id.agency_phone_db);
            circleImageView = (CircleImageView)itemView.findViewById(R.id.article_image);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ListItem listItem= this.listItems.get(position);
            Intent i = new Intent(this.ctx,AgencyProfileActivity.class);
            i.putExtra("name",listItem.getAgency_name());
            i.putExtra("pic",listItem.getAgency_id());
            i.putExtra("agency_email",listItem.getAgency_email());
            i.putExtra("agency owner",listItem.getAgency_owner());
            i.putExtra("agency phone",listItem.getAgency_phone());
            i.putExtra("agency service area",listItem.getAgency_service_area());
            i.putExtra("agency id",listItem.getAgency_id());
            this.ctx.startActivity(i);


        }
    }



}

