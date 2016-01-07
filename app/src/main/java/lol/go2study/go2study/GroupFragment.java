package lol.go2study.go2study;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;
import Go2Study.Models.Group;
import java.io.UnsupportedEncodingException;
import java.util.List;
import Go2Study.Api.GroupsApi;
import lol.go2study.go2study.CallBack.GroupsCallbacks;
import lol.go2study.go2study.Models.GroupModel;



/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends android.support.v4.app.Fragment {

    private List<Group> groups;
    private SharedPreferences pref;
    private GroupsApi groupsApi;
    private OAuthSettings settings;
    private SwipeRefreshLayout swipeContainer;

    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView  staffListView = (ListView) view.findViewById(R.id.listViewGroups);
        Log.v("test", "test");
        //images =  BitMapImages(people);
        final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, groups);
        staffListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        staffListView.setItemsCanFocus(false);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        settings = new OAuthSettings();
        groupsApi = new GroupsApi();
        final JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        final String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainerGroups);
        final ListView staffListView = (ListView)rootView.findViewById(R.id.listViewGroups);

        try {
            //GroupModel.deleteAll();
            GroupFragment.this.groups = GroupModel.getAllGroups();
            if(groups == null || groups.size() == 0)
            {
                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {
                            GroupsCallbacks groupsCallbacks = new GroupsCallbacks();
                            GroupsCallbacks.GetGroupsCallback getGroupsCallback = groupsCallbacks.new GetGroupsCallback();
                            groupsApi.groupsGet(getGroupsCallback, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (getGroupsCallback.groupsList == null || getGroupsCallback.groupsList.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {
                                    // swipeContainer.setRefreshing(false);
                                }
                            }
                            GroupFragment.this.groups = getGroupsCallback.groupsList;


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



                            GroupsCallbacks groupsCallbacks = new GroupsCallbacks();
                            GroupsCallbacks.GetGroupsCallback getGroupsCallback = groupsCallbacks.new GetGroupsCallback();
                            groupsApi.groupsGet(getGroupsCallback, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (getGroupsCallback.groupsList == null || getGroupsCallback.groupsList.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {
                                    // swipeContainer.setRefreshing(false);
                                }
                            }
                            GroupFragment.this.groups = getGroupsCallback.groupsList;


                            final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, GroupFragment.this.groups);

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

    public  class YourRecyclerAdapter extends ArrayAdapter<Group> {

        private Context context;
        private List<Group> groups;
        private MLRoundedImageView roundedImageView;



        public YourRecyclerAdapter(Context context, int resource, List<Group> groups) {
            super(context, resource,groups );
            this.context = context;
            this.groups = groups;
            this.roundedImageView =  new MLRoundedImageView(context);


        }

        public void refreshEvents(List<Group> groups) {
            this.groups.clear();
            this.groups.addAll(groups);
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

            Group p = getItem(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextViewStaff);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextViewStaff);
              //  ImageView image = (ImageView)v.findViewById(R.id.rowImageView);  //for the image

                if (tt1 != null) {
                    tt1.setText(p.getName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getPcnlist().toString());
                }
            }
            return v;
        }

    }



}
