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
import team4.softwareengineering.com.cateringsystem.model.ReviewResourcesModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;

/**
 * Created by vikra on 3/24/2018.
 */

public class SelectedEventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle,textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView9,textView10,textView11,textView12,textView13,textView14,textView15,textView16,tvCost,textView20,textView18,textView22;
    private Button btnReviewResources,btnCreateEvent;
    private DatabaseAdapter databaseAdapter;
    private ReviewResourcesModel reviewResourcesModel;
    DatabaseEventsModel databaseEventsModel = new DatabaseEventsModel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_event_details);

        mContext= this;
        init();
    }

    private void init() {
        reviewResourcesModel= new ReviewResourcesModel();
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);
        btnReviewResources =(Button) findViewById(R.id.btnReviewResources);
        btnCreateEvent =(Button) findViewById(R.id.btnCreateEvent);
        btnCreateEvent.setOnClickListener(this);
        btnReviewResources.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        //textView = (TextView) findViewById(R.id.textView);
        //textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView18 =(TextView) findViewById(R.id.textView18);
        textView20 =(TextView) findViewById(R.id.textView20);
        textView22 =(TextView) findViewById(R.id.textView22);
        tvCost =(TextView)findViewById(R.id.tvCost);


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Event Details");
        final String eventId = getIntent().getStringExtra("EventId");
        final List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        int i = 0;

        for(i=0;i<dbEvents.size();i++){
            if((dbEvents.get(i).getEventAssignedColumnId()).equals(eventId)){
                databaseEventsModel = dbEvents.get(i);
                textView16.setText(dbEvents.get(i).getEventColumnOccasionType());
                textView14.setText(dbEvents.get(i).getEventColumnDuration());
                textView10.setText(dbEvents.get(i).getEventColumnDate());
                textView3.setText(eventId);
                textView12.setText(dbEvents.get(i).getEventColumnTime());
                textView5.setText(dbEvents.get(i).getEventColumnLocation());
                textView7.setText(dbEvents.get(i).getEventColumnSizeOfParty()+"");
                tvCost.setText(tvCost.getText().toString()+dbEvents.get(i).getEventColumnEventCost()+"");
                textView18.setText(dbEvents.get(i).getEventColumnMealType());
                textView20.setText(dbEvents.get(i).getEventColumnDrinks());
                textView22.setText(dbEvents.get(i).getEventColumnEntertainment());
                databaseEventsModel.setEventColumnId(dbEvents.get(i).getEventColumnId());
                databaseEventsModel.setEventColumnCatererId(AppPreferences.getUtaId(mContext));
                reviewResourcesModel.setFoodvenue(dbEvents.get(i).getEventColumnFoodVenue());
                reviewResourcesModel.setDrink(dbEvents.get(i).getEventColumnDrinks());
                reviewResourcesModel.setEntertainment(dbEvents.get(i).getEventColumnEntertainment());
                reviewResourcesModel.setMealformality(dbEvents.get(i).getEventColumnMealFormality());
                reviewResourcesModel.setMealtype(dbEvents.get(i).getEventColumnMealType());
                break;
            }
        }

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
                DatabaseEventsModel dm = databaseEventsModel;
                dm.setEventColumnStatus("Booked");
                if(databaseAdapter.updateEvent( databaseEventsModel.getEventColumnId(),dm)) {
                    Toast.makeText(mContext, "Event Created", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case R.id.btnReviewResources:
                Bundle b = new Bundle();
                b.putSerializable("Resources", reviewResourcesModel);
                startActivity(new Intent(SelectedEventDetailsActivity.this, ReviewResourcesActivity.class).putExtras(b));
                break;
            default:
                break;
        }
    }
}
