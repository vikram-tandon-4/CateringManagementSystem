package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import team4.softwareengineering.com.cateringsystem.R;

/**
 * Created by vikra on 3/24/2018.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle,btnResetPassword;
    private EditText txtUserName,txtNewPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        mContext= this;
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        btnResetPassword = (TextView) findViewById(R.id.btnResetPassword);
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
