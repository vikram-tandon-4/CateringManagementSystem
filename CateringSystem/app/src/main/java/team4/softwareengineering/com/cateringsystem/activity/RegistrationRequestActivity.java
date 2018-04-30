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

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.HallsAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.RegistrationRequestAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.AdminRegistrationRequestModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class RegistrationRequestActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private RegistrationRequestAdapter mAdapter;
    private RecyclerView rvRegistrationRequest;
    private Dialog confirmDialog;

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
    protected void onResume() {
        super.onResume();
        mAdapter = new RegistrationRequestAdapter(getRegistrationRequestData(), mContext);
        rvRegistrationRequest.setAdapter(mAdapter);
    }

    private ArrayList<DatabaseUsersModel>  getRegistrationRequestData(){


        ArrayList<DatabaseUsersModel> adminRegistrationRequestModels = new ArrayList<>();


        for(DatabaseUsersModel databaseUser: databaseAdapter.getUsers()){
            if(databaseUser.getUserColumnStatus().equalsIgnoreCase("Pending")){
                adminRegistrationRequestModels.add(databaseUser);
            }
        }

        return adminRegistrationRequestModels;
    }
}
