package lol.go2study.go2study;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
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
        Bundle arguments = getArguments();
        //int pageNumber = arguments.getInt(ARG_PAGE);
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setAdapter(new YourRecyclerAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
        //return inflater.inflate(R.layout.fragment_people_, container, false);
    }

    //CONTAINER
    public  class YourRecyclerAdapter extends RecyclerView.Adapter<YourRecyclerAdapter.RecycleViewPeopleActivity> {
        private ArrayList<String> list = new ArrayList<>();
        private List<Person> personArray = new ArrayList<>();
        List<Bitmap> bitMapList = new ArrayList<>();
        private Context context;
        private LayoutInflater inflater;
        private MLRoundedImageView roundedImageView;

        public YourRecyclerAdapter(Context context)
        {

            this.context = context;

            inflater = LayoutInflater.from(context);
            personArray= HomeActivity.people;
            bitMapList = HomeActivity.staffImages;

        }



        @Override
        public RecycleViewPeopleActivity onCreateViewHolder(ViewGroup viewGroup, int i) {
            View root = inflater.inflate(R.layout.custom_row_tab, viewGroup, false);
            RecycleViewPeopleActivity holder = new RecycleViewPeopleActivity(root);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleViewPeopleActivity yourRecyclerViewHolder, int i) {
            personArray= HomeActivity.people;
            if(i == 0)
            {
                yourRecyclerViewHolder.divider.setVisibility(View.INVISIBLE);
            }
            yourRecyclerViewHolder.nameTextView.setText(personArray.get(i).getGivenName());
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
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return personArray.size()  ;
        }

        public  class RecycleViewPeopleActivity extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            TextView nameTextView;
            TextView roomTextView;
            ImageView imageView;
            View divider;
            ImageView favoritesImage ;

            private ItemClickListener clickListener;

            public RecycleViewPeopleActivity(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
                roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
                imageView = (ImageView) itemView.findViewById(R.id.rowImageView);
                favoritesImage = (ImageView)itemView.findViewById(R.id.favoritesImageView);
                divider = (View) itemView.findViewById(R.id.dividerView);
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


