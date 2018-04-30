package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;
import team4.softwareengineering.com.cateringsystem.utils.AppConstants;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class SearchUserDetailsInAdminActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle,tvUserName,tvFirstName,tvLastName,tvEmailId,tvPassword,tvAddress,tvCategory;
    private Dialog confirmDialog;
    private DatabaseAdapter databaseAdapter;
    private DatabaseUsersModel databaseUsersModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_details);

        mContext= this;
        init();
    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);
        tvUserName= (TextView) findViewById(R.id.tvUserName);
        tvFirstName= (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvEmailId= (TextView) findViewById(R.id.tvEmailId);
        tvPassword= (TextView) findViewById(R.id.tvPassword);
        tvAddress= (TextView) findViewById(R.id.tvAddress);
        tvCategory= (TextView) findViewById(R.id.tvCategory);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("User Details");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.delete_edit_user_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        confirmationLDialog();
                        return true;

                    case R.id.edit:
                        Intent intent = new Intent(SearchUserDetailsInAdminActivity.this,EditUserActivity.class);
                        intent.putExtra(AppConstants.EDIT_USER_ACTIVITY,databaseUsersModel);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.delete:
                        confirmationDialog();
                        return true;

                }
                return false;
            }
        });

         databaseUsersModel = (DatabaseUsersModel) getIntent().getSerializableExtra(AppConstants.SEARCH_USER_DETAILS);

        tvUserName.setText(databaseUsersModel.getUserColumnUtaId());
        tvFirstName.setText(databaseUsersModel.getUserColumnFirstName());
        tvLastName.setText(databaseUsersModel.getUserColumnLastName());
        tvEmailId.setText(databaseUsersModel.getUserColumnEmailId());
        tvPassword.setText(databaseUsersModel.getUserColumnPassword());
        tvAddress.setText(databaseUsersModel.getUserColumnAddress());
        tvCategory.setText(databaseUsersModel.getUserColumnCategory());
    }
    private void confirmationLDialog() {
        confirmDialog = Utils.showConfirmationDialog(mContext);
        confirmDialog.show();

        final TextView btnYes = (TextView) confirmDialog.findViewById(R.id.okLogout);
        final TextView btnNo = (TextView) confirmDialog.findViewById(R.id.cancelLogout);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Logging Out",Toast.LENGTH_LONG).show();
                confirmDialog.dismiss();
                finishAffinity();
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }
    private void confirmationDialog() {
        confirmDialog = Utils.showConfirmationDialog(mContext);
        confirmDialog.show();

        final TextView tvConfirmationText = (TextView) confirmDialog.findViewById(R.id.tvConfirmationText);
        final TextView btnYes = (TextView) confirmDialog.findViewById(R.id.okLogout);
        final TextView btnNo = (TextView) confirmDialog.findViewById(R.id.cancelLogout);

        tvConfirmationText.setText("Are you sure you want to delete this user?");

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAdapter.deleteUser(databaseUsersModel.getUserColumnUserId());
                confirmDialog.dismiss();
                finish();
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
