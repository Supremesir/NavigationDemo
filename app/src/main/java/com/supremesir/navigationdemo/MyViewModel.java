package com.supremesir.navigationdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

/**
 * @author HaoFan Fang
 * @date 2020/3/14 20:01
 */

public class MyViewModel extends AndroidViewModel {

    private SavedStateHandle handle;
    private String key = getApplication().getResources().getString(R.string.key);
    private String shpName = getApplication().getResources().getString(R.string.shpName);

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(key)) {
            load();
        }
    }

    public MutableLiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    public void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        int x = shp.getInt(key, 0);
        handle.set(key, x);
    }

    public void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key, getNumber().getValue());
        editor.apply();
    }
}
