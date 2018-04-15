package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import team4.softwareengineering.com.cateringsystem.R;

/**
 * Created by vikra on 3/24/2018.
 */

public class SelectedEventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private Button btnReviewResources,btnCreateEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_event_details);

        mContext= this;
        init();
    }

    private void init() {

        btnReviewResources =(Button) findViewById(R.id.btnReviewResources);
        btnCreateEvent =(Button) findViewById(R.id.btnCreateEvent);
        btnCreateEvent.setOnClickListener(this);
        btnReviewResources.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Event Details");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
            case R.id.btnCreateEvent:
                Toast.makeText(mContext,"Event Created",Toast.LENGTH_LONG).show();
                break;

            case R.id.btnReviewResources:
                startActivity(new Intent(SelectedEventDetailsActivity.this,ReviewResourcesActivity.class));
                break;

            default:
                break;
        }
    }
}
