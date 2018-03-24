package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.SimpleSpinnerAdaptor;

/**
 * Created by vikra on 3/24/2018.
 */

public class RegisterationActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner spinnerCategory;
    private ArrayAdapter<String> simpleSpinnerAdaptor;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mContext= this;

        init();


    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.request_event);

        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        setSystemUserCategory();
    }

    /*

Adding categories to dropdown
 */
    private void setSystemUserCategory(){



        ArrayList<String> systemUserCategory=new ArrayList<>();

        systemUserCategory= new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.category_array)));


        spinnerCategory.setSelection(0);
        simpleSpinnerAdaptor = new SimpleSpinnerAdaptor(mContext, android.R.layout.simple_spinner_item, systemUserCategory);
        simpleSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(simpleSpinnerAdaptor);

    }
}
