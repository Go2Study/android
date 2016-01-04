package lol.go2study.go2study;


import android.accounts.AccountManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import Go2Study.Models.Group;
import lol.go2study.go2study.CallBack.PeopleCallback;
import lol.go2study.go2study.Models.PersonModel;
import mehdi.sakout.dynamicbox.DynamicBox;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {

    protected List<Person> people;
    protected   List<Bitmap> images;
    private SwipeRefreshLayout swipeContainer;
    private PeopleApi peopleApi;
    SharedPreferences pref;
    ListView staffListView;
    private OAuthSettings settings;
    private DynamicBox box;

    public StaffFragment() {
        // Required empty public constructor
    }


    private View rootView;






    private List<Bitmap> BitMapImages(List<Person> personList)
    {
        List<byte[]> listOfImages = new ArrayList<>();
        Log.v("People list size", String.valueOf(personList.size()));
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
        Log.v("List of IMG Length", String.valueOf(listOfImages.size()));
        for (byte[] b:listOfImages) {
            Bitmap bitmap   = BitmapFactory.decodeByteArray(b, 0, b.length);
            temp.add(bitmap);
        }
        return  temp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_staff, container, false);
        peopleApi = new PeopleApi();
        settings = new OAuthSettings();
        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);
        staffListView = (ListView)rootView.findViewById(R.id.listViewStaff);

        // Populate the Staff using the cached data in the DB. If it's empty - get new data from Fontys

        people = PersonModel.getAll();

        staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("displayname", people.get(position).getDisplayName());
                intent.putExtra("mobilenumber", people.get(position).getMobileNumber());
                intent.putExtra("office", people.get(position).getOffice());
                intent.putExtra("mail", people.get(position).getMail());
                intent.putExtra("initial", people.get(position).getInitials());
                intent.putExtra("photo", images.get(position));
                intent.putExtra("personalTitle", people.get(position).getPersonalTitle());
                startActivity(intent);
            }
        });

   /*     Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getDisplayName().compareToIgnoreCase(rhs.getDisplayName());
            }

        });*/

        images =  BitMapImages(people);
        final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, people);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                staffListView.setAdapter(adapter);
                adapter.refreshEvents(people);
                staffListView.setItemsCanFocus(false);
            }
        });


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                swipeContainer.setRefreshing(true);
                    JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
                    String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
                    if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                        if (WelcomeActivity.isLoggedIn(accessJSON)) {
                            try {
                                PeopleCallback peopleCallback = new PeopleCallback();
                                peopleApi.peopleList(accessToken,peopleCallback,true);

                                // Wait for getting the people from Fontys API
                                long timestampNow = System.currentTimeMillis();
                                while (peopleCallback.people == null || peopleCallback.people.isEmpty()){
                                    if (System.currentTimeMillis() - timestampNow >= 6000l){
                                        swipeContainer.setRefreshing(false);
                                    }
                                }

                                people = peopleCallback.people;
                                staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                        intent.putExtra("displayname", people.get(position).getDisplayName());
                                        intent.putExtra("mobilenumber", people.get(position).getMobileNumber());
                                        intent.putExtra("office", people.get(position).getOffice());
                                        intent.putExtra("mail", people.get(position).getMail());
                                        intent.putExtra("initial", people.get(position).getInitials());
                                        intent.putExtra("photo", images.get(position));
                                        intent.putExtra("personalTitle", people.get(position).getPersonalTitle());
                                        startActivity(intent);
                                    }
                                });

                                Collections.sort(people, new Comparator<Person>() {
                                    @Override
                                    public int compare(Person lhs, Person rhs) {
                                        return lhs.getDisplayName().compareToIgnoreCase(rhs.getDisplayName());
                                    }

                                });

                                images =  BitMapImages(people);
                                final YourRecyclerAdapter adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, people);

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        staffListView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        staffListView.setItemsCanFocus(false);

                                    }
                                });

                                swipeContainer.setRefreshing(false);

                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        });
        return rootView;
    }

    public  class YourRecyclerAdapter extends ArrayAdapter<Person> {

        private Context context;
        private List<Person> people;
        private MLRoundedImageView roundedImageView;



        public YourRecyclerAdapter(Context context, int resource, List<Person> people) {
            super(context, resource,people );
            this.context = context;
            this.people = people;
            this.roundedImageView =  new MLRoundedImageView(context);


        }

        public void refreshEvents(List<Person> people) {
            this.people.clear();
            this.people.addAll(people);
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

            Person p = getItem(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextView);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextView);
                ImageView image = (ImageView)v.findViewById(R.id.rowImageView);  //for the image

                if (tt1 != null) {
                    tt1.setText(p.getDisplayName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getOffice().toString());
                }
                if(image != null)
                {
                    Bitmap roundedImage = roundedImageView.getCroppedBitmap(images.get(position),90);
                    image.setImageBitmap(roundedImage);
                }
            }
            return v;
        }

    }
}


