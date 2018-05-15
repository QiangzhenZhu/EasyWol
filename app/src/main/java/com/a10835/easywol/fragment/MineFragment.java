package com.a10835.easywol.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a10835.easywol.R;
import com.a10835.easywol.activity.UserInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10835 on 2018/4/15.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.ll_fragment_mine_share)
    LinearLayout llFragmentMineShare;
    @BindView(R.id.ll_fragment_mine_tipping)
    LinearLayout llFragmentMineTipping;
    @BindView(R.id.ll_fragment_mine_fead_back)
    LinearLayout llFragmentMineFeadBack;
    @BindView(R.id.ll_fragment_mine_check_update)
    LinearLayout llFragmentMineCheckUpdate;
    @BindView(R.id.ll_fragment_mine_sinaweibo)
    LinearLayout llFragmentMineSinaweibo;
    @BindView(R.id.ll_fragment_mine_email)
    LinearLayout llFragmentMineEmail;
    Unbinder unbinder;
    @BindView(R.id.ll_fragment_mine_user_info)
    LinearLayout llFragmentMineUserInfo;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void addView(FrameLayout frameLayout) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine, null);
        FrameLayout.LayoutParams lp = new FrameLayout.
                LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        frameLayout.addView(view);
    }

    @Override
    public void setToolBarTitleName(TextView textView) {
        textView.setText("我的");
    }

    @Override
    public void setToolBarIcon(ImageView imageView, FrameLayout fm) {
        imageView.setImageResource(R.drawable.ic_news_tool_bar);
    }

    @Override
    public void setEvent() {
        //跳转到个人信息页
        /*llFragmentMineUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = UserInfoActivity.newIntent(mContext);
                startActivity(intent);
            }
        });*/

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
