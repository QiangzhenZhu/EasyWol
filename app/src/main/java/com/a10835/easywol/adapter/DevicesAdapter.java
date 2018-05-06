package com.a10835.easywol.adapter;

import android.print.PrinterId;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a10835.easywol.R;
import com.a10835.easywol.bean.Devices;
import com.a10835.easywol.view.ProgressDialog;
import com.a10835.easywol.view.ToastUtil;

import java.security.PrivilegedAction;
import java.util.List;

/**
 * Created by 10835 on 2018/5/5.
 */

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.ViewHolder> {

    private List<Devices> devicesList;
    private onActiveListner listner;
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameText;
        private TextView mIP;
        private ImageView mActive;
        private View item;

        public ViewHolder(View itemView) {
            super(itemView);
            mNameText = itemView.findViewById(R.id.tv_home_fragment_name);
            mIP = itemView.findViewById(R.id.ip_home_fragment_name);
            mActive = itemView.findViewById(R.id.iv_home_fragment_active);
            item = itemView;

        }
    }

    public DevicesAdapter(List<Devices> devicesList,onActiveListner listner) {
        this.devicesList = devicesList;
        this.listner = listner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view_devices,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Devices devices = devicesList.get(position);
        holder.mNameText.setText(devices.getName()+"");
        holder.mIP.setText(devices.getIp()+"");
        holder.mActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onActiveClickListner(position);
            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClickListner(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }
    public interface onActiveListner{
         void onActiveClickListner(int position);
         void onItemClickListner(int position);
    }

}
