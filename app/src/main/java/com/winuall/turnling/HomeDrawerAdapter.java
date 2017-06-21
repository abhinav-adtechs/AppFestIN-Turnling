package com.winuall.turnling;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeDrawerAdapter extends ArrayAdapter<HomeDrawerItem> {

    Context context;
    List<HomeDrawerItem> drawerItemList;
    int layoutResID;

    public HomeDrawerAdapter(Context context, int layoutResourceID, List<HomeDrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.activity_home_view_drawer_item_icon_tv_label);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.activity_home_view_drawer_item_icon);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        HomeDrawerItem homeViewDrawerItem = this.drawerItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(homeViewDrawerItem.getImgResID()));
        drawerHolder.ItemName.setText(homeViewDrawerItem.getItemName());

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }
}

