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

import java.util.ArrayList;
import java.util.Calendar;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.activity.AssignedEventDetails;
import team4.softwareengineering.com.cateringsystem.activity.EventDetailsActivity;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;


public class AssignedEventsAdapter extends RecyclerView.Adapter<AssignedEventsAdapter.MyViewHolder> {

    private ArrayList<DatabaseEventsModel> reservedEventsModels;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventName, tvEventId, tvStatus, tvDate, tvTime;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvEventName = (TextView) view.findViewById(R.id.tvEventName);
            tvEventId = (TextView) view.findViewById(R.id.tvEventId);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvTime  = (TextView) view.findViewById(R.id.tvTime);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public AssignedEventsAdapter(ArrayList<DatabaseEventsModel> reservedEventsModels, Context mContext) {
        this.reservedEventsModels = reservedEventsModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reserved_events_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvEventName.setText(reservedEventsModels.get(position).getEventColumnOccasionType());
        holder.tvEventId.setText(reservedEventsModels.get(position).getEventAssignedColumnId());
        holder.tvStatus.setText("Status: "+reservedEventsModels.get(position).getEventColumnStatus());
        holder.tvDate.setText(reservedEventsModels.get(position).getEventColumnDate());
        holder.tvTime.setText(reservedEventsModels.get(position).getEventColumnTime());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext,AssignedEventDetails.class);
                intent.putExtra(AppConstants.ASSIGNED_EVENTS_ACTIVITY,reservedEventsModels.get(position));
                mContext.startActivity(intent);
            }
        });

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return reservedEventsModels.size();
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
