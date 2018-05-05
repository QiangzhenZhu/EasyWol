package com.a10835.easywol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.a10835.easywol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDevicesTipsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_right_title_tool_bar)
    TextView tvRightTitleToolBar;
    @BindView(R.id.btn_add_devices_next)
    TextView btnAddDevicesNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_devices);
        ButterKnife.bind(this);
        setWhiteStatusBar();
    }

    @Override
    public void setEevent() {
        tvRightTitleToolBar.setOnClickListener(this);
        btnAddDevicesNext.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_right_title_tool_bar:
                finish();
                break;
            case R.id.btn_add_devices_next:
                Intent intent = new Intent(mContext,AddDevicesActivity.class);
                startActivity(intent);
                finish();
                break;
            default:break;

        }
    }
}
