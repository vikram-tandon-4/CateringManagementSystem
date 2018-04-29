package team4.softwareengineering.com.cateringsystem.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.model.AvailableStaffListModel;


public class AvailableStaffAdapter extends RecyclerView.Adapter<AvailableStaffAdapter.MyViewHolder> {

    private static ArrayList<AvailableStaffListModel> availableStaffListModels;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStaffName;
        public CheckBox cbSelectStaff;
        CardView container;
        CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            tvStaffName = (TextView) view.findViewById(R.id.tvStaffName);
            cbSelectStaff = (CheckBox) view.findViewById(R.id.cbSelectStaff);
            container = (CardView) view.findViewById(R.id.nameContainer);
            checkBox= (CheckBox) view.findViewById(R.id.cbSelectStaff);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public AvailableStaffAdapter(ArrayList<AvailableStaffListModel> availableStaffListModels, Context mContext) {
        this.availableStaffListModels = availableStaffListModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_member, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        holder.tvStaffName.setText(availableStaffListModels.get(position).getStaffMemberName());

        setAnimation(holder.container, position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    availableStaffListModels.get(position).setSelected(true);
                }
                else{
                    availableStaffListModels.get(position).setSelected(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return availableStaffListModels.size();
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

    public static ArrayList<AvailableStaffListModel> getStaff(){

        return availableStaffListModels;
    }
}
