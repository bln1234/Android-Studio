package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        float batteryPct = level/(float)scale * 100;
        Toast.makeText(context,"当前电量为"+batteryPct+"%",Toast.LENGTH_SHORT).show();
    }
}
