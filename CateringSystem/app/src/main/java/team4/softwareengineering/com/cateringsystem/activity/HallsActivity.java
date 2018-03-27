package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
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
import team4.softwareengineering.com.cateringsystem.adapter.HallsAdapter;
import team4.softwareengineering.com.cateringsystem.model.HallModel;

/**
 * Created by vikra on 3/24/2018.
 */

public class HallsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private HallsAdapter mAdapter;
    private RecyclerView rvAvailableHall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_hall);

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

        tvTbTitle.setText("Halls");

        rvAvailableHall = (RecyclerView)findViewById(R.id.rvAvailableHall);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAvailableHall.setLayoutManager(mLayoutManager);
        rvAvailableHall.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new HallsAdapter(getHallData(), mContext);
        rvAvailableHall.setAdapter(mAdapter);

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
    }

    private ArrayList<HallModel>  getHallData(){

        ArrayList<HallModel> halls = new ArrayList<>();

        HallModel hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 200");
        hallModel.setHallName("Liberty");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $150/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 300");
        hallModel.setHallName("KC");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $250/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 800");
        hallModel.setHallName("Shard");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $350/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 500");
        hallModel.setHallName("Arlington");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $100/hr");
        halls.add(hallModel);

        hallModel = new HallModel();
        hallModel.setCapacity("Capacity: 300");
        hallModel.setHallName("Maverick");
        hallModel.setLocation("UTA");
        hallModel.setPrice("Price: $170/hr");
        halls.add(hallModel);

        return halls;
    }
}
