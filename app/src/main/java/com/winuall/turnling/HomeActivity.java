package com.winuall.turnling;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class  HomeActivity extends AppCompatActivity {
    @BindView(R.id.activity_home_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.activity_home_drawer)
    ListView drawerList;

    @BindView(R.id.activity_home_content_frame)
    FrameLayout frameLayoutMain ;

    @BindView(R.id.activity_home_main_toolbar)
    Toolbar toolbar;

    private int activityNumber = 1;

    private int drawerPosition ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initDrawerView();
        initChatFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private void initDrawerView() {
        LayoutInflater inflater = getLayoutInflater();
        /*View listHeaderView = inflater.inflate(R.layout.activity_home_view_drawer_header,drawerList, false);
        drawerList.addHeaderView(listHeaderView);
        listHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });*/

        ArrayList<HomeDrawerItem> itemList = new ArrayList<>();
        /*itemList.add(new HomeViewDrawerItem("Institute", R.drawable.ic_institute));
        itemList.add(new HomeViewDrawerItem("Practice", R.drawable.ic_practice));
        itemList.add(new HomeViewDrawerItem("Doubts", R.drawable.ic_question_mark));
        itemList.add(new HomeViewDrawerItem("Performance", R.drawable.ic_performnce));
        itemList.add(new HomeViewDrawerItem("Study Material", R.drawable.ic_study_material));
        itemList.add(new HomeViewDrawerItem("Archive", R.drawable.ic_archive));*/

        drawerList.setAdapter(new HomeDrawerAdapter(this, R.layout.item_home_view_drawer, itemList));
        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name
        );
        drawerLayout.addDrawerListener(drawerToggle);
    }

    private void initChatFragment(){
        ChatFragment chatFragment = new ChatFragment() ;

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_home_content_frame, chatFragment)
                .commit();
        //drawerList.setItemChecked(1, true);
    }

    private void selectItem(int position) {
        drawerPosition = position ;
        if(position == activityNumber || position == 0) {
            if(position != 0)
                drawerLayout.closeDrawer(drawerList);
            return;
        }
        activityNumber = position;

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        switch (drawerPosition) {
            /*case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_home_content_frame, new DashboardFragment())
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_home_content_frame, new PracticeFragment())
                        .commit();
                break;
            case 3:
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.activity_home_content_frame, new ProfileFragment(), null)
                        .addToBackStack(null)
                        .commit();
                break;
            case 5:
                break;
            case 6:*/
        }
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
