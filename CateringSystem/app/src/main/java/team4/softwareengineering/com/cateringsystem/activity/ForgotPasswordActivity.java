package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle,btnResetPassword;
    private EditText txtUserName,txtNewPassword;
    private DatabaseAdapter databaseAdapter;
    DatabaseUsersModel data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        mContext= this;
        init();
    }

    private void init() {
        txtUserName=(EditText) findViewById(R.id.txtUserName);
        txtNewPassword=(EditText) findViewById(R.id.txtNewPassword);
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        btnResetPassword = (TextView) findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DatabaseUsersModel> users= databaseAdapter.getUsers();
                for(DatabaseUsersModel databaseUsersModel:users){
                    if(databaseUsersModel.getUserColumnUtaId().equals(txtUserName.getText().toString())){
                        data=databaseUsersModel;
                        break;
                    }
                }
                data.setUserColumnPassword(txtNewPassword.getText().toString());
                if(databaseAdapter.updateUserProfile(data.getUserColumnUserId(),data)){
                    Toast.makeText(mContext,"New Password Updated ",Toast.LENGTH_LONG).show();
                    databaseAdapter.getUsers();
                    finish();

                }
            }
        });
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvTbTitle.setText(R.string.forgot_password);
    }
}
