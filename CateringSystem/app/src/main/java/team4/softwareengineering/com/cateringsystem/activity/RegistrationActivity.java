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

                databaseUsersModel.setUserColumnUtaId("1001554543");
                databaseUsersModel.setUserColumnFirstName("Roopam");
                databaseUsersModel.setUserColumnLastName("Sharma");
                databaseUsersModel.setUserColumnEmailId("roopam@gmail.com");
                databaseUsersModel.setUserColumnPhoneNumber("987654321");
                databaseUsersModel.setUserColumnCategory("Staff");
                databaseUsersModel.setUserColumnPassword("password");
                databaseUsersModel.setUserColumnAddress("404 Borders");
                // fixed fields
                databaseUsersModel.setUserColumnTimestamp(""+System.currentTimeMillis());
                databaseUsersModel.setUserColumnStatus("Pending");

                if(databaseAdapter.addUser(databaseUsersModel)){
                    databaseAdapter.getUsers();
                    if(databaseAdapter.deleteUser(databaseAdapter.getUsers().get(0).getUserColumnUserId())){
                        databaseAdapter.getUsers();
                    }
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
