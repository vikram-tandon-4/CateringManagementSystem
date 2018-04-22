package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.SimpleSpinnerAdaptor;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class RequestActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner spinnerMealType,spinnerMealFormality,spinnerDrink,spinnerOccasionType;
    private ArrayAdapter<String> simpleSpinnerAdaptor;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private EditText etPartySize;
    private EditText etDurationInMinutes;
    private EditText etDate;
    private EditText etTime;
    private CheckBox cbEntertainment,cbAmerican,cbChinese,cbFrench,cbGreek,cbIndian,cbItalian,
            cbJapanese,cbMexican,cbPizza;
    private TextView btnSubmit;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);

        mContext= this;
        init();
    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        etPartySize= (EditText) findViewById(R.id.etPartySize);
        etDurationInMinutes= (EditText) findViewById(R.id.etDurationInMinutes);

        cbEntertainment =(CheckBox)findViewById(R.id.cbEntertainment);
        cbAmerican =(CheckBox)findViewById(R.id.cbAmerican);
        cbChinese =(CheckBox)findViewById(R.id.cbChinese);
        cbFrench =(CheckBox)findViewById(R.id.cbFrench);
        cbGreek =(CheckBox)findViewById(R.id.cbGreek);
        cbIndian =(CheckBox)findViewById(R.id.cbIndian);
        cbItalian =(CheckBox)findViewById(R.id.cbItalian);
        cbJapanese =(CheckBox)findViewById(R.id.cbJapanese);
        cbMexican =(CheckBox)findViewById(R.id.cbMexican);
        cbPizza =(CheckBox)findViewById(R.id.cbPizza);

        String foodVenue="";
//
//        if(cbAmerican.isChecked()){
//            foodVenue += "American";
//        }

        etDate= (EditText) findViewById(R.id.etDate);
        etTime= (EditText) findViewById(R.id.etTime);
        btnSubmit= (TextView) findViewById(R.id.btnSubmit);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.request_event);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseEventsModel databaseEventsModel = new DatabaseEventsModel();

//                contentValues.put(EVENT_COLUMN_EVENT_ID,databaseEventsModel.getEventColumnId());
                databaseEventsModel.setEventAssignedColumnId("UTA+TIMESTAMP");
                databaseEventsModel.setEventColumnStatus("PENDING");
                databaseEventsModel.setEventColumnTimestamp(""+System.currentTimeMillis());
                databaseEventsModel.setEventColumnDuration("120");
                databaseEventsModel.setEventColumnOccasionType("Birthday");
                databaseEventsModel.setEventColumnEntertainment("Yes");
                databaseEventsModel.setEventColumnMealType("American");
                databaseEventsModel.setEventColumnDrinks("Alcoholic");
                databaseEventsModel.setEventColumnLocation("UC");
                databaseEventsModel.setEventColumnSizeOfParty(123);
                databaseEventsModel.setEventColumnCatererId("catererid");
                databaseEventsModel.setEventColumnMealFormality("formal");
                databaseEventsModel.setEventColumnFoodVenue("foodvenue");
                databaseEventsModel.setEventColumnStaffId("Ankur,pradeep");
                databaseEventsModel.setEventColumnUtaId("UTA1234");
                databaseEventsModel.setEventColumnUserId("userid");
                databaseEventsModel.setEventColumnEventCost(500);
                databaseEventsModel.setEventColumnDate("12/12/2018");
                databaseEventsModel.setEventColumnTime("2:00PM");
                databaseEventsModel.setEventColumnHallId("hall11");
                databaseEventsModel.setEventColumnUserFirstName("Roopam");

                databaseEventsModel.setEventColumnUtaId("UTA1234");
                if(databaseAdapter.insertEvents(databaseEventsModel)){
                    databaseAdapter.getEvents();
                    // Uncomment below to delete the top most event
                    if(databaseAdapter.deleteEvent(databaseAdapter.getEvents().get(0).getEventColumnId()))
                    databaseAdapter.getEvents();
                }


            }
        });

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


        spinnerMealType = (Spinner) findViewById(R.id.spinnerMealType);
        spinnerMealFormality = (Spinner) findViewById(R.id.spinnerMealFormality);
        spinnerDrink = (Spinner) findViewById(R.id.spinnerDrink);
        spinnerOccasionType = (Spinner) findViewById(R.id.spinnerOccasionType);


        ArrayList<String> data=new ArrayList<>();
        data= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meal_type_array)));
        setSpinner(spinnerMealType,data);
        data= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meal_formality_array)));
        setSpinner(spinnerMealFormality,data);
        data= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.drink_type_array)));
        setSpinner(spinnerDrink,data);
        data= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.ocassion_type_array)));
        setSpinner(spinnerOccasionType,data);
    }


    /*

Adding data to dropdowns
 */
    private void setSpinner(Spinner spinnerCategory, ArrayList<String> data){

        spinnerCategory.setSelection(0);
        simpleSpinnerAdaptor = new SimpleSpinnerAdaptor(mContext, android.R.layout.simple_spinner_item, data);
        simpleSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(simpleSpinnerAdaptor);

    }
}
