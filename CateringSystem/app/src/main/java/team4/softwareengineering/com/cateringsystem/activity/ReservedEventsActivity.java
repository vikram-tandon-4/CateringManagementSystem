package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.RequestedEventsListAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class ReservedEventsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private ReservedEventsAdapter mAdapter;
    private RecyclerView rvReservedEvents;
    private DatabaseAdapter databaseAdapter;
    private Dialog confirmDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_events);

        mContext= this;
        init();

    }

    private void init() {
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.reserved_events);

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
                        confirmationDialog();
                        return true;

                }
                return false;
            }
        });
    }
    private void confirmationDialog() {
        confirmDialog = Utils.showConfirmationDialog(mContext);
        confirmDialog.show();

        final TextView btnYes = (TextView) confirmDialog.findViewById(R.id.okLogout);
        final TextView btnNo = (TextView) confirmDialog.findViewById(R.id.cancelLogout);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Logging Out",Toast.LENGTH_LONG).show();
                confirmDialog.dismiss();
                finishAffinity();
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        mAdapter = new ReservedEventsAdapter(getEventsData(), mContext);
        rvReservedEvents.setAdapter(mAdapter);
    }


    private ArrayList<ReservedEventsModel> getEventsData(){
        ArrayList<ReservedEventsModel> events = new ArrayList<>();

        List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        for(int i =0;i<dbEvents.size();i++){
            if(dbEvents.get(i).getEventColumnUserId().equals(AppPreferences.getUtaId(mContext))) {
                ReservedEventsModel reservedEventModel = new ReservedEventsModel();
                reservedEventModel.setEventId(dbEvents.get(i).getEventAssignedColumnId());
                reservedEventModel.setEventName(dbEvents.get(i).getEventColumnOccasionType());
                reservedEventModel.setDate(dbEvents.get(i).getEventColumnDate());
                reservedEventModel.setStatus(dbEvents.get(i).getEventColumnStatus());
                reservedEventModel.setTime(dbEvents.get(i).getEventColumnTime());
                reservedEventModel.setDbId(dbEvents.get(i).getEventColumnId()+"");
                events.add(reservedEventModel);
            }
        }
        return events;
    }
}
