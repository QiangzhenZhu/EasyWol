package com.a10835.easywol.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.activity.AddDevicesActivity;
import com.a10835.easywol.activity.BaseActivity;
import com.a10835.easywol.utils.LogUtil;
import com.a10835.easywol.view.ToastUtil;

/**
 * Created by 10835 on 2018/4/15.
 */

public class HomeFragment extends BaseFragment {
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            ((AppCompatActivity) getActivity()).getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = ((AppCompatActivity) getActivity()).getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }


    }


    @Override
    public void addView(FrameLayout frameLayout) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home,null);
        swipeRefreshLayout = view.findViewById(R.id.sw_fragment_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.pink,R.color.blue,R.color.ching,R.color.red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ToastUtil.show(mContext,"swipe");
            }
        });
        FrameLayout.LayoutParams lp = new FrameLayout.
                LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        frameLayout.addView(view);
    }

    @Override
    public void setToolBarTitleName(TextView textView) {
        textView.setText("设备");
    }

    @Override
    public void setToolBarIcon(ImageView imageView,FrameLayout fm) {
        imageView.setImageResource(R.drawable.ic_icon_tool_bar_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(mContext, AddDevicesActivity.class);
                startActivity(intent);
            }
        });
    }


}
