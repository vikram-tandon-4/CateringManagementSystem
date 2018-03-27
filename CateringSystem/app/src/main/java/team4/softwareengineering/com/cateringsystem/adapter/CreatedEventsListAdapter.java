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
import team4.softwareengineering.com.cateringsystem.model.CreatedEventModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;


public class CreatedEventsListAdapter extends RecyclerView.Adapter<CreatedEventsListAdapter.MyViewHolder> {

    private ArrayList<CreatedEventModel> createdEventsListModels;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventName, tvEventId, tvStatus;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvEventName = (TextView) view.findViewById(R.id.tvEventName);
            tvEventId = (TextView) view.findViewById(R.id.tvEventId);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public CreatedEventsListAdapter(ArrayList<CreatedEventModel> createdEventModels, Context mContext) {
        this.createdEventsListModels = createdEventModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.created_events_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        holder.tvEventName.setText(createdEventsListModels.get(position).getEvent());
        holder.tvEventId.setText(createdEventsListModels.get(position).getEventId());
        holder.tvStatus.setText(createdEventsListModels.get(position).getStatus());

      setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return createdEventsListModels.size();
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
