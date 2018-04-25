package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;

/**
 * Created by vikra on 3/24/2018.
 */

public class UserDetailsAdminActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    private Button btnApprove, btnReject;
    private TextView tvUserName, tvFirstName, tvLastName, tvEmailId,tvPassword, tvAddress,tvCategory;
    private DatabaseUsersModel databaseUsersModel;

    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mContext= this;
        init();
    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        btnApprove = (Button) findViewById(R.id.btnApprove);
        btnReject = (Button) findViewById(R.id.btnReject);
        btnApprove.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvEmailId = (TextView) findViewById(R.id.tvEmailId);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvAddress = (TextView) findViewById(R.id.tvAddress) ;
        tvCategory = (TextView) findViewById(R.id.tvCategory);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.user_event_details);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        databaseUsersModel = (DatabaseUsersModel) getIntent().getSerializableExtra(AppConstants.REGISTRATION_REQUEST);
        tvUserName.setText(databaseUsersModel.getUserColumnUtaId());
        tvFirstName.setText(databaseUsersModel.getUserColumnFirstName());
        tvLastName.setText(databaseUsersModel.getUserColumnLastName());
        tvEmailId.setText(databaseUsersModel.getUserColumnEmailId());
        tvPassword.setText(databaseUsersModel.getUserColumnPassword());
        tvCategory.setText(databaseUsersModel.getUserColumnCategory());
        tvAddress.setText(databaseUsersModel.getUserColumnAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.btnApprove:
                databaseUsersModel.setUserColumnStatus(AppConstants.APPROVED);
                if(databaseAdapter.updateUserProfile(databaseUsersModel.getUserColumnUserId(),databaseUsersModel))
                databaseAdapter.getUsers();
                break;

            case R.id.btnReject:
                databaseAdapter.deleteUser(databaseUsersModel.getUserColumnUserId());
                databaseAdapter.getUsers();
                break;

            default:
                break;
        }
    }
}
