package lol.go2study.go2study;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import mehdi.sakout.dynamicbox.DynamicBox;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {

    protected List<Person> people;
    protected   List<Bitmap> bitMapList;
    private SwipeRefreshLayout swipeContainer;
    SharedPreferences pref;
    private OAuthSettings settings;
    //STAFF
    private PeopleApi peopleApi;
    public static List<Bitmap> staffImages;
    private ViewGroup container;
    private DynamicBox dynamicBox;


    public StaffFragment() {
        // Required empty public constructor
    }


    // CALLBACKS
    public Callback getPeopleStaff = new Callback() {

        @Override
        public void onFailure(Request request, IOException e) {
            //do something to indicate error
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();
                    Person p;
                    //List<Person> people = new ArrayList<>();
                    people = (List<Person>) ApiInvoker.deserialize(responseRaw, "list", Person.class);

                    Collections.sort(people, new Comparator<Person>() {
                        @Override
                        public int compare(Person lhs, Person rhs) {
                            return lhs.getDisplayName().compareToIgnoreCase(rhs.getDisplayName());
                        }

                    });

                    bitMapList =  BitMapImages(people);


                    // Update UI
                    View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_staff, container, false);
                    // Initialize DynamicBox for updating the UI
                    dynamicBox = new DynamicBox(getActivity(), getView());
                    dynamicBox.showLoadingLayout();
                    dynamicBox.addCustomView(rootView, "RootView");

                    swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);
                    // Setup refresh listener which triggers new data loading

                    swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {

                            // Your code to refresh the list here.

                            // Make sure you call swipeContainer.setRefreshing(false)

                            // once the network request has completed successfully.
                            //https://guides.codepath.com/android/Implementing-Pull-to-Refresh-Guide
                            // fetchTimelineAsync(0);
                        }
                    });


                    ListView staffListView = (ListView) rootView.findViewById(R.id.listViewStaff);
                    staffListView.setAdapter(new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, people));
                    staffListView.setItemsCanFocus(false);
                    staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
                            intent.putExtra("displayname", people.get(position).getDisplayName());
                            intent.putExtra("mobilenumber", people.get(position).getMobileNumber());
                            intent.putExtra("office", people.get(position).getOffice());
                            intent.putExtra("mail", people.get(position).getMail());
                            intent.putExtra("initial", people.get(position).getInitials());
                            intent.putExtra("photo", bitMapList.get(position));
                            intent.putExtra("personalTitle", people.get(position).getPersonalTitle());
                            startActivity(intent);
                        }
                    });
                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                }
                dynamicBox.showCustomView("RootView");
            }
        }
    };

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
        Log.v("List of IMG Length",String.valueOf(listOfImages.size()));
        List<Bitmap> temp = new ArrayList<>();
        for (byte[] b:listOfImages) {
            Bitmap bitmap   = BitmapFactory.decodeByteArray(b, 0, b.length);
            temp.add(bitmap);
        }
        return temp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        settings = new OAuthSettings();
        //Access token
        pref =  this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.fragment_staff, container, false);

       /* // Initialize DynamicBox for updating the UI
        dynamicBox = new DynamicBox(this.getActivity(), rootView.findViewById(R.id.listViewStaff));
        dynamicBox.showLoadingLayout();*/

        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        peopleApi = new PeopleApi();

        try {
            peopleApi.peopleList(accessToken,getPeopleStaff,true);
        } catch (ApiException e) {
            e.printStackTrace();
        }


/*
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Your code to refresh the list here.

                // Make sure you call swipeContainer.setRefreshing(false)

                // once the network request has completed successfully.
                //https://guides.codepath.com/android/Implementing-Pull-to-Refresh-Guide
               // fetchTimelineAsync(0);
            }
        });

        ListView staffListView = (ListView) rootView.findViewById(R.id.listViewStaff);
        staffListView.setAdapter(new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, people));
        staffListView.setItemsCanFocus(false);
        staffListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity() ,ProfileActivity.class);
                intent.putExtra("displayname",people.get(position).getDisplayName());
                intent.putExtra("mobilenumber",people.get(position).getMobileNumber());
                intent.putExtra("office",people.get(position).getOffice());
                intent.putExtra("mail",people.get(position).getMail());
                intent.putExtra("initial",people.get(position).getInitials());
                intent.putExtra("photo",bitMapList.get(position));
                intent.putExtra("personalTitle",people.get(position).getPersonalTitle());
                startActivity(intent);
            }
        });*/



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
                    Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitMapList.get(position),90);
                    image.setImageBitmap(roundedImage);
                }
            }
            return v;
        }

    }
}


