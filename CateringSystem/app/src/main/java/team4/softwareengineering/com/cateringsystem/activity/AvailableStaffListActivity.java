package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.AvailableStaffAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.model.AvailableStaffListModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class AvailableStaffListActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private AvailableStaffAdapter mAdapter;
    private RecyclerView rvReservedEvents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_events);

        mContext= this;
        init();

    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Available Staff List");

        rvReservedEvents = (RecyclerView)findViewById(R.id.rvReservedEvents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvReservedEvents.setLayoutManager(mLayoutManager);
        rvReservedEvents.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new AvailableStaffAdapter(getStaff(), mContext);
        rvReservedEvents.setAdapter(mAdapter);

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        Toast.makeText(mContext, "Logout",Toast.LENGTH_LONG).show();
                        return true;

                }
                return false;
            }
        });
    }

    private ArrayList<AvailableStaffListModel> getStaff(){

        ArrayList<AvailableStaffListModel> staff = new ArrayList<>();

        AvailableStaffListModel availableStaffListModel = new AvailableStaffListModel();
        availableStaffListModel.setStaffMemberName("Mark");
        staff.add(availableStaffListModel);

        availableStaffListModel = new AvailableStaffListModel();
        availableStaffListModel.setStaffMemberName("Tom");

        staff.add(availableStaffListModel);

        availableStaffListModel = new AvailableStaffListModel();
        availableStaffListModel.setStaffMemberName("Jim");

        staff.add(availableStaffListModel);

        availableStaffListModel = new AvailableStaffListModel();
        availableStaffListModel.setStaffMemberName("Mark");

        staff.add(availableStaffListModel);

        availableStaffListModel = new AvailableStaffListModel();
        availableStaffListModel.setStaffMemberName("Tom");

        staff.add(availableStaffListModel);

        return staff;
    }
}
