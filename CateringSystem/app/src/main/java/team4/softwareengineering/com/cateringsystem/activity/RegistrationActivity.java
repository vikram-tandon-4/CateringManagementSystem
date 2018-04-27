package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.SimpleSpinnerAdaptor;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class RegistrationActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner spinnerCategory;
    private ArrayAdapter<String> simpleSpinnerAdaptor ;
    private EditText etFirstName,etLastName,etEmail,etPhoneNumber,etUtaId,etPassword,etAddress;
    private Toolbar toolbar;
    private TextView tvTbTitle,btnRegister;

    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mContext= this;
        init();
    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etUtaId = (EditText) findViewById(R.id.etUtaId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnRegister = (TextView) findViewById(R.id.btnRegister);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.registration);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseUsersModel databaseUsersModel = new DatabaseUsersModel();

                // Don't keep any field empty
                // There are no validations

                databaseUsersModel.setUserColumnUtaId(etUtaId.getText().toString());
                databaseUsersModel.setUserColumnFirstName(etFirstName.getText().toString());
                databaseUsersModel.setUserColumnLastName(etLastName.getText().toString());
                databaseUsersModel.setUserColumnEmailId(etEmail.getText().toString());
                databaseUsersModel.setUserColumnPhoneNumber(etPhoneNumber.getText().toString());
                databaseUsersModel.setUserColumnCategory(spinnerCategory.getSelectedItem().toString());
                databaseUsersModel.setUserColumnPassword(etPassword.getText().toString());
                databaseUsersModel.setUserColumnAddress(etAddress.getText().toString());
                // fixed fields
                databaseUsersModel.setUserColumnTimestamp(""+System.currentTimeMillis());
                databaseUsersModel.setUserColumnStatus("Pending");

                /*
                databaseAdapter.addUser() is for inserting a new user to the database
                returns true if the insertion is done successfully

                databaseAdapter.getUsers() is for fetching the users from the database

                databaseAdapter.deleteUser() is for deleting a  user from the database
                 returns true if the deletion is done successfully
                 */

                if(databaseAdapter.addUser(databaseUsersModel)){
                    databaseAdapter.getUsers();
                    Toast.makeText(mContext,"Registration successful",Toast.LENGTH_LONG).show();
                    finish();
                    // Uncomment below to see how deletion works
                    //if(databaseAdapter.deleteUser(databaseAdapter.getUsers().get(0).getUserColumnUserId())){
                    //        databaseAdapter.getUsers();
                     //   Toast.makeText(mContext,"Deletion successful",Toast.LENGTH_LONG).show();
                  //  }
                }
            }
        });

        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        setSystemUserCategory();
    }

    /*

Adding categories to dropdown
 */
    private void setSystemUserCategory(){

        ArrayList<String> systemUserCategory=new ArrayList<>();

        systemUserCategory= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.category_array)));

        spinnerCategory.setSelection(0);
        simpleSpinnerAdaptor = new SimpleSpinnerAdaptor(mContext, android.R.layout.simple_spinner_item, systemUserCategory);
        simpleSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(simpleSpinnerAdaptor);

    }
}
