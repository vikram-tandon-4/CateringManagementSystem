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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.SimpleSpinnerAdaptor;

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
    private TextView btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);

        mContext= this;

        init();


    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        etPartySize= (EditText) findViewById(R.id.etPartySize);
        etDurationInMinutes= (EditText) findViewById(R.id.etDurationInMinutes);
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
