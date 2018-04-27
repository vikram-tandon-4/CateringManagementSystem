package team4.softwareengineering.com.cateringsystem.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.HallsAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class HallsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private HallsAdapter mAdapter;
    private RecyclerView rvAvailableHall;
    private static EditText etDate,etTime;
    private Button btnSearch;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_hall);
        mContext= this;
        init();

    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               rvAvailableHall = (RecyclerView)findViewById(R.id.rvAvailableHall);
               RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
               rvAvailableHall.setLayoutManager(mLayoutManager);
               rvAvailableHall.setItemAnimator(new DefaultItemAnimator());
               mAdapter = new HallsAdapter(getHallData(), mContext);
               rvAvailableHall.setAdapter(mAdapter);
           }
        });

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Halls");

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
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

    public HallModel getHall(String s){
        HallModel hallModel = new HallModel();

        if (s.equals("Liberty")) {
            hallModel.setCapacity("Capacity: 200");
            hallModel.setHallName("Liberty");
            hallModel.setLocation("UTA");
            hallModel.setPrice("Price: $150/hr");
        }
        else if(s.equals("KC")) {
            hallModel.setCapacity("Capacity: 300");
            hallModel.setHallName("KC");
            hallModel.setLocation("UTA");
            hallModel.setPrice("Price: $250/hr");
        }
        else if(s.equals("Shard")) {
            hallModel.setCapacity("Capacity: 800");
            hallModel.setHallName("Shard");
            hallModel.setLocation("UTA");
            hallModel.setPrice("Price: $350/hr");
        }
        else if(s.equals("Arlington")) {
            hallModel.setCapacity("Capacity: 500");
            hallModel.setHallName("Arlington");
            hallModel.setLocation("UTA");
            hallModel.setPrice("Price: $100/hr");
        }
        else if(s.equals("Maverick")){
            hallModel.setCapacity("Capacity: 300");
            hallModel.setHallName("Maverick");
            hallModel.setLocation("UTA");
            hallModel.setPrice("Price: $170/hr");
        }
        return hallModel;
    }

    private ArrayList<HallModel>  getHallData(){

        ArrayList<HallModel> halls = new ArrayList<>();
        List<String> availHall = new ArrayList<String>();
        List<DatabaseEventsModel> events = databaseAdapter.getEvents();
        availHall.add("Liberty");
        availHall.add("Shard");
        availHall.add("KC");
        availHall.add("Maverick");
        availHall.add("Arlington");
        for(int i=0;i<events.size();i++){
            if(events.get(i).getEventColumnDate().equals(etDate.getText().toString()) && events.get(i).getEventColumnTime().equals(etTime.getText().toString())){
                availHall.remove(events.get(i).getEventColumnHallId());
                //id to be changed to name
            }
        }
        for(int i=0;i<availHall.size();i++) {
            halls.add(getHall(availHall.get(i)));
        }
        return halls;
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
            etDate.setText(month+"/"+day+"/"+year);
        }
    }
}
