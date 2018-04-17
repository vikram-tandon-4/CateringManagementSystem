package team4.softwareengineering.com.cateringsystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import team4.softwareengineering.com.cateringsystem.R;

/**
 * Created by vikra on 3/24/2018.
 */

public class UserEventDetailsActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;

    private TextView textView, textView1,textView2,textView3,textView4,textView5,textView6;
    private TextView textView7, textView8, textView9, textView10, textView11,textView12,textView13;
    private TextView textView14, textView15,textView16;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_event_details);

        mContext= this;
        init();
    }

    private void init() {

    textView =  (TextView) findViewById(R.id.textView);
    textView1 = (TextView) findViewById(R.id.textView1);
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

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
                        Toast.makeText(mContext, "Cancel",Toast.LENGTH_LONG).show();
                        return true;

                }
                return false;
            }
        });

    }
}
