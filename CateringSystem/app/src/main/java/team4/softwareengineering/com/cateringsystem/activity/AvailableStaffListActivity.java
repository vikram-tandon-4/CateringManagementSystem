package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Activity;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.AvailableStaffAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.AvailableStaffListModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class AvailableStaffListActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private AvailableStaffAdapter mAdapter;
    private RecyclerView rvReservedEvents;
    private DatabaseEventsModel databaseEventsModel;
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
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        databaseEventsModel = (DatabaseEventsModel) bundle.getSerializable("EventId");
        databaseAdapter= DatabaseAdapter.getDBAdapterInstance(mContext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Available Staff");

        rvReservedEvents = (RecyclerView)findViewById(R.id.rvReservedEvents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvReservedEvents.setLayoutManager(mLayoutManager);
        rvReservedEvents.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new AvailableStaffAdapter(getStaff(), mContext);
        rvReservedEvents.setAdapter(mAdapter);

        toolbar.inflateMenu(R.menu.assign_staff_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        confirmationDialog();
                        return true;

                    case R.id.assignStaff:
                        Toast.makeText(mContext, "Assign Staff",Toast.LENGTH_LONG).show();

                        StringBuilder stringBuilder = new StringBuilder();

                        ArrayList<AvailableStaffListModel> availableStaffListModels = AvailableStaffAdapter.getStaff();

                        for(AvailableStaffListModel availableStaffListModel: availableStaffListModels){

                            if(availableStaffListModel.isSelected()){
                                stringBuilder.append(availableStaffListModel.getId());
                                stringBuilder.append(",");
                            }
                        }

                        Log.e("Assigned Members",stringBuilder.toString());
                        databaseEventsModel.setEventColumnStaffId(stringBuilder.toString());
                        if(databaseAdapter.updateEvent(databaseEventsModel.getEventColumnId(),databaseEventsModel)){
                            Toast.makeText(mContext,"Staff Assigned",Toast.LENGTH_LONG).show();
                            databaseAdapter.getEvents();
                            ((Activity)mContext).finish();
                        }
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

    private ArrayList<AvailableStaffListModel> getStaff(){

        ArrayList<AvailableStaffListModel> staff = new ArrayList<>();
        List<DatabaseUsersModel> Users= databaseAdapter.getUsers();
        for(int i=0;i<Users.size();i++){
            if(Users.get(i).getUserColumnCategory().equalsIgnoreCase("Caterer Staff")){
                AvailableStaffListModel availableStaffListModel=new AvailableStaffListModel();
                availableStaffListModel.setStaffMemberName(Users.get(i).getUserColumnFirstName());
                availableStaffListModel.setId(Users.get(i).getUserColumnUtaId());
                availableStaffListModel.setSelected(false);
                staff.add(availableStaffListModel);

            }
        }

        return staff;
    }
}
