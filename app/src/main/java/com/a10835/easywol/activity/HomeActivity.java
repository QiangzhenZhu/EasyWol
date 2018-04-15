package com.a10835.easywol.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.a10835.easywol.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private BottomNavigationView mBottomNavigationView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();

    }

    public void initView() {
        mBottomNavigationView = findViewById(R.id.bootom_navigationview_tab);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(mContext,"HomePage",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_add:
                        Toast.makeText(mContext,"Add",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_mine:
                        Toast.makeText(mContext,"Mine",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

}
