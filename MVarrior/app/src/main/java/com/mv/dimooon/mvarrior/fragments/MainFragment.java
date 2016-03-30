package com.mv.dimooon.mvarrior.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mv.dimooon.mvarrior.FileUtils;
import com.mv.dimooon.mvarrior.R;
import com.mv.dimooon.mvarrior.dao.Player;

import java.util.ArrayList;

/**
 * Created by dimooon on 22.02.16.
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.main_screen_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parseAndGetCurrentPlayer();
    }

    private void parseAndGetCurrentPlayer(){

        ArrayList<Player> players = (ArrayList<Player>) Player.listAll(Player.class);
        ((TextView)(getActivity().findViewById(R.id.helloText))).setText(players.toString());
    }
}
