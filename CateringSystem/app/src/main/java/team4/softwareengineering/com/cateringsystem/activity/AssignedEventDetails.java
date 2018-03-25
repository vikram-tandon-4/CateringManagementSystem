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
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class AssignedEventDetails extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private ReservedEventsAdapter mAdapter;
    private RecyclerView rvReservedEvents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assigned_event_details);

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

        tvTbTitle.setText(R.string.assigned_events);

        rvReservedEvents = (RecyclerView)findViewById(R.id.rvReservedEvents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvReservedEvents.setLayoutManager(mLayoutManager);
        rvReservedEvents.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ReservedEventsAdapter(getEventsData(), mContext);
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

    private ArrayList<ReservedEventsModel> getEventsData(){

        ArrayList<ReservedEventsModel> reserved = new ArrayList<>();

        ReservedEventsModel reservedEventsModel = new ReservedEventsModel();
        reservedEventsModel.setDate("03/03/2018");
        reservedEventsModel.setEventId("E0103452018");
        reservedEventsModel.setEventName("Birthday");
        reservedEventsModel.setStatus("Status: Booked");
        reservedEventsModel.setTime("4:00 PM");
        reserved.add(reservedEventsModel);

        reservedEventsModel = new ReservedEventsModel();
        reservedEventsModel.setDate("03/05/2018");
        reservedEventsModel.setEventId("E01034532018");
        reservedEventsModel.setEventName("Farewell");
        reservedEventsModel.setStatus("Status: Cancelled");
        reservedEventsModel.setTime("5:00 PM");
        reserved.add(reservedEventsModel);

        reservedEventsModel = new ReservedEventsModel();
        reservedEventsModel.setDate("04/03/2018");
        reservedEventsModel.setEventId("E0105632018");
        reservedEventsModel.setEventName("Christmas");
        reservedEventsModel.setStatus("Status: Booked");
        reservedEventsModel.setTime("6:00 PM");
        reserved.add(reservedEventsModel);

        reservedEventsModel = new ReservedEventsModel();
        reservedEventsModel.setDate("04/03/2018");
        reservedEventsModel.setEventId("E0563032018");
        reservedEventsModel.setEventName("Birthday");
        reservedEventsModel.setStatus("Status: Cancelled");
        reservedEventsModel.setTime("7:00 PM");
        reserved.add(reservedEventsModel);

        reservedEventsModel = new ReservedEventsModel();
        reservedEventsModel.setDate("03/03/2018");
        reservedEventsModel.setEventId("E0103056018");
        reservedEventsModel.setEventName("Farewell");
        reservedEventsModel.setStatus("Status: Pending");
        reservedEventsModel.setTime("8:00 PM");
        reserved.add(reservedEventsModel);

        return reserved;
    }
}
