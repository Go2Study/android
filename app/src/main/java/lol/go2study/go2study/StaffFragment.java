package lol.go2study.go2study;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {


    public StaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        //int pageNumber = arguments.getInt(ARG_PAGE);
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setAdapter(new YourRecyclerAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
        //return inflater.inflate(R.layout.fragment_people_, container, false);
    }

    //CONTAINER
    public static class YourRecyclerAdapter extends RecyclerView.Adapter<YourRecyclerAdapter.RecycleViewPeopleActivity> {
        private ArrayList<String> list = new ArrayList<>();
        private List<Person> personArray = new ArrayList<>();
        List<Bitmap> bitMapList = new ArrayList<>();

        private LayoutInflater inflater;

        public YourRecyclerAdapter(Context context)
        {
            //list = people;

            personArray= HomeActivity.people;
            bitMapList = HomeActivity.staffImages;
            inflater = LayoutInflater.from(context);
            //Log.v("FROMHERE",personArray.get(0).getPhoto());


        }



        @Override
        public RecycleViewPeopleActivity onCreateViewHolder(ViewGroup viewGroup, int i) {
            View root = inflater.inflate(R.layout.custom_row_tab, viewGroup, false);
            RecycleViewPeopleActivity holder = new RecycleViewPeopleActivity(root);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleViewPeopleActivity yourRecyclerViewHolder, int i) {
            yourRecyclerViewHolder.nameTextView.setText(personArray.get(i).getGivenName());
            yourRecyclerViewHolder.roomTextView.setText(personArray.get(i).getOffice());
            yourRecyclerViewHolder.imageView.setImageBitmap(bitMapList.get(i));


        }

        @Override
        public int getItemCount() {
            return personArray.size();
        }

        public  class RecycleViewPeopleActivity extends RecyclerView.ViewHolder {

            TextView nameTextView;
            TextView roomTextView;
            ImageView imageView;

            public RecycleViewPeopleActivity(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
                roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
                imageView = (ImageView) itemView.findViewById(R.id.rowImageView);

            }
        }

    }
}


