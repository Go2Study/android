package lol.go2study.go2study;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.Person;
import lol.go2study.go2study.CallBack.PeopleCallback;
import lol.go2study.go2study.Models.PersonImage;
import lol.go2study.go2study.Models.PersonModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {

    protected List<Person> people;
    protected List<Bitmap> images;
    private SwipeRefreshLayout swipeContainer;
    private PeopleApi peopleApi;
    private SharedPreferences pref;
    private YourRecyclerAdapter adapter;
    private  ListView staffListView;
    private OAuthSettings settings;
    private View rootView;
    private SearchView searchView;
    List<PersonImage> newList;

    public StaffFragment() {
        // Required empty public constructor
    }


    private List<Bitmap> BitMapImages(List<Person> personList) {
        List<byte[]> listOfImages = new ArrayList<>();

        for (Person p :personList) {
            byte[] data = null;
            try {
                if(p.getThumbnailData() != "" && p.getThumbnailData() != null && !p.getThumbnailData().equals("") ) {
                    data = p.getThumbnailData().getBytes("UTF-8");

                    byte[] byteImage = Base64.decode(data, Base64.DEFAULT);
                    listOfImages.add(byteImage);
                }
            } catch (UnsupportedEncodingException e) {
                Log.v("data",data.toString());
                e.printStackTrace();
            }
        }
        List<Bitmap> temp = new ArrayList<>();
        for (byte[] b:listOfImages) {
            Bitmap bitmap   = BitmapFactory.decodeByteArray(b, 0, b.length);
            temp.add(bitmap);
        }
        return  temp;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView  staffListView = (ListView) view.findViewById(R.id.listViewStaff);

        images =  BitMapImages(people);

        newList = new ArrayList<>();
        for(int i = 0; i < people.size();i++)
        {
            PersonImage temp = new PersonImage(people.get(i),images.get(i),people.get(i).getDisplayName());

            newList.add(temp);
        }




        adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, newList);
        staffListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        staffListView.setItemsCanFocus(false);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_search,menu);
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


        rootView = inflater.inflate(R.layout.fragment_staff, container, false);
        peopleApi = new PeopleApi();
        settings = new OAuthSettings();
        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        final JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        final String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);
        setHasOptionsMenu(true);
        staffListView = (ListView)rootView.findViewById(R.id.listViewStaff);


        //PersonModel.deleteAll();
        try {

            people = PersonModel.getAllPeople();
            if(people == null || people.size() == 0)
            {
                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {
                            PeopleCallback peopleCallback = new PeopleCallback();
                            peopleApi.peopleList(accessToken, peopleCallback, true);

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (peopleCallback.people == null || peopleCallback.people.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {
                                     //swipeContainer.setRefreshing(false);
                                }
                            }
                            people = peopleCallback.people;


                        } catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        } catch (UnsupportedEncodingException | DecoderException e) {
            e.printStackTrace();
        }

        staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("displayname", StaffFragment.this.people.get(position).getDisplayName());
                intent.putExtra("mobilenumber", StaffFragment.this.people.get(position).getMobileNumber());
                intent.putExtra("office", StaffFragment.this.people.get(position).getOffice());
                intent.putExtra("mail", StaffFragment.this.people.get(position).getMail());
                intent.putExtra("initial", StaffFragment.this.people.get(position).getInitials());
                intent.putExtra("photo", images.get(position));
                intent.putExtra("personalTitle", StaffFragment.this.people.get(position).getPersonalTitle());
                startActivity(intent);
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                swipeContainer.setRefreshing(true);

                    if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                        if (WelcomeActivity.isLoggedIn(accessJSON)) {
                            try {
                                PeopleCallback peopleCallback = new PeopleCallback();
                                peopleApi.peopleList(accessToken,peopleCallback,true);

                                // Wait for getting the people from Fontys API
                                long timestampNow = System.currentTimeMillis();
                                while (peopleCallback.people == null || peopleCallback.people.isEmpty()){
                                    if (System.currentTimeMillis() - timestampNow >= 6000l){
                                       // swipeContainer.setRefreshing(false);
                                    }
                                }

                                people = peopleCallback.people;


                                adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, newList);

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        staffListView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        staffListView.setItemsCanFocus(false);

                                    }
                                });
                                swipeContainer.setRefreshing(false);

                                staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                        intent.putExtra("displayname", StaffFragment.this.people.get(position).getDisplayName());
                                        intent.putExtra("mobilenumber", StaffFragment.this.people.get(position).getMobileNumber());
                                        intent.putExtra("office", StaffFragment.this.people.get(position).getOffice());
                                        intent.putExtra("mail", StaffFragment.this.people.get(position).getMail());
                                        intent.putExtra("initial", StaffFragment.this.people.get(position).getInitials());
                                        intent.putExtra("photo", images.get(position));
                                        intent.putExtra("personalTitle", StaffFragment.this.people.get(position).getPersonalTitle());
                                        startActivity(intent);
                                    }
                                });

                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        });
        return rootView;
    }

    public  class YourRecyclerAdapter extends ArrayAdapter<PersonImage> {

        private Context context;
        private List<PersonImage> people;
        private MLRoundedImageView roundedImageView;



        public YourRecyclerAdapter(Context context, int resource, List<PersonImage> people) {
            super(context, resource,people );
            this.context = context;
            this.people = people;
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

            PersonImage p = people.get(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextViewStaff);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextViewStaff);
                ImageView image = (ImageView)v.findViewById(R.id.rowImageViewStaffUser);

                if (tt1 != null) {
                    tt1.setText(p.getPerson().getDisplayName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getPerson().getOffice().toString());
                }
                if(image != null)
                {
                    Bitmap roundedImage = roundedImageView.getCroppedBitmap(p.getImage(),90);
                    image.setImageBitmap(roundedImage);
                }
            }
            return v;
        }

    }
}


