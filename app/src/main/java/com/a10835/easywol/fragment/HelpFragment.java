package com.a10835.easywol.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.adapter.HelpInfoAdapter;
import com.a10835.easywol.adapter.SpaceItemDecoration;
import com.a10835.easywol.bean._Article;
import com.a10835.easywol.utils.LogUtil;
import com.a10835.easywol.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.a10835.easywol.view.ToastUtil.toast;

/**
 * Created by 10835 on 2018/4/15.
 */

public class HelpFragment extends BaseFragment {

    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecycleView;
    private HelpInfoAdapter mAdapter;
    private List<_Article> articleList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        articleList = new ArrayList<>();
    }

    @Override
    public void addView(FrameLayout frameLayout) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_help,null);
        swipeRefreshLayout = view.findViewById(R.id.sw_fragment_home);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.pink,R.color.blue,R.color.ching,R.color.red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        mRecycleView = view.findViewById(R.id.rv_devices);
        FrameLayout.LayoutParams lp = new FrameLayout.
                LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        frameLayout.addView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    public void setToolBarTitleName(TextView textView) {
        textView.setText("帮助");
    }

    @Override
    public void setToolBarIcon(ImageView imageView,FrameLayout fm) {
        fm.setVisibility(View.GONE);
    }


    public void setEvent() {

    }

    /**请求数据
     *
     */
    public void requestData(){
        BmobQuery<_Article> query = new BmobQuery<_Article>();
        query.order("-createdAt");
        query.findObjects(new FindListener<_Article>() {
            @Override
            public void done(List<_Article> object, BmobException e) {
                if(e==null){
                    swipeRefreshLayout.setRefreshing(false);
                    articleList.clear();
                    articleList.addAll(object);
                    setOrNotifyAdapter();
                }else{
                    swipeRefreshLayout.setRefreshing(false);
                    LogUtil.setTag(getClass().getSimpleName());
                    LogUtil.d(e.getErrorCode() + e.getMessage());
                    ToastUtil.show(mContext,e.getErrorCode()+": "+e.getMessage());
                }
            }
        });
    }


    /**
     * 设置适配器
     */
    public void setOrNotifyAdapter(){
        if (mAdapter == null){
            mAdapter = new HelpInfoAdapter(articleList);
            mRecycleView.addItemDecoration(new SpaceItemDecoration(24));
            mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecycleView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

}
