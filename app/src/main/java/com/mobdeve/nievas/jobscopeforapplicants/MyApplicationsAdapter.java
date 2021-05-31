package com.mobdeve.nievas.jobscopeforapplicants;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyApplicationsAdapter extends RecyclerView.Adapter<MyApplicationsAdapter.MyViewHolder>{

    private ArrayList<Applications> orderArrayList;
    private MyApplicationsAdapter.OnOrderListener mOnOrderListener;

    public MyApplicationsAdapter(MyProfileActivity myProfileActivity, ArrayList<Applications> data, OnOrderListener onOrderListener){
        this.orderArrayList = data;
        this.mOnOrderListener = onOrderListener;
    }

    @NonNull
    @Override
    public MyApplicationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create the ViewGroup by inflating the layout in our activity's context
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_listing_card, parent, false);
        //return an instance of the ViewHolder
        return new MyApplicationsAdapter.MyViewHolder(view, mOnOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyApplicationsAdapter.MyViewHolder holder, int position) {
        holder.setTitle(this.orderArrayList.get(position).getTitle());
        holder.setEmployer(this.orderArrayList.get(position).getEmployer());
        holder.setLocation(this.orderArrayList.get(position).getLocation());
        holder.setDescription(this.orderArrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, employer, location, description ;
        MyApplicationsAdapter.OnOrderListener onOrderListener;

        public MyViewHolder(@NonNull View itemView, MyApplicationsAdapter.OnOrderListener mOnOrderListener) {
            super(itemView);

            //instantiate the views
            this.title = itemView.findViewById(R.id.tvTitle);
            this.employer = itemView.findViewById(R.id.tvEmployer);
            this.location = itemView.findViewById(R.id.tvLocation);
            this.description = itemView.findViewById(R.id.tvDescription);
            this.onOrderListener = mOnOrderListener;

            // sets onclick listener to the whole itemView
            itemView.setOnClickListener(this);
        }

        //setters so we can add in data
        public void setTitle(String title){
            this.title.setText(title);
        }
        public void setEmployer(String employer){
            this.employer.setText(employer);
        }
        public void setLocation(String location){
            this.location.setText(location);
        }
        public void setDescription(String description){
            this.description.setText(description);
        }


        @Override
        public void onClick(View v) {

            onOrderListener.onOrderClick(getAdapterPosition());


        }
    }

    public interface OnOrderListener{
        void onOrderClick(int position);

    }


}
