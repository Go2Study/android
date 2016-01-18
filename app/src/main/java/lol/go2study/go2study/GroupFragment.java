package lol.go2study.go2study;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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
    private   YourRecyclerAdapter adapter;
    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView  staffListView = (ListView) view.findViewById(R.id.listViewGroups);
        Log.v("test", "test");
        adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, groups);
        staffListView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        staffListView.setItemsCanFocus(false);
        swipeContainer.setRefreshing(false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((PeopleActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("search query submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        settings = new OAuthSettings();
        setHasOptionsMenu(true);

        groupsApi = new GroupsApi();
        final JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        final String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainerGroups);
        final ListView staffListView = (ListView)rootView.findViewById(R.id.listViewGroups);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.flbtn_addNewURL);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent  intent = new Intent(getActivity(), GroupActivity.class);
                getActivity().startActivity(intent);

            }
        });

        try {
            //GroupModel.deleteAll();
            groups = GroupModel.getAllGroups();
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
                            groups = getGroupsCallback.groupsList;


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
                            groups = getGroupsCallback.groupsList;


                             adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, GroupFragment.this.groups);

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


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_row_staff_user, null);
            }

            Group p = groups.get(position);
            Log.v("Group", p.toString());


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextViewStaff);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextViewStaff);

                if (tt1 != null && tt2 != null) {
                    tt1.setText(p.getName());
                    tt2.setText(p.getPcnlist().toString());
                }
                else
                {
                    Log.v("Error", "");
                    tt2.setText("");
                }
            }
            return v;
        }

    }



}
