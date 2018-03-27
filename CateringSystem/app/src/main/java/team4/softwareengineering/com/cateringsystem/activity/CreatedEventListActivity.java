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
import team4.softwareengineering.com.cateringsystem.adapter.CreatedEventsListAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.model.CreatedEventModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class CreatedEventListActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private CreatedEventsListAdapter mAdapter;
    private RecyclerView rvCreateEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_event_list);

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

        tvTbTitle.setText("Created Event List");

        rvCreateEvent = (RecyclerView)findViewById(R.id.rvCreateEvent);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCreateEvent.setLayoutManager(mLayoutManager);
        rvCreateEvent.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CreatedEventsListAdapter(getEventsData(), mContext);
        rvCreateEvent.setAdapter(mAdapter);

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

    private ArrayList<CreatedEventModel> getEventsData(){

        ArrayList<CreatedEventModel> events = new ArrayList<>();

        CreatedEventModel createdEventModel = new CreatedEventModel();
        createdEventModel.setStatus("Status: Booked");
        createdEventModel.setEventId("E0103452018");
        createdEventModel.setEvent("Birthday");
        events.add(createdEventModel);

        createdEventModel = new CreatedEventModel();
        createdEventModel.setEventId("E01034532018");
        createdEventModel.setEvent("Farewell");
        createdEventModel.setStatus("Status: Cancelled");
        events.add(createdEventModel);

        createdEventModel = new CreatedEventModel();
        createdEventModel.setEventId("E0105632018");
        createdEventModel.setEvent("Christmas");
        createdEventModel.setStatus("Status: Booked");
        events.add(createdEventModel);

        createdEventModel = new CreatedEventModel();
        createdEventModel.setEventId("E0563032018");
        createdEventModel.setEvent("Birthday");
        createdEventModel.setStatus("Status: Cancelled");
        events.add(createdEventModel);

        createdEventModel = new CreatedEventModel();
        createdEventModel.setEventId("E0103056018");
        createdEventModel.setEvent("Farewell");
        createdEventModel.setStatus("Status: Pending");
        events.add(createdEventModel);

        return events;
    }
}
