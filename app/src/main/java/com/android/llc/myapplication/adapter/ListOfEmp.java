package com.android.llc.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.llc.myapplication.MainActivity;
import com.android.llc.myapplication.R;
import com.android.llc.myapplication.fontmod.Roboto_Light;
import com.android.llc.myapplication.fontmod.Roboto_Medium;
import com.android.llc.myapplication.viewmod.CropCircleTransformation;
import com.bumptech.glide.Glide;

/**
 * Created by Bodhidipta on 7/23/2017.
 * <!-- * Copyright (c) 2017, The MyApplication-->
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ListOfEmp extends RecyclerView.Adapter<ListOfEmp.ViewHolder> {
    Context mcontext;
    int selected = 2;

    public ListOfEmp(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == selected) {
            holder.contd_bacvk.setBackgroundColor(Color.parseColor("#0095ff"));
            holder.right_chev.setBackgroundResource(R.drawable.ic_right_chevron_white);
            holder.text_id.setTextColor(Color.parseColor("#ffffff"));
            holder.emp_name.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.contd_bacvk.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.right_chev.setBackgroundResource(R.drawable.ic_chevron_right);
            holder.text_id.setTextColor(Color.parseColor("#333333"));
            holder.emp_name.setTextColor(Color.parseColor("#333333"));
        }
        Glide.with(mcontext)
                .load(R.drawable.boy)
                .fitCenter()
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(mcontext))
                .into(holder.image_v);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_v, right_chev;
        Roboto_Light text_id;
        Roboto_Medium emp_name;
        RelativeLayout contd_bacvk;

        public ViewHolder(View itemView) {
            super(itemView);
            image_v = (ImageView) itemView.findViewById(R.id.image_v);
            right_chev = (ImageView) itemView.findViewById(R.id.right_chev);
            text_id = (Roboto_Light) itemView.findViewById(R.id.text_id);
            emp_name = (Roboto_Medium) itemView.findViewById(R.id.emp_name);
            contd_bacvk = (RelativeLayout) itemView.findViewById(R.id.contd_bacvk);
        }
    }
}
