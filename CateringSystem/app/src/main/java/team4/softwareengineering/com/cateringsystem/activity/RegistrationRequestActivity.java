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
import team4.softwareengineering.com.cateringsystem.adapter.HallsAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.RegistrationRequestAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.AdminRegistrationRequestModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class RegistrationRequestActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private RegistrationRequestAdapter mAdapter;
    private RecyclerView rvRegistrationRequest;

    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_request);

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

        tvTbTitle.setText("Registration Requests");

        rvRegistrationRequest = (RecyclerView)findViewById(R.id.rvRegistrationRequest);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvRegistrationRequest.setLayoutManager(mLayoutManager);
        rvRegistrationRequest.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RegistrationRequestAdapter(getRegistrationRequestData(), mContext);
        rvRegistrationRequest.setAdapter(mAdapter);

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

    private ArrayList<DatabaseUsersModel>  getRegistrationRequestData(){


        ArrayList<DatabaseUsersModel> adminRegistrationRequestModels = new ArrayList<>();


        for(DatabaseUsersModel databaseUser: databaseAdapter.getUsers()){
            if(databaseUser.getUserColumnStatus().equalsIgnoreCase("PENDING")){
                adminRegistrationRequestModels.add(databaseUser);
            }
        }

        return adminRegistrationRequestModels;


//        ArrayList<AdminRegistrationRequestModel> adminRegistrationRequestModels = new ArrayList<>();
//
//        AdminRegistrationRequestModel registrationRequestModel = new AdminRegistrationRequestModel();
//        registrationRequestModel.setName("Pradeep");
//        registrationRequestModel.setTime("12:00 PM");
//        registrationRequestModel.setDate("12/12/2018");
//        adminRegistrationRequestModels.add(registrationRequestModel);
//
//        registrationRequestModel = new AdminRegistrationRequestModel();
//        registrationRequestModel.setName("Ankur");
//        registrationRequestModel.setTime("12:00 PM");
//        registrationRequestModel.setDate("12/12/2018");
//        adminRegistrationRequestModels.add(registrationRequestModel);
//
//        registrationRequestModel = new AdminRegistrationRequestModel();
//        registrationRequestModel.setName("Roopam");
//        registrationRequestModel.setTime("12:00 PM");
//        registrationRequestModel.setDate("12/12/2018");
//        adminRegistrationRequestModels.add(registrationRequestModel);
//
//        registrationRequestModel = new AdminRegistrationRequestModel();
//        registrationRequestModel.setName("Gangsd");
//        registrationRequestModel.setTime("12:00 PM");
//        registrationRequestModel.setDate("12/12/2018");
//        adminRegistrationRequestModels.add(registrationRequestModel);
//
//        registrationRequestModel = new AdminRegistrationRequestModel();
//        registrationRequestModel.setName("Victoria");
//        registrationRequestModel.setTime("12:00 PM");
//        registrationRequestModel.setDate("12/12/2018");
//        adminRegistrationRequestModels.add(registrationRequestModel);

 //       return adminRegistrationRequestModels;
    }
}
