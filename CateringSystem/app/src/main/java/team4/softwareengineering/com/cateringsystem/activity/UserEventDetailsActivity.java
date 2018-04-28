package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.ReservedEventsModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class UserEventDetailsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    private TextView tvOccasionType,tvEventName,tvDuration,tvTime,tvPlace,tvCapacity,tvEventId,tvDate,tvCost;
    private DatabaseAdapter databaseAdapter;
    private ReservedEventsModel reservedEventsModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_event_details);
        mContext= this;
        init();
    }

    private void init() {
        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);
        tvOccasionType = (TextView) findViewById(R.id.tvOccasionType);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        tvEventName = (TextView) findViewById(R.id.tvEventName);
        tvEventId = (TextView) findViewById(R.id.tvEventId);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvPlace = (TextView) findViewById(R.id.tvPlace);
        tvCapacity = (TextView) findViewById(R.id.tvCapacity);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvCost = (TextView) findViewById(R.id.tvCost);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        reservedEventsModel= (ReservedEventsModel)getIntent().getSerializableExtra("EventId");
        final List<DatabaseEventsModel> dbEvents = databaseAdapter.getEvents();
        int i = 0;
        //Toast.makeText(mContext,eventId,Toast.LENGTH_SHORT).show();
        DatabaseEventsModel databaseEventsModel = new DatabaseEventsModel();
        for(i=0;i<dbEvents.size();i++){
            if((dbEvents.get(i).getEventAssignedColumnId()+"").equals(reservedEventsModel.getEventId())){
                databaseEventsModel = dbEvents.get(i);
                tvOccasionType.setText(dbEvents.get(i).getEventColumnOccasionType());
                tvDuration.setText(dbEvents.get(i).getEventColumnDuration());
                tvEventName.setText(dbEvents.get(i).getEventColumnOccasionType());
                tvDate.setText(dbEvents.get(i).getEventColumnDate());
                tvEventId.setText(dbEvents.get(i).getEventAssignedColumnId());
                tvTime.setText(dbEvents.get(i).getEventColumnTime());
                tvPlace.setText(dbEvents.get(i).getEventColumnLocation());
                tvCapacity.setText(dbEvents.get(i).getEventColumnSizeOfParty()+"");
                tvCost.setText(tvCost.getText().toString()+dbEvents.get(i).getEventColumnEventCost()+"");
                break;
            }
        }
        final DatabaseEventsModel dm = databaseEventsModel;
        //Toast.makeText(mContext,eventId,Toast.LENGTH_SHORT).show();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.user_event_details);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.cancel_event_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        Toast.makeText(mContext, "Logout",Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.cancelEvent:
                        dm.setEventColumnStatus("Cancelled");
                        if(databaseAdapter.updateEvent( Integer.parseInt(reservedEventsModel.getDbId()),dm)){
                            Toast.makeText(mContext, "Event Cancelled Successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        return true;
                }
                return false;
            }
        });

    }
}
