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
import android.widget.TextView;
import android.widget.Toast;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class AdminHomeActivity extends AppCompatActivity  implements View.OnClickListener{

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle,tvViewregistrationRequest,tvSearchUsers;
    private Dialog confirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        mContext= this;
        init();
    }

    private void init() {



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        tvSearchUsers = (TextView) findViewById(R.id.tvSearchUsers);
        tvViewregistrationRequest = (TextView) findViewById(R.id.tvViewregistrationRequest);

        tvViewregistrationRequest.setOnClickListener(this);
        tvSearchUsers.setOnClickListener(this);

        tvTbTitle.setText("Admin Home");

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        Toast.makeText(mContext, "Logout",Toast.LENGTH_LONG).show();
                        confirmationDialog();
                        return true;

                }
                return false;
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

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.tvViewregistrationRequest:
                startActivity(new Intent(AdminHomeActivity.this,RegistrationRequestActivity.class));
                break;
            case R.id.tvSearchUsers:

                startActivity(new Intent(AdminHomeActivity.this,SearchUserActivity.class));

                break;

            default:
                break;
        }
    }
}
