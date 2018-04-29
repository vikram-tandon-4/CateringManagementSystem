package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;

/**
 * Created by vikra on 3/24/2018.
 */

public class EditUserActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner spinnerCategory;
    private ArrayAdapter<String> simpleSpinnerAdaptor ;
    private Toolbar toolbar;
    private TextView tvTbTitle,tvSave;
    private EditText etUtaId, etFirstName, etLastName, etEmail, etPhoneNumber, etPassword;
    private DatabaseAdapter databaseAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        mContext= this;
        init();
    }

    private void init() {

        final DatabaseUsersModel databaseUsersModel = (DatabaseUsersModel) getIntent().getSerializableExtra(AppConstants.EDIT_USER_ACTIVITY);

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        etUtaId = (EditText)findViewById(R.id.etUtaId);
        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tvSave = (TextView) findViewById(R.id.tvSave);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseUsersModel.setUserColumnUtaId(etUtaId.getText().toString());
                databaseUsersModel.setUserColumnFirstName(etFirstName.getText().toString());
                databaseUsersModel.setUserColumnLastName(etLastName.getText().toString());
                databaseUsersModel.setUserColumnEmailId(etEmail.getText().toString());
                databaseUsersModel.setUserColumnPhoneNumber(etPhoneNumber.getText().toString());
                databaseUsersModel.setUserColumnPassword(etPassword.getText().toString());
                databaseUsersModel.setUserColumnCategory(spinnerCategory.getSelectedItem().toString());

                databaseAdapter.updateUserProfile(databaseUsersModel.getUserColumnUserId(),databaseUsersModel);

                Toast.makeText(mContext,"Update saved",Toast.LENGTH_LONG).show();
                finish();
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

        tvTbTitle.setText("Edit User");

        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        setSystemUserCategory();

        Toast.makeText(mContext,"This screen will have prefilled data",Toast.LENGTH_LONG);

        // Prefill data from databsse

        etUtaId.setText(databaseUsersModel.getUserColumnUtaId());
        etFirstName.setText(databaseUsersModel.getUserColumnFirstName());
        etLastName.setText(databaseUsersModel.getUserColumnLastName());
        etEmail.setText(databaseUsersModel.getUserColumnEmailId());
        etPhoneNumber.setText(databaseUsersModel.getUserColumnPhoneNumber());
        etPassword.setText(databaseUsersModel.getUserColumnPassword());

        if(databaseUsersModel.getUserColumnCategory().equals("Caterer")){
            spinnerCategory.setSelection(0);
        }else if(databaseUsersModel.getUserColumnCategory().equals("Caterer Staff")){
            spinnerCategory.setSelection(1);
        }else{
            spinnerCategory.setSelection(2);
        }
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
