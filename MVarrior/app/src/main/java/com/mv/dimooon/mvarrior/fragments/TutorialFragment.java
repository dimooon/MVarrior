package com.mv.dimooon.mvarrior.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mv.dimooon.mvarrior.R;
import com.mv.dimooon.mvarrior.fragments.tutorial.TutorialMain;
import com.mv.dimooon.mvarrior.fragments.tutorial.TutorialSecond;
import com.mv.dimooon.mvarrior.fragments.tutorial.TutorialThird;

import java.util.ArrayList;

/**
 * Created by dimooon on 22.02.16.
 */
public class TutorialFragment extends BaseFragment {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.tutorial_screen_fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager = (ViewPager) getView().findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == pager.getAdapter().getCount()-1){
                    MainFragment newFragment = new MainFragment();
                    android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_activity_root, newFragment);
                    transaction.commit();
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putBoolean("tutorialPassed",true).commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> tutorialFragments = new ArrayList<>();
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            tutorialFragments.add(new TutorialMain());
            tutorialFragments.add(new TutorialSecond());
            tutorialFragments.add(new TutorialThird());
        }

        @Override
        public Fragment getItem(int position) {
            return tutorialFragments.get(position);
        }

        @Override
        public int getCount() {
            return tutorialFragments.size();
        }
    }
}
