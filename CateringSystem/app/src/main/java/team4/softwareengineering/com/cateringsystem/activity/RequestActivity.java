package team4.softwareengineering.com.cateringsystem.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.SimpleSpinnerAdaptor;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.utils.AppPreferences;

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
    private static EditText etDate;
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

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new RequestActivity.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseEventsModel databaseEventsModel = new DatabaseEventsModel();

                long time= System.currentTimeMillis();

                // 5 values are to assigned later
                // Event update method will be used to change values

//                contentValues.put(EVENT_COLUMN_EVENT_ID,databaseEventsModel.getEventColumnId());
                databaseEventsModel.setEventAssignedColumnId("E"+time);
                databaseEventsModel.setEventColumnStatus("PENDING");
                databaseEventsModel.setEventColumnTimestamp(""+System.currentTimeMillis());
                databaseEventsModel.setEventColumnDuration(etDurationInMinutes.getText().toString());
                databaseEventsModel.setEventColumnOccasionType(spinnerOccasionType.getSelectedItem().toString());
                databaseEventsModel.setEventColumnEntertainment(cbEntertainment.isChecked()?"Yes":"No");
                databaseEventsModel.setEventColumnMealType(spinnerMealType.getSelectedItem().toString());
                databaseEventsModel.setEventColumnDrinks(spinnerDrink.getSelectedItem().toString());
                databaseEventsModel.setEventColumnLocation("");
                databaseEventsModel.setEventColumnSizeOfParty(Integer.parseInt(etPartySize.getText().toString()));
                databaseEventsModel.setEventColumnCatererId("");
                databaseEventsModel.setEventColumnMealFormality(spinnerMealFormality.getSelectedItem().toString());
                databaseEventsModel.setEventColumnFoodVenue(getFoodVenues());
//                databaseEventsModel.setEventColumnStaffId("To be assigned");
                databaseEventsModel.setEventColumnStaffId("");
                databaseEventsModel.setEventColumnUtaId(AppPreferences.getUtaId(mContext));
                databaseEventsModel.setEventColumnUserId(AppPreferences.getUtaId(mContext));
                databaseEventsModel.setEventColumnEventCost(calculateCost());
                databaseEventsModel.setEventColumnDate(etDate.getText().toString());
                databaseEventsModel.setEventColumnTime(etTime.getText().toString());
                databaseEventsModel.setEventColumnHallId("");
                databaseEventsModel.setEventColumnUserFirstName("Roopam");

                if(databaseAdapter.insertEvents(databaseEventsModel)){
                 //   databaseAdapter.getEvents();
                    // Uncomment below to delete the top most event
//                    if(databaseAdapter.deleteEvent(databaseAdapter.getEvents().get(0).getEventColumnId()))
//                    databaseAdapter.getEvents();
                    // Updating event
                  //  databaseEventsModel.setEventColumnHallId("Red River");
                  //  databaseEventsModel.setEventColumnLocation("University Center");
                  //  if(databaseAdapter.updateEvent( databaseAdapter.getEvents().get(0).getEventColumnId(),databaseEventsModel)){
                  //      databaseAdapter.getEvents();
                   // }
                    Toast.makeText(mContext,"Request placed successfully",Toast.LENGTH_SHORT).show();
                    finish();
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

    private String getFoodVenues(){

        String foodVenue="";
        if(cbAmerican.isChecked()){
            foodVenue =foodVenue+ " American";
        }
        if(cbChinese.isChecked()){
            foodVenue =foodVenue+ " Chinese";
        }
        if(cbFrench.isChecked()){
            foodVenue =foodVenue+ " French";
        }
        if(cbGreek.isChecked()){
            foodVenue =foodVenue+ " Greek";
        }
        if(cbIndian.isChecked()){
            foodVenue =foodVenue+ " Indian";
        }
        if(cbItalian.isChecked()){
            foodVenue =foodVenue+ " Italian";
        }
        if(cbJapanese.isChecked()){
            foodVenue =foodVenue+ " Japanese";
        }
        if(cbMexican.isChecked()){
            foodVenue =foodVenue+ " Mexican";
        }
        if(cbPizza.isChecked()){
            foodVenue =foodVenue+ " Pizza";
        }

        return foodVenue;
    }


    private String getName(){

        String name="";

        List<DatabaseUsersModel> databaseUsersModels = databaseAdapter.getUsers();

        for(DatabaseUsersModel databaseUsersModel:databaseUsersModels){
            if(AppPreferences.getUtaId(mContext).equals(databaseUsersModel.getUserColumnUtaId())){
                name= databaseUsersModel.getUserColumnFirstName();
            }
        }
        return name;
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            c.add( Calendar.MONTH, 9 );
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            etDate.setText(month+"/"+day+"/"+year);
        }
    }

    // implementation
    private int calculateCost(){
        int cost = 0;
        if (spinnerMealType.getSelectedItem().toString().contains("Breakfast")){
            cost = 8;
        }
        else if(spinnerMealType.getSelectedItem().toString().contains("Lunch")){
            cost = 12;
        }
        else{
            cost = 18;
        }
        if (spinnerMealFormality.getSelectedItem().toString().contains("Formal")){
            cost*=1.5;
        }
        if (spinnerDrink.getSelectedItem().toString().contains("Standard")){
            cost+=10;
        }
        else{
            cost+=15;
        }
        System.out.println(Integer.parseInt(etPartySize.getText().toString()));
        return cost*Integer.parseInt(etPartySize.getText().toString());
    }
}
