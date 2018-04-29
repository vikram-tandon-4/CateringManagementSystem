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

public class UserHomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle, tvRequestEvent, tvViewReservedEvents,tvViewAvailableHalls, tvUpdateProfile;
    private Dialog confirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        mContext= this;
        init();
    }

    private void init() {

        tvRequestEvent = (TextView) findViewById(R.id.tvRequestEvent);
        tvViewReservedEvents = (TextView) findViewById(R.id.tvViewReservedEvents);
        tvViewAvailableHalls = (TextView) findViewById(R.id.tvViewAvailableHalls);
        tvUpdateProfile = (TextView) findViewById(R.id.tvUpdateProfile);

        tvRequestEvent.setOnClickListener(this);
        tvViewReservedEvents.setOnClickListener(this);
        tvViewAvailableHalls.setOnClickListener(this);
        tvUpdateProfile.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText("User Home");

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.tvRequestEvent:
                startActivity(new Intent(UserHomePageActivity.this,RequestActivity.class));
                break;

            case R.id.tvViewReservedEvents:
                startActivity(new Intent(UserHomePageActivity.this,ReservedEventsActivity.class));
                break;

            case R.id.tvViewAvailableHalls:
                startActivity(new Intent(UserHomePageActivity.this,HallsActivity.class));
                break;

            case R.id.tvUpdateProfile:
                startActivity(new Intent(UserHomePageActivity.this,UpdateProfileActivity.class));
                break;

            default:
                break;
        }
    }
}
