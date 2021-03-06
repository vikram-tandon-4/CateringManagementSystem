package team4.softwareengineering.com.cateringsystem.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.CreatedEventsListAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.CreatedEventModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class CreatedEventListActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private CreatedEventsListAdapter mAdapter;
    private RecyclerView rvCreateEvent;
    private static EditText etStartTime,etEndTime;
    private static boolean isStarttime=true;
    private DatabaseAdapter databaseAdapter;
    private static boolean isComingBackFromPreviousScreen = false;
    private Dialog confirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_event_list);

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

        etEndTime = (EditText) findViewById(R.id.etEndTime);
        etStartTime = (EditText) findViewById(R.id.etStartTime);

        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isStarttime= true;
                DialogFragment newFragment = new CreatedEventListActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStarttime= false;
                DialogFragment newFragment = new CreatedEventListActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        etEndTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapter = new CreatedEventsListAdapter(getEventsData(), mContext);
                rvCreateEvent.setAdapter(mAdapter);
            }
        });
        tvTbTitle.setText("Created Event List");

        rvCreateEvent = (RecyclerView)findViewById(R.id.rvCreateEvent);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCreateEvent.setLayoutManager(mLayoutManager);
        rvCreateEvent.setItemAnimator(new DefaultItemAnimator());



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

    @Override
    protected void onPause() {
        super.onPause();
        isComingBackFromPreviousScreen = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isComingBackFromPreviousScreen){
            mAdapter = new CreatedEventsListAdapter(getEventsData(), mContext);
            rvCreateEvent.setAdapter(mAdapter);
        }
    }

    private ArrayList<DatabaseEventsModel> getEventsData(){

        ArrayList<CreatedEventModel> events = new ArrayList<>();

//        List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
//        for(int i =0;i<dbEvents.size();i++){
//            if(dbEvents.get(i).getEventColumnCatererId().equals(AppPreferences.getUtaId(mContext))) {
//                CreatedEventModel createdEventModel = new CreatedEventModel();
//                createdEventModel.setEventId(dbEvents.get(i).getEventAssignedColumnId() + "");
//                createdEventModel.setEvent(dbEvents.get(i).getEventColumnOccasionType());
//                createdEventModel.setStatus(dbEvents.get(i).getEventColumnStatus());
//                events.add(createdEventModel);
//            }
//        }

        List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        ArrayList<DatabaseEventsModel> dbCatererEvents = new ArrayList<DatabaseEventsModel>();
        for(int i =0;i<dbEvents.size();i++){
            if(dbEvents.get(i).getEventColumnCatererId().equals(AppPreferences.getUtaId(mContext))) {
//                CreatedEventModel createdEventModel = new CreatedEventModel();
//                createdEventModel.setEventId(dbEvents.get(i).getEventAssignedColumnId() + "");
//                createdEventModel.setEvent(dbEvents.get(i).getEventColumnOccasionType());
//                createdEventModel.setStatus(dbEvents.get(i).getEventColumnStatus());
                dbCatererEvents.add(dbEvents.get(i));
//                events.add(createdEventModel);
            }
        }

        return dbCatererEvents;
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
            c.add( Calendar.MONTH, 9 );
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
