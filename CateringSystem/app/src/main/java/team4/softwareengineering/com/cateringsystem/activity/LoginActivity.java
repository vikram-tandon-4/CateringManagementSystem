package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import team4.softwareengineering.com.cateringsystem.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    /*

    remove 4 buttons
    Check flow
     */

    private TextView btnAdmin,btnCaterer,btnStaff,btnUser,btnLogin;
    private EditText etUserName,etPassword;
    private TextView tvRegister,tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mContext= this;
        init();
    }

    private void init() {

        btnAdmin = (TextView) findViewById(R.id.btnAdmin);
        btnCaterer = (TextView) findViewById(R.id.btnCaterer);
        btnStaff = (TextView) findViewById(R.id.btnStaff);
        btnUser = (TextView) findViewById(R.id.btnUser);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnAdmin.setOnClickListener(this);
        btnCaterer.setOnClickListener(this);
        btnStaff.setOnClickListener(this);
        btnUser.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText(R.string.login);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.btnAdmin:
                startActivity(new Intent(LoginActivity.this,AdminHomeActivity.class));
                break;
            case R.id.btnCaterer:

                startActivity(new Intent(LoginActivity.this,CatererHomePageActivity.class));

                break;
            case R.id.btnStaff:
                startActivity(new Intent(LoginActivity.this,CatererStaffHomePageActivity.class));

                break;

            case R.id.btnUser:
                startActivity(new Intent(LoginActivity.this,UserHomePageActivity.class));

                break;
            case R.id.tvRegister:
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

                break;

            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;

            default:
                break;
        }
    }
}
