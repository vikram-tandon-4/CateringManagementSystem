package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.ReservedEventsAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class AssignedEventDetails extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private Dialog confirmDialog;

    private TextView tvEventName,tvEventId, tvPlace, tvCapacity, tvDate, tvTime, tvDuration, tvOcassionType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assigned_event_details);

        mContext= this;
        init();

    }

    private void init() {

        tvEventName =(TextView) findViewById(R.id.tvEventName);
        tvEventId =(TextView) findViewById(R.id.tvEventId);
        tvPlace =(TextView) findViewById(R.id.tvPlace);
        tvCapacity =(TextView) findViewById(R.id.tvCapacity);
        tvDate =(TextView) findViewById(R.id.tvDate);
        tvTime =(TextView) findViewById(R.id.tvTime);
        tvDuration =(TextView) findViewById(R.id.tvDuration);
        tvOcassionType =(TextView) findViewById(R.id.tvOcassionType);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.assigned_events);

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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

        DatabaseEventsModel databaseEventsModel = (DatabaseEventsModel)getIntent().getSerializableExtra(AppConstants.ASSIGNED_EVENTS_ACTIVITY);


        tvEventName.setText(databaseEventsModel.getEventColumnOccasionType());
        tvEventId.setText(databaseEventsModel.getEventAssignedColumnId());
        tvPlace.setText(databaseEventsModel.getEventColumnLocation());
        tvCapacity.setText(""+databaseEventsModel.getEventColumnSizeOfParty());
        tvDate.setText(databaseEventsModel.getEventColumnDate());
        tvDuration.setText(databaseEventsModel.getEventColumnDuration());
        tvTime.setText(databaseEventsModel.getEventColumnTime());
        tvOcassionType.setText(databaseEventsModel.getEventColumnOccasionType());

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


}
