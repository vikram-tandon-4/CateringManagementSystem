package team4.softwareengineering.com.cateringsystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.R;
import team4.softwareengineering.com.cateringsystem.adapter.RegistrationRequestAdapter;
import team4.softwareengineering.com.cateringsystem.adapter.SearchUserAdapter;
import team4.softwareengineering.com.cateringsystem.database.DatabaseAdapter;
import team4.softwareengineering.com.cateringsystem.model.AdminRegistrationRequestModel;
import team4.softwareengineering.com.cateringsystem.model.SearchUserModel;
import team4.softwareengineering.com.cateringsystem.utils.Utils;

/**
 * Created by vikra on 3/24/2018.
 */

public class SearchUserActivity extends AppCompatActivity {

    private Context mContext;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private SearchUserAdapter mAdapter;
    private RecyclerView rvSearch;
    private EditText etSearch;
    private Dialog confirmDialog;

    private DatabaseAdapter databaseAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        mContext= this;
        init();

    }

    private void init() {

        databaseAdapter = DatabaseAdapter.getDBAdapterInstance(mContext);

        etSearch =(EditText) findViewById(R.id.etSearch);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText("Search User");

        rvSearch = (RecyclerView)findViewById(R.id.rvSearch);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvSearch.setLayoutManager(mLayoutManager);
        rvSearch.setItemAnimator(new DefaultItemAnimator());

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        confirmationDialog();
                        return true;

                }
                return false;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new SearchUserAdapter(databaseAdapter.getUsers(), mContext);
        rvSearch.setAdapter(mAdapter);
    }
}
