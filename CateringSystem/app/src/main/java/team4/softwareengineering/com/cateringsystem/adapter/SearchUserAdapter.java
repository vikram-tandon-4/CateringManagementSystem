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
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.activity.SearchUserDetailsInAdminActivity;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.model.SearchUserModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;


public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.MyViewHolder> {

    private List<DatabaseUsersModel> searchUserModels;
    private List<DatabaseUsersModel> mFilteredList;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public SearchUserAdapter(List<DatabaseUsersModel> searchUserModels, Context mContext) {
        this.searchUserModels = searchUserModels;
        mFilteredList = searchUserModels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_user_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {



        holder.tvUserName.setText(mFilteredList.get(position).getUserColumnFirstName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SearchUserDetailsInAdminActivity.class);
                intent.putExtra(AppConstants.SEARCH_USER_DETAILS,mFilteredList.get(position));
                mContext.startActivity(intent);
            }
        });
        setAnimation(holder.container, position);
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = searchUserModels;
                } else {

                    ArrayList<DatabaseUsersModel> filteredList = new ArrayList<>();

                    for (DatabaseUsersModel searchUser : searchUserModels) {
//                        if (searchUser.getName().toLowerCase().contains(charString) || searchUser.getName().toLowerCase().contains(charString) || searchUser.getVer().toLowerCase().contains(charString)) {
                        if (searchUser.getUserColumnFirstName().toLowerCase().contains(charString)) {

                            filteredList.add(searchUser);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<DatabaseUsersModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
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
