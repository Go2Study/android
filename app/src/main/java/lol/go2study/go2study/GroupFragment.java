package lol.go2study.go2study;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import Go2Study.Models.Group;
import Go2Study.Models.User;
import java.util.ArrayList;
import java.util.List;
import Go2Study.Models.Group;
import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Models.User;

import Go2Study.Models.Group;
import Go2Study.Models.User;
import static lol.go2study.go2study.HomeActivity.userList;
import static lol.go2study.go2study.R.id.listViewAddGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends android.support.v4.app.Fragment {

   private List<Group> groups;

    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        groups = HomeActivity.groupsList;

        ListView usersListView = (ListView)rootView.findViewById(R.id.listViewGroup);
        usersListView.setAdapter(new YourRecyclerAdapter(getContext(), R.layout.custom_row_groupadd, groups));
        usersListView.setItemsCanFocus(false);

        FloatingActionButton button = (FloatingActionButton)rootView.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(GroupFragment.this, AddGroupActivity.class);
                Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    ////////////////////////////////////////////////ADAPTE/ used to fill in the CUSTOM_ROW_TAB layout/////////////////////////////
    public  class YourRecyclerAdapter extends ArrayAdapter<Group> {
        private List<Person> personArray;
        //List<Bitmap> bitMapList = new ArrayList<>();                     ///////////////NEEDS TO CHANGE GET THE THE BITMAP IMAGES THROUGH THE CONSTRUCTOR !!!!
        private Context context;
        private List<Group> groups;
        private LayoutInflater inflater;
        private MLRoundedImageView roundedImageView;
        List<Bitmap> bitMapList = new ArrayList<>();


        public YourRecyclerAdapter(Context context, int resource, List<Group> groups) {
            super(context, resource,groups );
            this.context = context;
           this.groups = groups;
            // this.bitMapList = HomeActivity.staffImages;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_row_groupadd, null);
            }

            Group p = getItem(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextView);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextView);
                ImageView image = (ImageView)v.findViewById(R.id.rowImageView);  //for the image

                if (tt1 != null) {
                    tt1.setText(p.getName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getPcnlist().toString());
                }
                // if(image != null) {
                //   Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitMapList.get(position),90);
                // image.setImageBitmap(roundedImage);
                //}


            }

            return v;
        }

        ////////////////////////////////////////////// //CONTAINER AND PLACEHOLDER


    }

}
