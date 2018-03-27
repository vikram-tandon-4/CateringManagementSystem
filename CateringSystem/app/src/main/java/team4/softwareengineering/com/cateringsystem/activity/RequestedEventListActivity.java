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
import team4.softwareengineering.com.cateringsystem.adapter.RequestedEventsListAdapter;
import team4.softwareengineering.com.cateringsystem.model.CreatedEventModel;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class RequestedEventListActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private RequestedEventsListAdapter mAdapter;
    private RecyclerView rvRequestedEvent;

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

        tvTbTitle.setText("Requested Event List");

        rvRequestedEvent = (RecyclerView)findViewById(R.id.rvCreateEvent);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvRequestedEvent.setLayoutManager(mLayoutManager);
        rvRequestedEvent.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RequestedEventsListAdapter(getEventsData(), mContext);
        rvRequestedEvent.setAdapter(mAdapter);

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

    private ArrayList<RequestedEventModel> getEventsData(){

        ArrayList<RequestedEventModel> events = new ArrayList<>();

        RequestedEventModel requestedEventModel = new RequestedEventModel();
        requestedEventModel.setEventId("E0103452018");
        requestedEventModel.setEvent("Birthday");
        events.add(requestedEventModel);

        requestedEventModel = new RequestedEventModel();
        requestedEventModel.setEventId("E01034532018");
        requestedEventModel.setEvent("Farewell");
        events.add(requestedEventModel);

        requestedEventModel = new RequestedEventModel();
        requestedEventModel.setEventId("E0105632018");
        requestedEventModel.setEvent("Christmas");
        events.add(requestedEventModel);

        requestedEventModel = new RequestedEventModel();
        requestedEventModel.setEventId("E0563032018");
        requestedEventModel.setEvent("Birthday");
        events.add(requestedEventModel);

        requestedEventModel = new RequestedEventModel();
        requestedEventModel.setEventId("E0103056018");
        requestedEventModel.setEvent("Farewell");
        events.add(requestedEventModel);

        return events;
    }
}
