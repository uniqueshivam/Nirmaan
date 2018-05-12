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
import com.bapps.saisathvik.nirmaan.Model.wagesmodel;
import com.bapps.saisathvik.nirmaan.Mysingleton;
import com.bapps.saisathvik.nirmaan.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sai Sathvik on 3/23/2018.
 */

public class WageAdapter extends RecyclerView.Adapter<WageAdapter.ViewHolder> {
    private List<wagesmodel> listItems;
    private Context context;
   // private String ROOT_URL="http://uniqueideas.in/hackathon/v1/upload/";

    public WageAdapter(List<wagesmodel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_wagelist,parent,false);
        return new ViewHolder(v,context,listItems);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        wagesmodel listItem = listItems.get(position);
        holder.textView_wages_skilled.setText(" Skilled Labours Wage Rs"+listItem.getAvgwage_skilled_labour());
        holder.textView_wages_semiskilled.setText("Semi-Skilled Labour wage Rs"+listItem.getAvgwage_semiskilled_labours());




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView textView_wages_skilled,textView_wages_semiskilled;
        private CircleImageView circleImageView;

        List<wagesmodel> listItems = new ArrayList<>();
        Context ctx;



        public ViewHolder(View itemView, Context ctx, List<wagesmodel> listItems) {
            super(itemView);
            this.listItems=listItems;
            this.ctx=ctx;


            textView_wages_skilled=(TextView)itemView.findViewById(R.id.avg_wage_of_skilled);
            textView_wages_semiskilled=(TextView)itemView.findViewById(R.id.avg_wage_of_semiskilled);

            circleImageView = (CircleImageView)itemView.findViewById(R.id.article_labour);
        }



    }



}

