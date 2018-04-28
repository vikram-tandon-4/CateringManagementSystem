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

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.activity.EventDetailsActivity;
import team4.softwareengineering.com.cateringsystem.activity.SelectedEventDetailsActivity;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;


public class RequestedEventsListAdapter extends RecyclerView.Adapter<RequestedEventsListAdapter.MyViewHolder> {

    private ArrayList<RequestedEventModel> requestedEventModels;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventName, tvEventId;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvEventName = (TextView) view.findViewById(R.id.tvEvent);
            tvEventId = (TextView) view.findViewById(R.id.tvEventId);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public RequestedEventsListAdapter(ArrayList<RequestedEventModel> requestedEventModels, Context mContext) {
        this.requestedEventModels = requestedEventModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requested_event, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        holder.tvEventName.setText(requestedEventModels.get(position).getEvent());
        holder.tvEventId.setText(requestedEventModels.get(position).getEventId());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,SelectedEventDetailsActivity.class).putExtra("EventId",holder.tvEventId.getText().toString()));
            }
        });

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return requestedEventModels.size();
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
