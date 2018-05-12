package com.bapps.saisathvik.nirmaan.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bapps.saisathvik.nirmaan.Interface.ItemClickListener_moment;
import com.bapps.saisathvik.nirmaan.R;

/**
 * Created by Sai Sathvik on 3/28/2018.
 */

public class SkillDevelopmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView moment_name;
    public ImageView moment_image;

    ItemClickListener_moment itemClickListener_moment;

    public void setItemClickListener_moment(ItemClickListener_moment itemClickListener_moment){
        this.itemClickListener_moment = itemClickListener_moment;
    }


    public SkillDevelopmentViewHolder(View itemView) {
        super(itemView);

        moment_name = (TextView)itemView.findViewById(R.id.name);
        moment_image = (ImageView)itemView.findViewById(R.id.image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        itemClickListener_moment.onClick(view,getAdapterPosition());


    }
}
