package com.a10835.easywol.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.a10835.easywol.R;
import com.a10835.easywol.fragment.HelpFragment;
import com.a10835.easywol.fragment.HomeFragment;
import com.a10835.easywol.fragment.MineFragment;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";
    private static final int HOMEPAGER_FRAGMENT = 0;
    private static final int ADD_FRAGMENT = 1;
    private static final int MINE_FRAGMENT = 2;

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;
    private HelpFragment addFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initData1();

    }

    @Override
    public void setEevent() {
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelection(HOMEPAGER_FRAGMENT);
    }

    //初始化视图
    public void initView() {
        mBottomNavigationView = findViewById(R.id.bootom_navigationview_tab);
        //设置匿名监听器，监听点击了底部哪一个tab
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        setSelection(HOMEPAGER_FRAGMENT);
                        break;
                    case R.id.action_add:
                        setSelection(ADD_FRAGMENT);
                        break;
                    case R.id.action_mine:
                        setSelection(MINE_FRAGMENT);
                        break;
                }
                return true;
            }
        });

    }


    //初始化数据
    public void initData1(){
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        addFragment  = new HelpFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container,homeFragment)
                .add(R.id.fl_fragment_container,addFragment)
                .add(R.id.fl_fragment_container,mineFragment)
                .commit();
    }

    //选择显示哪一个Fragemnt
    public void setSelection(int selection){
        hideAllFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (selection){
            case HOMEPAGER_FRAGMENT:
                transaction.show(homeFragment).commit();
                break;
            case ADD_FRAGMENT:
                transaction.show(addFragment).commit();
                break;
            case MINE_FRAGMENT:
                transaction.show(mineFragment).commit();
                break;
            default:
                break;


        }
    }


    //隐藏全部的Fragment
    public void hideAllFragment(){
        //if (homeFragment != null){
            fragmentManager.beginTransaction().hide(homeFragment).commit();
        //}
        //if (addFragment != null){
            fragmentManager.beginTransaction().hide(addFragment).commit();
       // }
       // if (mineFragment != null){
            fragmentManager.beginTransaction().hide(mineFragment).commit();
        //}
    }





}
