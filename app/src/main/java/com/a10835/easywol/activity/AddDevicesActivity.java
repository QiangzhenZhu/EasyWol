package com.a10835.easywol.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.bean.Devices;
import com.a10835.easywol.utils.LogUtil;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;
import com.a10835.easywol.view.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddDevicesActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_add_devices_ip)
    XEditText etAddDevicesIp;
    @BindView(R.id.et_add_devices_port)
    XEditText etAddDevicesPort;
    @BindView(R.id.et_add_devices_password)
    XEditText etAddDevicesPassword;
    @BindView(R.id.et_add_devices_mac)
    XEditText etAddDevicesMac;
    @BindView(R.id.et_add_devices_subnet_mask)
    XEditText etAddDevicesSubnetMask;
    @BindView(R.id.btn_add_devices_save)
    TextView btnAddDevicesSave;
    @BindView(R.id.tv_right_title_tool_bar)
    TextView tvRightTitleToolBar;
    @BindView(R.id.et_add_devices_name)
    XEditText etAddDevicesName;
    private String ip;
    private String port;
    private String mac;
    private String password;
    private String subnetmask;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_devices2);
        ButterKnife.bind(this);
        setWhiteStatusBar();
    }

    @Override
    public void setEevent() {
        etAddDevicesIp.setOnClickListener(this);
        etAddDevicesMac.setOnClickListener(this);
        etAddDevicesPassword.setOnClickListener(this);
        etAddDevicesPort.setOnClickListener(this);
        etAddDevicesSubnetMask.setOnClickListener(this);
        btnAddDevicesSave.setOnClickListener(this);
        tvRightTitleToolBar.setOnClickListener(this);
    }

    @Override
    public void initData() {
        etAddDevicesIp.setDeleteDrawable();
        etAddDevicesMac.setDeleteDrawable();
        etAddDevicesPassword.setDeleteDrawable();
        etAddDevicesPort.setDeleteDrawable();
        etAddDevicesSubnetMask.setDeleteDrawable();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_devices_save:
                save();
                break;
            case R.id.tv_right_title_tool_bar:
                finish();
                break;
            default:
                break;
        }
    }

    public void save() {
        ip = etAddDevicesIp.getText().toString().trim();
        port = etAddDevicesPort.getText().toString().trim();
        password = etAddDevicesPassword.getText().toString().trim();
        mac = etAddDevicesMac.getText().toString().trim();
        subnetmask = etAddDevicesSubnetMask.getText().toString().trim();
        name = etAddDevicesName.getText().toString().trim();
        if (TextUtils.isEmpty(ip)) {
            etAddDevicesIp.showSharkAnimotion();
            return;
        } else if (TextUtils.isEmpty(port)) {
            etAddDevicesPort.showSharkAnimotion();
            return;
        /*} else if (TextUtils.isEmpty(password)){
            etAddDevicesPassword.showSharkAnimotion();
            return;*/
        } else if (TextUtils.isEmpty(mac)) {
            etAddDevicesMac.showSharkAnimotion();
            return;
        /*} else if (TextUtils.isEmpty(subnetmask)){
            etAddDevicesSubnetMask.showSharkAnimotion();
            return;*/
        }else if (TextUtils.isEmpty(name)){
            etAddDevicesName.showSharkAnimotion();
            return;
        } else {
            final ProgressDialog dialog = new ProgressDialog();
            dialog.show(getSupportFragmentManager(), "AddDevices");
            BmobUser user = BmobUser.getCurrentUser();
            final Devices devices = new Devices();
            devices.setIp(ip);
            devices.setMac(mac);
            devices.setPort(port);
            devices.setPassword(password);
            devices.setSubnetmaster(subnetmask);
            devices.setUser(user);
            devices.setName(name);
            devices.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        dialog.dismiss();
                        LogUtil.setTag("addDevices");
                        LogUtil.d("add Devices success" + ip + ":" + ip);
                        startActivity(HomeActivity.newIntent(mContext, 0));
                    } else {
                        dialog.dismiss();
                        LogUtil.setTag("addDevices");
                        LogUtil.d("add Devices error");
                        ToastUtil.show(mContext, e.getMessage());
                    }
                }
            });
        }
    }
}
