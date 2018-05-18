package com.a10835.easywol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a10835.easywol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpInfoWebViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_tool_bar)
    TextView tvTitleToolBar;
    @BindView(R.id.iv_icon_tool_bar)
    TextView ivIconToolBar;
    @BindView(R.id.fm_tool_bar_icon)
    RelativeLayout fmToolBarIcon;
    @BindView(R.id.web_view_help)
    WebView webViewHelp;
    private String url;
    private String title;
    public static final String URL = "web_view_url";
    public static final String TITLE = "web_view_title";

    public static Intent newIntent(Context context,String url,String title) {
        Intent intent = new Intent(context, HelpInfoWebViewActivity.class);
        intent.putExtra(URL,url);
        intent.putExtra(TITLE,title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_info_web_view);
        ButterKnife.bind(this);
        setWhiteStatusBar();

    }

    @Override
    public void setEevent() {
        ivIconToolBar.setOnClickListener(this);
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra(URL);
        title = getIntent().getStringExtra(TITLE);
        tvTitleToolBar.setText(title);
        webViewHelp.getSettings().setJavaScriptEnabled(true);
        webViewHelp.loadUrl(url);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_icon_tool_bar:
                finish();
                break;
        }
    }
}
