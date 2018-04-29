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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.AvailableHallsAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.HallsAdapter;
import team4.softwareengineering.com.cateringsystem.model.AvailableHallModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;
import team4.softwareengineering.com.cateringsystem.model.ReviewResourcesModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class AvailableHallsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private AvailableHallsAdapter mAdapter;
    private RecyclerView rvAvailableHall;
    private static EditText etDate;
    private DatabaseEventsModel dbEvents;
    private Button btnSearch;
    private Dialog confirmDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_hall);

        mContext= this;
        init();

    }

    private void init() {
        btnSearch= (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter = new AvailableHallsAdapter(AppPreferences.getHalls(mContext), mContext,dbEvents);
                rvAvailableHall.setAdapter(mAdapter);
            }
        });
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        dbEvents= (DatabaseEventsModel) bundle.getSerializable("EventId");
        etDate = (EditText) findViewById(R.id.etDate);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new AvailableHallsActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.available_halls);

        rvAvailableHall = (RecyclerView)findViewById(R.id.rvAvailableHall);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAvailableHall.setLayoutManager(mLayoutManager);
        rvAvailableHall.setItemAnimator(new DefaultItemAnimator());



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

    private ArrayList<AvailableHallModel>  getHallData(){

        ArrayList<AvailableHallModel> halls = new ArrayList<>();

        AvailableHallModel availableHallModel = new AvailableHallModel();
        availableHallModel.setCapacity("Capacity: 75");
        availableHallModel.setHallName("Liberty");
        availableHallModel.setPrice("Price: $150/hr");
        halls.add(availableHallModel);

        availableHallModel = new AvailableHallModel();
        availableHallModel.setCapacity("Capacity: 25");
        availableHallModel.setHallName("KC");
        availableHallModel.setPrice("Price: $50/hr");
        halls.add(availableHallModel);

        availableHallModel = new AvailableHallModel();
        availableHallModel.setCapacity("Capacity: 25");
        availableHallModel.setHallName("Shard");
        availableHallModel.setPrice("Price: $50/hr");
        halls.add(availableHallModel);

        availableHallModel = new AvailableHallModel();
        availableHallModel.setCapacity("Capacity: 50");
        availableHallModel.setHallName("Arlington");
        availableHallModel.setPrice("Price: $100/hr");
        halls.add(availableHallModel);

        availableHallModel = new AvailableHallModel();
        availableHallModel.setCapacity("Capacity: 100");
        availableHallModel.setHallName("Maverick");
        availableHallModel.setPrice("Price: $200/hr");
        halls.add(availableHallModel);

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
            c.add( Calendar.MONTH, 9 );
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            etDate.setText(month+"/"+day+"/"+year);
        }
    }
}
