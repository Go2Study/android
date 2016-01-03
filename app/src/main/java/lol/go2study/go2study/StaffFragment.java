package lol.go2study.go2study;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Models.Group;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {

    protected List<Person> people;
    protected   List<Bitmap> bitMapList;
    private SwipeRefreshLayout swipeContainer;
    public StaffFragment() {
        // Required empty public constructor
    }

/*
    public void fetchTimelineAsync(int page) {
        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones

                adapter.clear();
                // ...the data has come back, add new items to your adapter...

                adapter.addAll(...);
                // Now we call setRefreshing(false) to signal refresh has finished

                swipeContainer.setRefreshing(false);
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }

*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        people = HomeActivity.people;
        bitMapList = HomeActivity.staffImages;
        View rootView = inflater.inflate(R.layout.fragment_staff, container, false);

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


