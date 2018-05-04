package com.a10835.easywol.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.utils.LogUtil;

/**
 * Created by 10835 on 2018/4/15.
 */

public class HelpFragment extends BaseFragment {

    private Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void addView(FrameLayout frameLayout) {

    }

    @Override
    public void setToolBarTitleName(TextView textView) {
        textView.setText("帮助");
    }

    @Override
    public void setToolBarIcon(ImageView imageView,FrameLayout fm) {
        fm.setVisibility(View.GONE);
    }


}
