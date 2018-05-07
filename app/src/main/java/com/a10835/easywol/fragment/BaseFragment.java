package com.a10835.easywol.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;

/**
 * Created by 10835 on 2018/5/3.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;
    private TextView mToolBarTitle;
    private ImageView mToolBarIcon;
    private FrameLayout frameLayout;
    private FrameLayout fmIcon;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWhiteBar();



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,container,false);
        Toolbar toolbar = view.findViewById(R.id.tool_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mToolBarTitle = view.findViewById(R.id.tv_title_tool_bar);
        mToolBarIcon = view.findViewById(R.id.iv_icon_tool_bar);
        frameLayout = view.findViewById(R.id.fragment_container);
        fmIcon = view.findViewById(R.id.fm_tool_bar_icon);
        addView(frameLayout);
        setToolBarTitleName(mToolBarTitle);
        setToolBarIcon(mToolBarIcon,fmIcon);
        return view;
    }
    /**
     * android6.0以上标题栏显示为白色，状态栏为白底黑色
     */
    public void setWhiteBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            ((AppCompatActivity) getActivity()).getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = ((AppCompatActivity) getActivity()).getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * 向Fragment_container中添加内容
     */
    public abstract void addView(FrameLayout frameLayout);

    /**
     * 设置ToolBar字体内容
     */
    public  abstract void setToolBarTitleName( TextView textView);

    /**
     * 设置ToolBar的icon
     */
    public abstract void setToolBarIcon( ImageView imageView,FrameLayout frameLayout);

}
