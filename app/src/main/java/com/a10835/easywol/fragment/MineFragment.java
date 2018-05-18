package com.a10835.easywol.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
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

import com.a10835.easywol.R;
import com.a10835.easywol.activity.FeedBackActivity;
import com.a10835.easywol.activity.UserInfoActivity;
import com.a10835.easywol.view.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10835 on 2018/4/15.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
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
    @BindView(R.id.ll_fragment_mine_git)
    LinearLayout llFragmentMineGit;
    @BindView(R.id.tv_mine_fragment_phone)
    TextView tvMineFragmentPhone;
    @BindView(R.id.tv_fragment_mine_version)
    TextView tvFragmentMineVersion;
    @BindView(R.id.tv_fragment_mine_sina)
    TextView tvFragmentMineSina;
    @BindView(R.id.tv_fragment_mine_email)
    TextView tvFragmentMineEmail;
    @BindView(R.id.tv_fragment_mine_git)
    TextView tvFragmentMineGit;
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


    public void setEvent() {

        llFragmentMineUserInfo.setOnClickListener(this);
        llFragmentMineShare.setOnClickListener(this);
        llFragmentMineFeadBack.setOnClickListener(this);
        llFragmentMineCheckUpdate.setOnClickListener(this);
        llFragmentMineSinaweibo.setOnClickListener(this);
        llFragmentMineGit.setOnClickListener(this);
        llFragmentMineEmail.setOnClickListener(this);
        llFragmentMineTipping.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        setEvent();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_fragment_mine_user_info:
                Intent intent = UserInfoActivity.newIntent(mContext);
                startActivity(intent);
                break;
            case R.id.ll_fragment_mine_share:

                break;
            case R.id.ll_fragment_mine_tipping:

                break;
            case R.id.ll_fragment_mine_fead_back:
                startActivity(new Intent(FeedBackActivity.newIntent(mContext)));
                break;
            case R.id.ll_fragment_mine_check_update:

                break;

            case R.id.ll_fragment_mine_sinaweibo:
                saveTextToClipBoardManager(tvFragmentMineSina.getText().toString().trim(), "\"@喜盈盈虾条\"以复制到剪贴板，打开微博搜索并关注");
                break;
            case R.id.ll_fragment_mine_git:
                saveTextToClipBoardManager(tvFragmentMineGit.getText().toString().trim(), "\"QiangzhenZhu\"以复制到剪贴板");
                break;

            case R.id.ll_fragment_mine_email:
                saveTextToClipBoardManager(tvFragmentMineEmail.getText().toString().trim(), "\"邮箱\"以复制到剪贴板");
                break;
            default:
                break;


        }
    }

    public void saveTextToClipBoardManager(String text, String toast) {
        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("EASYWOL", text);
        cm.setPrimaryClip(clipData);
        ToastUtil.show(mContext, toast);
    }


}
