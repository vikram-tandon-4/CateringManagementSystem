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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.RequestedEventsListAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
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
    private LinearLayout llDates;
    //private static EditText etStartTime,etEndTime;
   // private static boolean isStarttime=true;

    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_event_list);

        mContext= this;
        init();

    }

    private void init() {
        llDates = (LinearLayout) findViewById(R.id.llDates);
        databaseAdapter= DatabaseAdapter.getDBAdapterInstance(mContext);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        llDates.setVisibility(View.INVISIBLE);
        llDates.setMinimumWidth(0);
        ViewGroup.LayoutParams layoutParams = llDates.getLayoutParams();
        layoutParams.width=0;
        llDates.setLayoutParams(layoutParams);

       // etEndTime = (EditText) findViewById(R.id.etEndTime);
        //etStartTime = (EditText) findViewById(R.id.etStartTime);
//
//        etStartTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                isStarttime= true;
//                DialogFragment newFragment = new RequestedEventListActivity.DatePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "datePicker");
//            }
//        });

//        etEndTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isStarttime= false;
//                DialogFragment newFragment = new RequestedEventListActivity.DatePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "datePicker");
//            }
//        });

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

        List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        for(int i =0;i<dbEvents.size();i++){
            if(dbEvents.get(i).getEventColumnStatus().equals("PENDING")) {
                RequestedEventModel requestedEventModel = new RequestedEventModel();
                requestedEventModel.setEventId(dbEvents.get(i).getEventAssignedColumnId() + "");
                requestedEventModel.setEvent(dbEvents.get(i).getEventColumnOccasionType());
                events.add(requestedEventModel);
            }
        }
        return events;
    }

//    public static class DatePickerFragment extends DialogFragment
//            implements DatePickerDialog.OnDateSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
//            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
//            return  dialog;
//        }

//        public void onDateSet(DatePicker view, int year, int month, int day) {
//            if(isStarttime){
//                etStartTime.setText(month+"/"+day+"/"+year);
//            }else{
//                etEndTime.setText(month+"/"+day+"/"+year);
//            }
//
//        }
    }

