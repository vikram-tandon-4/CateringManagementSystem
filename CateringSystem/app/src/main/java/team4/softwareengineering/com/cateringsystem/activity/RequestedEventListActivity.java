package team4.softwareengineering.com.cateringsystem.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

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
    private static EditText etStartTime,etEndTime;
    private static boolean isStarttime=true;

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

        etEndTime = (EditText) findViewById(R.id.etEndTime);
        etStartTime = (EditText) findViewById(R.id.etStartTime);

        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isStarttime= true;
                DialogFragment newFragment = new RequestedEventListActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStarttime= false;
                DialogFragment newFragment = new RequestedEventListActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            if(isStarttime){
                etStartTime.setText(month+"/"+day+"/"+year);
            }else{
                etEndTime.setText(month+"/"+day+"/"+year);
            }

        }
    }
}
