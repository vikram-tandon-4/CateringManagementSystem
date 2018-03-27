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
import team4.softwareengineering.com.cateringsystem.model.HallModel;


public class HallsAdapter extends RecyclerView.Adapter<HallsAdapter.MyViewHolder> {

    private ArrayList<HallModel> hallsData;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHallName, tvPrice, tvCapacity, tvLocation;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvHallName = (TextView) view.findViewById(R.id.tvHallName);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvLocation = (TextView) view.findViewById(R.id.tvLocation);
            tvCapacity = (TextView) view.findViewById(R.id.tvCapacity);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public HallsAdapter(ArrayList<HallModel> hallData, Context mContext) {
        this.hallsData = hallData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.halls_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        holder.tvHallName.setText(hallsData.get(position).getHallName());
        holder.tvPrice.setText(hallsData.get(position).getPrice());
        holder.tvCapacity.setText(hallsData.get(position).getCapacity());
        holder.tvLocation.setText(hallsData.get(position).getLocation());

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return hallsData.size();
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
