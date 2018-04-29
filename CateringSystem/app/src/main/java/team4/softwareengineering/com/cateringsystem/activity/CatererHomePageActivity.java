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

public class CatererHomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private Dialog confirmDialog;
    private TextView tvViewRequestedEvents, tvViewEventCalender,tvUpdateProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_homepage);

        mContext= this;
        init();
    }

    private void init() {

        tvUpdateProfile = (TextView) findViewById(R.id.tvUpdateProfile);
        tvViewRequestedEvents = (TextView) findViewById(R.id.tvViewRequestedEvents);
        tvViewEventCalender = (TextView) findViewById(R.id.tvViewEventCalender);

        tvViewRequestedEvents.setOnClickListener(this);
        tvViewEventCalender.setOnClickListener(this);
        tvUpdateProfile.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText("Caterer Home");

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
            case R.id.tvViewEventCalender:
                startActivity(new Intent(CatererHomePageActivity.this,CreatedEventListActivity.class));
                break;
            case R.id.tvViewRequestedEvents:
                startActivity(new Intent(CatererHomePageActivity.this,RequestedEventListActivity.class));
                break;
            case R.id.tvUpdateProfile:
                startActivity(new Intent(CatererHomePageActivity.this,UpdateProfileActivity.class));
                break;
            default:
                break;
        }
    }
}
