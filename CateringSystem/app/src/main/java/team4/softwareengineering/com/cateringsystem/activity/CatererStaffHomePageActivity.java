package team4.softwareengineering.com.cateringsystem.activity;

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

/**
 * Created by vikra on 3/24/2018.
 */

public class CatererStaffHomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle, tvViewAssignedEvents, tvUpdateProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catererstaff_homepage);

        mContext= this;
        init();
    }

    private void init() {

        tvViewAssignedEvents = (TextView) findViewById(R.id.tvViewAssignedEvents);
        tvUpdateProfile = (TextView) findViewById(R.id.tvUpdateProfile);
        tvViewAssignedEvents.setOnClickListener(this);
        tvUpdateProfile.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);


        tvTbTitle.setText("Caterer Staff Home");

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.tvViewAssignedEvents:
                startActivity(new Intent(CatererStaffHomePageActivity.this,AssignedEventsActivity.class));
                break;

            case R.id.tvUpdateProfile:
                startActivity(new Intent(CatererStaffHomePageActivity.this,UpdateProfileActivity.class));

                break;

            default:
                break;
        }
    }
}
