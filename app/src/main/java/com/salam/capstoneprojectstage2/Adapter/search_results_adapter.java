package com.salam.capstoneprojectstage2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.salam.capstoneprojectstage2.Models.search_results_model;
import com.salam.capstoneprojectstage2.R;
import com.salam.capstoneprojectstage2.Search_query_UI.caseLaw_details_fragment;
import com.salam.capstoneprojectstage2.widget.app_details_widget;

import java.util.List;


public class search_results_adapter extends RecyclerView.Adapter<search_results_adapter.RecyclerViewHolder> {
        private List<search_results_model> CaseList;
        private final Activity activity;
    boolean isPhone;

        public search_results_adapter(Activity activity, List<search_results_model> CaseList) {
            this.CaseList = CaseList;
            this.activity = activity;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.results_item, parent, false));
        }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final search_results_model caseList_result = CaseList.get(position);
        holder.title.setText(caseList_result.getTitle());
        holder.jurisdiciton.setText(caseList_result.getJurisdiction());
        holder.date.setText(caseList_result.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isPhone = activity.getResources().getBoolean(R.bool.is_phone);
                if (!isPhone) { //it's a tablet
                    Intent i = new Intent(activity, app_details_widget.class);
                    i.setAction(app_details_widget.UPDATE_ACTION);
                    i.putExtra("id",caseList_result.getCaseid());
                    activity.sendBroadcast(i);
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new caseLaw_details_fragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container2, myFragment).addToBackStack(null).commit();
                    Bundle args = new Bundle();
                    args.putString("id", caseList_result.getCaseid());
                    args.putString("j", caseList_result.getJurisdiction());
                    myFragment.setArguments(args);

                } else { //it's a phone, not a tablet
                    Intent i = new Intent(activity, app_details_widget.class);
                    i.setAction(app_details_widget.UPDATE_ACTION);
                    i.putExtra("id",caseList_result.getCaseid());
                    activity.sendBroadcast(i);
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new caseLaw_details_fragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
                    Bundle args = new Bundle();
                    args.putString("id", caseList_result.getCaseid());
                    args.putString("j", caseList_result.getJurisdiction());
                    myFragment.setArguments(args);

                }







            }
        });
    }

        @Override
        public int getItemCount() {
            return CaseList.size();
        }

        public void addItems(List<search_results_model> caseList) {
            this.CaseList = caseList;
            notifyDataSetChanged();
        }

        static class RecyclerViewHolder extends RecyclerView.ViewHolder {
            final TextView title;
            final TextView jurisdiciton;
            final TextView date;
            RecyclerViewHolder(View view) {
                super(view);
                title =  view.findViewById(R.id.case_title);
                jurisdiciton = view.findViewById(R.id.case_jurisdiction);
                date = view.findViewById(R.id.case_date);

            }
        }
    }


