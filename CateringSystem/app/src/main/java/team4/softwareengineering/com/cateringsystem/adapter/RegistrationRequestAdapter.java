package team4.softwareengineering.com.cateringsystem.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.activity.AdminHomeActivity;
import team4.softwareengineering.com.cateringsystem.activity.RegistrationRequestActivity;
import team4.softwareengineering.com.cateringsystem.activity.UserDetailsAdminActivity;
import team4.softwareengineering.com.cateringsystem.model.AdminRegistrationRequestModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;


public class RegistrationRequestAdapter extends RecyclerView.Adapter<RegistrationRequestAdapter.MyViewHolder> {

    private ArrayList<DatabaseUsersModel> adminRegistrationRequestModels;

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


    public RegistrationRequestAdapter(ArrayList<DatabaseUsersModel> adminRegistrationRequestModels, Context mContext) {
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

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(adminRegistrationRequestModels.get(position).getUserColumnTimestamp()));  //here your time in miliseconds
        String date = "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        String time = "" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);

        holder.tvUserName.setText(adminRegistrationRequestModels.get(position).getUserColumnFirstName());
        holder.tvDate.setText(date);
        holder.tvTime.setText(time);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,UserDetailsAdminActivity.class);
                intent.putExtra(AppConstants.REGISTRATION_REQUEST,(Serializable) adminRegistrationRequestModels.get(position));
                mContext.startActivity(intent);
            }
        });
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
