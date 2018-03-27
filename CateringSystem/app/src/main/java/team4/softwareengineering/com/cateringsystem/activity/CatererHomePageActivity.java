package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class CatererHomePageActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private Dialog confirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_homepage);

        mContext= this;
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        tvTbTitle.setText("Caterer Home");

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        Toast.makeText(mContext, "Logout",Toast.LENGTH_LONG).show();
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

        final TextView btnYes = (TextView) confirmDialog.findViewById(R.id.okLogout);
        final TextView btnNo = (TextView) confirmDialog.findViewById(R.id.cancelLogout);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }
}
