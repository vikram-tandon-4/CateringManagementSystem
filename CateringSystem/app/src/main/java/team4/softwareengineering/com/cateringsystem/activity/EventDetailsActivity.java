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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    private Button btnAvailableHalls, btnAvailableStaff;
    private TextView tvEventName, tvEventId, tvPlace, tvPartySize, tvDate, tvTime,
            tvDuration, tvOccasionType, tvMealType, tvDrinks, tvEntertainment,tvCost;
    private DatabaseEventsModel dbEvents;
    private DatabaseAdapter databaseAdapter;
    private Dialog confirmDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        mContext= this;
        init();
    }

    private void init() {
        dbEvents= new DatabaseEventsModel();
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);
        btnAvailableHalls = (Button) findViewById(R.id.btnAvailableHalls);
        btnAvailableStaff = (Button) findViewById(R.id.btnAvailableStaff);
        btnAvailableHalls.setOnClickListener(this);
        btnAvailableStaff.setOnClickListener(this);

        tvEventName = (TextView) findViewById(R.id.tvEventName);
        tvEventId = (TextView) findViewById(R.id.tvEventId);
        tvPlace = (TextView) findViewById(R.id.tvPlace);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        tvPartySize = (TextView) findViewById(R.id.tvPartySize);
        tvOccasionType = (TextView) findViewById(R.id.tvOcassionType);
        tvMealType = (TextView) findViewById(R.id.tvMealType);
        tvDrinks = (TextView) findViewById(R.id.tvDrinks);
        //tvCost=(TextView) findViewById(R.id.tvCost);
        tvEntertainment = (TextView) findViewById(R.id.tvEntertainment);
        final DatabaseEventsModel event = (DatabaseEventsModel)getIntent().getSerializableExtra("EventId");
        dbEvents=event;
        final List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        for(int i=0;i<dbEvents.size();i++){
            if((dbEvents.get(i).getEventAssignedColumnId()).equals(event.getEventAssignedColumnId())){
                tvOccasionType.setText(dbEvents.get(i).getEventColumnOccasionType());
                tvDuration.setText(dbEvents.get(i).getEventColumnDuration());
                tvEventName.setText(dbEvents.get(i).getEventColumnOccasionType());
                tvDate.setText(dbEvents.get(i).getEventColumnDate());
                tvEventId.setText(dbEvents.get(i).getEventAssignedColumnId());
                tvTime.setText(dbEvents.get(i).getEventColumnTime());
                tvPlace.setText(dbEvents.get(i).getEventColumnLocation());
                tvPartySize.setText(dbEvents.get(i).getEventColumnSizeOfParty()+"");
                tvMealType.setText(dbEvents.get(i).getEventColumnMealType());
                tvDrinks.setText(dbEvents.get(i).getEventColumnDrinks());
                tvEntertainment.setText(dbEvents.get(i).getEventColumnEntertainment());
                //tvCost.setText(tvCost.getText().toString()+dbEvents.get(i).getEventColumnEventCost()+"");
                break;
            }
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.event_details);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.delete_event_menu);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        confirmationLDialog();
                        return true;
                    case R.id.deleteEvent:
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

        final TextView tvConfirmationText = (TextView) confirmDialog.findViewById(R.id.tvConfirmationText);
        final TextView btnYes = (TextView) confirmDialog.findViewById(R.id.okLogout);
        final TextView btnNo = (TextView) confirmDialog.findViewById(R.id.cancelLogout);

        tvConfirmationText.setText("Are you sure you want to delete this event?");

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAdapter.deleteEvent(dbEvents.getEventColumnId());
                Toast.makeText(mContext, "Event Deleted",Toast.LENGTH_LONG).show();
                confirmDialog.dismiss();
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }
    private void confirmationLDialog() {
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
            case R.id.btnAvailableHalls:
                Bundle b = new Bundle();
                b.putSerializable("EventId", dbEvents);
                startActivity(new Intent(EventDetailsActivity.this,AvailableHallsActivity.class).putExtras(b));
                finish();
                break;

            case R.id.btnAvailableStaff:
                Bundle b1 = new Bundle();
                b1.putSerializable("EventId", dbEvents);
                startActivity(new Intent(EventDetailsActivity.this,AvailableStaffListActivity.class).putExtras(b1));
                break;

            default:
                break;
        }
    }
}
