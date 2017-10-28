package com.rabbitdigital.chofficerapplication.presentation.home.activity;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import com.rabbitdigital.chofficerapplication.R;
import com.rabbitdigital.chofficerapplication.presentation.home.fragment.FourFragment;
import com.rabbitdigital.chofficerapplication.presentation.home.fragment.OneFragment;
import com.rabbitdigital.chofficerapplication.presentation.home.fragment.SecondFragment;
import com.rabbitdigital.chofficerapplication.presentation.home.fragment.ThreeFragment;
import com.rabbitdigital.chofficerapplication.presentation.splash.activity.BaseActvity;

public class HomeActivity extends BaseActvity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public int setLayoutResuourse() {
        return R.layout.activity_home;
    }

    @Override
    public void initGUI() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);

        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public void initData() {

    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(R.string.my_work);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_work_black_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.my_office);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_computer_black_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(R.string.my_self);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_person_black_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(R.string.chat);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_chat_black_24dp, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabThree);
    }

}
