package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

import static android.widget.TextView.BufferType.EDITABLE;

/**
 * Created by vikra on 3/24/2018.
 */

public class UpdateProfileActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    private EditText etFirstName,etLastName, etEmail,etPhoneNumber,etAddress,etPassword;
    private TextView btnUpdateProfile;
    private Dialog confirmDialog;
    private DatabaseAdapter databaseAdapter;
    int userId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mContext= this;
        init();


    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnUpdateProfile = (TextView) findViewById(R.id.btnUpdateProfile);


        //set edit texts to the profile information of current user
        for(DatabaseUsersModel databaseUser: databaseAdapter.getUsers()) {
            if (databaseUser.getUserColumnUtaId().equalsIgnoreCase(AppPreferences.getUtaId(mContext))) {

                etFirstName.setText(databaseUser.getUserColumnFirstName());
                etLastName.setText(databaseUser.getUserColumnLastName());
                etAddress.setText(databaseUser.getUserColumnAddress());
                etEmail.setText(databaseUser.getUserColumnEmailId());
                etPhoneNumber.setText(databaseUser.getUserColumnPhoneNumber());
                etPassword.setText(databaseUser.getUserColumnPassword());
                userId = databaseUser.getUserColumnUserId();
                break;
            }
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Update Profile");

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

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // I created a time stamp here
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timeStamp = sdf.format(new Date());

                DatabaseUsersModel databaseUsersModel = new DatabaseUsersModel();

                databaseUsersModel.setUserColumnFirstName(etFirstName.getText().toString());
                databaseUsersModel.setUserColumnLastName(etLastName.getText().toString());
                databaseUsersModel.setUserColumnAddress(etAddress.getText().toString());
                databaseUsersModel.setUserColumnEmailId(etEmail.getText().toString());
                databaseUsersModel.setUserColumnPhoneNumber(etPhoneNumber.getText().toString());
                databaseUsersModel.setUserColumnPassword(etPassword.getText().toString());
                databaseUsersModel.setUserColumnTimestamp(timeStamp);
               // databaseAdapter.getUsers();

                // databaseAdapter.updateUserProfile() will return true if the updation is successful
                // put debug points at this databaseAdapter.getUsers() to see updates

                if(databaseAdapter.updateUserProfile(userId,databaseUsersModel)){
                    //databaseAdapter.getUsers();    i commented this cos i dont see why we need to get users again
                    Toast.makeText(mContext,"Update successful",Toast.LENGTH_LONG).show();
                    finish();
            }

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


}
