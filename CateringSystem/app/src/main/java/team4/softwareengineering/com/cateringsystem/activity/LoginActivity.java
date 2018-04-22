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

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.model.HallModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;

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
        btnLogin.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText(R.string.login);


        if(AppPreferences.isFirstTime(mContext)){
            AppPreferences.setHalls(mContext,getHallData());
            ArrayList<HallModel> hallModels = AppPreferences.getHalls(mContext);
//            AppPreferences.setFirstTime(mContext,false);
        }


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

            case R.id.btnLogin:
               AppPreferences.setUtaId(mContext,"100155534376");
                // check thi UTA id in database
                // according to the category logging
                AppPreferences.getUtaId(mContext);
                break;

            default:
                break;
        }
    }

    private ArrayList<HallModel> getHallData(){

        ArrayList<HallModel> halls = new ArrayList<>();

        HallModel hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 200");
        hallModel.setHallName("Liberty");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $150/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 300");
        hallModel.setHallName("KC");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $250/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 800");
        hallModel.setHallName("Shard");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $350/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 500");
        hallModel.setHallName("Arlington");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $100/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 300");
        hallModel.setHallName("Maverick");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $170/hr");
        halls.add(hallModel);

        return halls;
    }
}
