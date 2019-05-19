/*
Who's Hungry android application
Authors - IT16067134 & IT16058910
CTSE pair project
Android Project
*/

package com.perera.android_app2;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FriendList extends AppCompatActivity {

    private String URL = "https://peaceful-mountain-19289.herokuapp.com/friend/";
    private SwipeMenuListView listView;
    ArrayList<FriendModel> friendList;
    AsyncHttpClient client;
    FriendModel friendObj;
    private FriendAdapter friendAdapter;
    TextView toolbartxt;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.user_info:
            {
                Intent intent = new Intent(FriendList.this, AddUser.class);
                startActivity(intent);
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        friendList = new ArrayList<>();
        listView = findViewById(R.id.lv);
        toolbartxt = findViewById(R.id.textView);
        toolbartxt.setText("Friend List");


        getAllFriends();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendModel newFriend = new FriendModel();
                Intent intent = new Intent(FriendList.this, AddFriend.class);
                intent.putExtra("id", newFriend);
                FriendList.this.startActivity(intent);


            }
        });






     SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(51, 204,
                        51)));
                // set item width
                openItem.setWidth(170);
                // set a icon
                openItem.setIcon(R.drawable.ic_sms);

                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);



        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                       sendSMS(position);
                        break;
                    case 1:
                       removeFriend(position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


}



//method to send sms
    private void sendSMS(int position) {
        Log.d("friend", "Sending message");

        String message = "Let's go for lunch!!!! ";
        String number = friendList.get(position).getPhone();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            Log.d("friend", e.toString());
        }
    }

    //method to remove friend
    private void removeFriend(int position) {
        Log.d("friend", "removing friend");

        String id= friendList.get(position).get_id();
        client = new AsyncHttpClient();
        client.delete(URL+id,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                getAllFriends();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        Intent intent = new Intent(FriendList.this, FriendList.class);
        startActivity(intent);

    }

    //method to get all the friends
    private void getAllFriends(){
        Log.d("friend", "inside in the get data");
        client = new AsyncHttpClient();

        client.get(URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("friend", response.toString());
                super.onSuccess(statusCode, headers, response);
                try{
                    JSONArray jArray = response.getJSONArray("data");

                    for(int i=0;i<jArray.length();i++)
                        try {

                            JSONObject obj = jArray.getJSONObject(i);
                            friendObj= new FriendModel(
                                    obj.getString("_id"),
                                    obj.getString("firstName"),
                                    obj.getString("secondName"),
                                    obj.getString("phone"));

                            friendList.add(friendObj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("friend", e.toString());
                        }
                    friendAdapter = new FriendAdapter(FriendList.this,friendList);
                    listView.setAdapter(friendAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            // TODO Auto-generated method stub
                            Log.d("friend", "view list item");
                            FriendModel f = friendList.get(position);
                           // Toast.makeText(FriendList.this, friendList.get(position).getFirstName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FriendList.this, AddFriend.class);
                            intent.putExtra("id", f);
                            FriendList.this.startActivity(intent);

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("friend", "fail to get request");
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        //getAllFriends();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



}
