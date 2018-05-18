package com.a10835.easywol.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.activity.AddDevicesTipsActivity;
import com.a10835.easywol.adapter.DevicesAdapter;
import com.a10835.easywol.adapter.SpaceItemDecoration;
import com.a10835.easywol.bean.Devices;
import com.a10835.easywol.common.http.udp.UdpHelper;
import com.a10835.easywol.utils.LogUtil;
import com.a10835.easywol.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 10835 on 2018/4/15.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_devices)
    RecyclerView rvDevices;
    Unbinder unbinder;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Devices> devicesList;
    private DevicesAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ((AppCompatActivity) getActivity()).getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = ((AppCompatActivity) getActivity()).getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
            devicesList = new ArrayList<>();

        }

    }



    @Override
    public void addView(FrameLayout frameLayout) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
        swipeRefreshLayout = view.findViewById(R.id.sw_fragment_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.pink, R.color.blue, R.color.ching, R.color.red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        FrameLayout.LayoutParams lp = new FrameLayout.
                LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        frameLayout.addView(view);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    public void setToolBarTitleName(TextView textView) {
        textView.setText("设备");
    }

    @Override
    public void setToolBarIcon(ImageView imageView, FrameLayout fm) {
        imageView.setImageResource(R.drawable.ic_icon_tool_bar_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddDevicesTipsActivity.class);
                startActivity(intent);
            }
        });
    }


    public void setEvent() {

    }

    public void requestData() {
        BmobUser user = BmobUser.getCurrentUser();
        BmobQuery<Devices> query = new BmobQuery<Devices>();
        query.addWhereEqualTo("user", user);  // 查询当前用户的所有设备
        query.order("-updatedAt");
        query.findObjects(new FindListener<Devices>() {
            @Override
            public void done(List<Devices> list, BmobException e) {
                if (e == null) {
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    LogUtil.setTag("Querydevices");
                    LogUtil.d("query success");
                    devicesList.clear();
                    devicesList.addAll(list);
                    setOrUpdate();

                } else {
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    ToastUtil.show(mContext, "错误代码：" + e.getErrorCode()+e.getMessage());
                    LogUtil.setTag("Querydevices");
                    LogUtil.d("query error " + e.getMessage());
                }
            }
        });
    }

    /**
     * 设置适配器，或更新数据
     */
    public void setOrUpdate() {
        if (adapter == null) {
            adapter = new DevicesAdapter(devicesList, new DevicesAdapter.onActiveListner() {
                @Override
                public void onActiveClickListner(int position) {
                    final Devices devices = devicesList.get(position);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UdpHelper.send(devices.getIp(),devices.getPort(),devices.getMac());
                        }
                    }).start();



                }

                @Override
                public void onItemClickListner(int position) {

                }
            });
            rvDevices.setLayoutManager(new LinearLayoutManager(mContext));
            rvDevices.addItemDecoration(new SpaceItemDecoration(38));
            rvDevices.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
