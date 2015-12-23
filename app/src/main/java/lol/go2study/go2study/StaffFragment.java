package lol.go2study.go2study;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.People;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import FontysICT.Models.Schedule;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends android.support.v4.app.Fragment {

    protected List<Person> people;
    public StaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        String s = i.getStringExtra("edttext");
      //  Log.v();                                                          //TO DO
       // people = (List<Person>) i.getSerializableExtra("list");

       // Log.v("PEOPLE TRANSFERED", people.toString());
        people = HomeActivity.people;


        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setAdapter(new YourRecyclerAdapter(getActivity(),people));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    ////////////////////////////////////////////////ADAPTE/ used to fill in the CUSTOM_ROW_TAB layout/////////////////////////////
    public  class YourRecyclerAdapter extends RecyclerView.Adapter<YourRecyclerAdapter.RecycleViewHolderPeopleActivity> {
        private List<Person> personArray;
        List<Bitmap> bitMapList = new ArrayList<>();                     ///////////////NEEDS TO CHANGE GET THE THE BITMAP IMAGES THROUGH THE CONSTRUCTOR !!!!
        private Context context;
        private LayoutInflater inflater;
        private MLRoundedImageView roundedImageView;

        public YourRecyclerAdapter(Context context, List<Person> people)
        {

            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.personArray = people;
            this.bitMapList = HomeActivity.staffImages;


        }

        @Override
        public RecycleViewHolderPeopleActivity onCreateViewHolder(ViewGroup viewGroup, int i) {
            View root = inflater.inflate(R.layout.custom_row_tab, viewGroup, false);

            RecycleViewHolderPeopleActivity holder = new RecycleViewHolderPeopleActivity(root);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleViewHolderPeopleActivity yourRecyclerViewHolder, int i) {
            //      //personArray= HomeActivity.people;
            if(i == 0)
            {
                yourRecyclerViewHolder.divider.setVisibility(View.INVISIBLE);

            }
            yourRecyclerViewHolder.chatImage.setVisibility(View.INVISIBLE);
            yourRecyclerViewHolder.nameTextView.setText(personArray.get(i).getDisplayName());
            yourRecyclerViewHolder.roomTextView.setText(personArray.get(i).getOffice());
            //Convert to Rounded Image
            roundedImageView = new MLRoundedImageView(context);
            Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitMapList.get(i),90);
            // Set the rounded image to the imageView
            yourRecyclerViewHolder.imageView.setImageBitmap(roundedImage);
            yourRecyclerViewHolder.favoritesImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "FOVORITES ICON", Toast.LENGTH_SHORT).show();
                }
            });
            yourRecyclerViewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent(getActivity() ,ProfileActivity.class);
                    intent.putExtra("displayname",personArray.get(position).getDisplayName());
                    intent.putExtra("mobilenumber",personArray.get(position).getMobileNumber());
                    intent.putExtra("office",personArray.get(position).getOffice());
                    intent.putExtra("mail",personArray.get(position).getMail());
                    intent.putExtra("initial",personArray.get(position).getInitials());
                    intent.putExtra("photo",bitMapList.get(position));
                    intent.putExtra("personalTitle",personArray.get(position).getPersonalTitle());
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return personArray.size()  ;
        }
////////////////////////////////////////////// //CONTAINER AND PLACEHOLDER

        public  class RecycleViewHolderPeopleActivity extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            TextView nameTextView;
            TextView roomTextView;
            ImageView imageView;
            View divider;
            ImageView favoritesImage ;
            ImageView chatImage;

            private ItemClickListener clickListener;

            public RecycleViewHolderPeopleActivity(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
                roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
                imageView = (ImageView) itemView.findViewById(R.id.rowImageView);
                favoritesImage = (ImageView)itemView.findViewById(R.id.favoritesImageView);
                divider = (View) itemView.findViewById(R.id.dividerView);
                chatImage = (ImageView)itemView.findViewById(R.id.chatImageView);
                itemView.setOnClickListener(this);

            }


            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }


            @Override
            public void onClick(View v) {
                clickListener.onClick(v,getAdapterPosition(),false);
                Log.v("position: " + getAdapterPosition() + "", "");
            }
        }

    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }
}


