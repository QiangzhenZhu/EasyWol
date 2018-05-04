package com.a10835.easywol.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10835 on 2018/5/2.
 */

public class ActivityCollector {
    private static ActivityCollector activityCollector;
    private static List<Activity> activityList;
    private ActivityCollector(){
        activityList = new ArrayList<>();
    }

    public static ActivityCollector getInstance(){
        if (activityCollector == null){
            synchronized (ActivityCollector.class){
                if (activityCollector == null){
                    activityCollector = new ActivityCollector();
                    return activityCollector;
                }
            }
        }
        return activityCollector;
    }

    /**
     * 添加一个Activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if (activity != null){
            activityList.add(activity);
        }
    }

    /**
     * 移除一个Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    /**
     * 结束所有Activity
     */
    public static void finshAll(){
        for (Activity activity : activityList) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
