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

import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.RequestedEventModel;

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
                        Toast.makeText(mContext, "Logout",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.deleteEvent:
                        Toast.makeText(mContext, "Delete",Toast.LENGTH_LONG).show();
                        return true;

                }
                return false;
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
                break;

            case R.id.btnAvailableStaff:
                startActivity(new Intent(EventDetailsActivity.this,AvailableStaffListActivity.class));
                break;

            default:
                break;
        }
    }
}
