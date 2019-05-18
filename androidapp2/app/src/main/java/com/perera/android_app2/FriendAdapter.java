package com.perera.android_app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FriendModel> friendList;

    public FriendAdapter(Context context, ArrayList<FriendModel> friendList) {

        this.context = context;
        this.friendList = friendList;
    }


    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.friend_item_layout, null, true);


            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvphone = (TextView) convertView.findViewById(R.id.phone);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.tvname.setText(friendList.get(position).getFirstName());
        holder.tvphone.setText(friendList.get(position).getPhone());




        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvphone;

    }

}
