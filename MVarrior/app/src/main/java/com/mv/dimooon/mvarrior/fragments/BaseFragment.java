package com.mv.dimooon.mvarrior.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dimooon on 22.02.16.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutId();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

}
