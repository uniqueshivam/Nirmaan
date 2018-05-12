package com.bapps.saisathvik.nirmaan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bapps.saisathvik.nirmaan.Model.Ad;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Sai Sathvik on 3/31/2018.
 */

public class AdList extends ArrayAdapter<Ad> {

    private Activity context;
    private List<Ad> adList;

    public AdList(Activity context,List<Ad> adList)
    {
        super(context,R.layout.list_layout,adList);
        this.context = context;
        this.adList = adList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);


        TextView textView = (TextView)listViewItem.findViewById(R.id.textViewName);

        Ad ad = adList.get(position);

        textView.setText(ad.getPost());

        return listViewItem;
    }
}
