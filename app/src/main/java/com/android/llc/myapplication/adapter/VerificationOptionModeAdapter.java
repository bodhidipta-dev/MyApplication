package com.android.llc.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.android.llc.myapplication.R;
import com.android.llc.myapplication.fontmod.Roboto_Light;

import java.util.ArrayList;

/**
 * Created by bodhidipta on 12/09/16.
 */
public class VerificationOptionModeAdapter extends ArrayAdapter<String> {

    Context context;
    Roboto_Light spinertitle;
    ArrayList<String> option_list;


    public VerificationOptionModeAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        option_list = objects;


    }

    @Override
    public int getCount() {
        return option_list.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_text, null);
        }


        spinertitle = (Roboto_Light) convertView.findViewById(R.id.txtspinemwnu);
        spinertitle.setText(option_list.get(position));



        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.actionmenuspiner, null);
        }
//        if (position==0){
//            spinertitle = (Roboto_Light) convertView.findViewById(R.id.txtspinemwnu);
//            spinertitle.setVisibility(View.GONE);
//        }else{
            spinertitle = (Roboto_Light) convertView.findViewById(R.id.txtspinemwnu);
            spinertitle.setVisibility(View.VISIBLE);
            spinertitle.setText(option_list.get(position));
//        }




        return convertView;
    }


}
