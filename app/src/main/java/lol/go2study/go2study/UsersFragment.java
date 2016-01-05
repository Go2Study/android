package lol.go2study.go2study;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import FontysICT.Invoker.ApiException;
import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;
import lol.go2study.go2study.CallBack.GroupsCallbacks;
import lol.go2study.go2study.CallBack.PeopleCallback;
import lol.go2study.go2study.CallBack.UsersCallbacks;
import lol.go2study.go2study.Models.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends android.support.v4.app.Fragment {

    List<User> users;
    SharedPreferences pref;
    private OAuthSettings settings;
    private SwipeRefreshLayout swipeContainer;
    private UsersApi usersApi;
    private List<Bitmap> images;

    public UsersFragment() {
        // Required empty public constructor

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView  staffListView = (ListView) view.findViewById(R.id.listViewUsers);
        Log.v("test","test");
        //images =  BitMapImages(people);
        final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, users);
        staffListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        staffListView.setItemsCanFocus(false);
        swipeContainer.setRefreshing(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users,container,false);


        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        settings = new OAuthSettings();
        usersApi = new UsersApi();
        final JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        final String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainerUsers);
        final ListView staffListView = (ListView)rootView.findViewById(R.id.listViewUsers);

        try {
            UserModel.deleteAll();
            UsersFragment.this.users = UserModel.getAllUsers();
            if(users == null || users.size() == 0)
            {
                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {
                            UsersCallbacks usersCallbacks = new UsersCallbacks();
                            UsersCallbacks.GetUsersCallBack getUsersCallBack = usersCallbacks.new GetUsersCallBack();
                            usersApi.usersGet(getUsersCallBack, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (usersCallbacks.userList == null || usersCallbacks.userList.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {
                                    // swipeContainer.setRefreshing(false);
                                }
                            }
                            UsersFragment.this.users = usersCallbacks.userList;


                        }  catch (Go2Study.Invoker.ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {



                            UsersCallbacks usersCallbacks = new UsersCallbacks();
                            UsersCallbacks.GetUsersCallBack getUsersCallBack = usersCallbacks.new GetUsersCallBack();
                            usersApi.usersGet(getUsersCallBack, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (usersCallbacks.userList == null || usersCallbacks.userList.isEmpty()){
                                if (System.currentTimeMillis() - timestampNow >= 6000l){
                                    // swipeContainer.setRefreshing(false);
                                }
                            }

                            UsersFragment.this.users = usersCallbacks.userList;

                            final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, UsersFragment.this.users);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    staffListView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    staffListView.setItemsCanFocus(false);

                                }
                            });

                            swipeContainer.setRefreshing(false);

                        }catch (Go2Study.Invoker.ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        return rootView;

    }



    public  class YourRecyclerAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> users;
        private MLRoundedImageView roundedImageView;



        public YourRecyclerAdapter(Context context, int resource, List<User> users) {
            super(context, resource,users );
            this.context = context;
            this.users = users;
            this.roundedImageView =  new MLRoundedImageView(context);


        }

        public void refreshEvents(List<User> users) {
            this.users.clear();
            this.users.addAll(users);
            notifyDataSetChanged();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_row_staff_user, null);
            }

            User p = getItem(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextView);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextView);
                ImageView image = (ImageView)v.findViewById(R.id.rowImageView);  //for the image

                if (tt1 != null) {
                    tt1.setText(p.getDisplayName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getEmail().toString());
                }
                if(image != null)
                {

                }
            }
            return v;
        }
    }
}


