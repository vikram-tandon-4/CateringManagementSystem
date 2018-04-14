package team4.softwareengineering.com.cateringsystem.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.model.AdminRegistrationRequestModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;


public class RegistrationRequestAdapter extends RecyclerView.Adapter<RegistrationRequestAdapter.MyViewHolder> {

    private ArrayList<AdminRegistrationRequestModel> adminRegistrationRequestModels;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName, tvDate, tvTime;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public RegistrationRequestAdapter(ArrayList<AdminRegistrationRequestModel> adminRegistrationRequestModels, Context mContext) {
        this.adminRegistrationRequestModels = adminRegistrationRequestModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_registration_request_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        holder.tvUserName.setText(adminRegistrationRequestModels.get(position).getName());
        holder.tvDate.setText(adminRegistrationRequestModels.get(position).getDate());
        holder.tvTime.setText(adminRegistrationRequestModels.get(position).getTime());

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return adminRegistrationRequestModels.size();
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
    }
    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        (holder).clearAnimation();
    }
}
