package com.a10835.easywol.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.activity.HelpInfoWebViewActivity;
import com.a10835.easywol.bean._Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：10835
 * 时间：2018/5/16/20:17
 */
public class HelpInfoAdapter extends RecyclerView.Adapter<HelpInfoAdapter.ViewHolder> {

    private List<_Article> articleList;
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mHelpTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mHelpTitle = itemView.findViewById(R.id.tv_help_fragment_title);
        }
    }

    public HelpInfoAdapter(List<_Article> articleList){
            this.articleList = articleList;
    }

    @Override
    public HelpInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_help_info_adapter_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HelpInfoAdapter.ViewHolder holder, int position) {
        final _Article article = articleList.get(position);
        holder.mHelpTitle.setText(article.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(HelpInfoWebViewActivity.newIntent(holder.itemView.getContext(),article.getUrl(),article.getTitle()));
                ((Activity)holder.itemView.getContext()).startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


}
