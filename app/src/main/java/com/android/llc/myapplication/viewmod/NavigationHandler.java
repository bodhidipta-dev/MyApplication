package com.android.llc.myapplication.viewmod;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.llc.myapplication.R;
import com.android.llc.myapplication.fontmod.Roboto_Medium;


/**
 * Created by bodhidipta on 23/07/17.
 * <!-- * Copyright (c) 2017-->
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
 * -->
 */

public class NavigationHandler {
    private View inflatedView = null;
    private OnHandleInput listener = null;
    private static NavigationHandler instance = null;
    private static Context mContext;

    public static NavigationHandler getInstance(Context context) {
        mContext = context;
        if (instance == null)
            instance = new NavigationHandler();
        return instance;
    }

    public void init(View view, OnHandleInput callback) {
        inflatedView = view;
        listener = callback;

        RecyclerView navigation_handler = inflatedView.findViewById(R.id.navigation_handler);
        navigation_handler.setLayoutManager(new LinearLayoutManager(mContext));
        navigation_handler.setAdapter(new NavigationBarAdapter());
    }

    private NavigationHandler() {
    }


    public interface OnHandleInput {
        void onClickItem(String tag);
    }

    private class NavigationBarAdapter extends RecyclerView.Adapter<NavigationBarAdapter.ViewHolder> {
        @Override
        public NavigationBarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NavigationBarAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.navigation_row_item, parent, false));
        }

        @Override
        public void onBindViewHolder(NavigationBarAdapter.ViewHolder holder, final int position) {
            holder.text_lebel.setText(mContext.getResources().getStringArray(R.array.navigation_item)[position]);
            holder.container_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onClickItem(mContext.getResources().getStringArray(R.array.navigation_item)[position]);
                }
            });
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @Override
        public int getItemCount() {
            return mContext.getResources().getStringArray(R.array.navigation_item).length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout container_row;
            Roboto_Medium text_lebel;

            public ViewHolder(View itemView) {
                super(itemView);
                container_row = itemView.findViewById(R.id.container_row);
                text_lebel = itemView.findViewById(R.id.text_lebel);
            }
        }


    }
}
