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
import android.widget.Toast;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.model.HallModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    /*

    remove 4 buttons
    Check flow
     */

    private TextView btnAdmin, btnCaterer, btnStaff, btnUser, btnLogin;
    private EditText etUserName, etPassword;
    private TextView tvRegister, tvForgotPassword;

    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mContext = this;
        init();
    }

    private void init() {
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

//        btnAdmin = (TextView) findViewById(R.id.btnAdmin);
//        btnCaterer = (TextView) findViewById(R.id.btnCaterer);
//        btnStaff = (TextView) findViewById(R.id.btnStaff);
//        btnUser = (TextView) findViewById(R.id.btnUser);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
//        btnAdmin.setOnClickListener(this);
//        btnCaterer.setOnClickListener(this);
//        btnStaff.setOnClickListener(this);
//        btnUser.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText(R.string.login);


        if (AppPreferences.isFirstTime(mContext)) {
            AppPreferences.setHalls(mContext, getHallData());
            // fetch halls
            ArrayList<HallModel> hallModels = AppPreferences.getHalls(mContext);
//            AppPreferences.setFirstTime(mContext,false);
        }
    }

        public void onClick(View v){

                switch (v.getId() /*to get clicked view id**/) {
//                    case R.id.btnAdmin:
//                        startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
//                        break;
//                    case R.id.btnCaterer:
//
//                        startActivity(new Intent(LoginActivity.this, CatererHomePageActivity.class));
//
//                        break;
//                    case R.id.btnStaff:
//                        startActivity(new Intent(LoginActivity.this, CatererStaffHomePageActivity.class));
//
//                        break;
//
//                    case R.id.btnUser:
//                        startActivity(new Intent(LoginActivity.this, UserHomePageActivity.class));
//
//                        break;
                   case R.id.tvRegister:
                        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

                        break;

                    case R.id.tvForgotPassword:
                        //      AppPreferences.getHalls(mContext);
                        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                        break;

                    case R.id.btnLogin:
                        // AppPreferences.setUtaId(mContext, "100155534376");
                        // get The User name and Password
                        String utaId = etUserName.getText().toString();
                        String password = etPassword.getText().toString();
                        // check this UTA id in database
                        String found = databaseAdapter.checkUser(utaId, password);

                        //how do we navigate to the home screen if user log in is succesful
                        switch (found.toUpperCase()) {
                            case "CATERER":
                                startActivity(new Intent(LoginActivity.this, CatererHomePageActivity.class));
                                break;
                            case "STAFF":
                                startActivity(new Intent(LoginActivity.this, CatererStaffHomePageActivity.class));
                                break;
                            case "USER":
                                startActivity(new Intent(LoginActivity.this, UserHomePageActivity.class));
                                break;
                            case "ADMIN":
                                startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
                                break;
                            default:
                                Toast.makeText(mContext, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                                break;
                        }
                        AppPreferences.setUtaId(mContext, utaId);
                        // Toast.makeText(mContext, "Login successful", Toast.LENGTH_LONG).show();

                    default:
                        break;
                }
                }


    private ArrayList<HallModel> getHallData() {

        ArrayList<HallModel> halls = new ArrayList<>();

        HallModel hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 200");
        hallModel.setHallName("Liberty");
        hallModel.setLocation("Liberty");
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

