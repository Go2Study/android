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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import Go2Study.Models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragment extends android.support.v4.app.Fragment {

    ArrayList<String> people;

    public StudentsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setAdapter(new ReCycleViewStudentFragment(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

    }


    //CONTAINER
    class ReCycleViewStudentFragment extends RecyclerView.Adapter<ReCycleViewStudentFragment.RecyclerViewHolderStudents> {

        private List<User> users = new ArrayList<>();
        private MLRoundedImageView roundedImageView;
        List<Bitmap> bitMapList = new ArrayList<>();
        private Context context;
        private LayoutInflater inflater;

        public ReCycleViewStudentFragment(Context context) {

            this.context = context;
            inflater = LayoutInflater.from(context);


        }


        @Override
        public RecyclerViewHolderStudents onCreateViewHolder(ViewGroup viewGroup, int i) {

            View root = inflater.inflate(R.layout.custom_row_staff_user, viewGroup, false);
            RecyclerViewHolderStudents holder = new RecyclerViewHolderStudents(root);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderStudents holder, int i) {
            holder.nameTextView.setText(users.get(i).getFirstName());
            holder.roomTextView.setText(users.get(i).getLastName());

            if (i == 0) {
                holder.divider.setVisibility(View.INVISIBLE);
            }
            holder.nameTextView.setText(users.get(i).getDisplayName());
            holder.roomTextView.setText(users.get(i).getClassName());
            //Convert to Rounded Image
            roundedImageView = new MLRoundedImageView(context);
            //Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitMapList.get(i), 90);
            // Set the rounded image to the imageView
          //  holder.imageView.setImageBitmap(roundedImage);
            holder.chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, ChatActivity.class); //////////////////////////////////
                    //intent.putExtra("displayname", users.get(position).getDisplayName());
                    //intent.putExtra("clasname", users.get(position).getClassName());

                    //startActivity(intent);
                }
            });
            holder.favoritesImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "FOVORITES ICON", Toast.LENGTH_SHORT).show();
                }
            });

            /*
            holder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("displayname", users.get(position).getDisplayName());
                    intent.putExtra("clasname", users.get(position).getClassName());
                    startActivity(intent);

                }
            });
            */
        }


        @Override
        public int getItemCount() {
            return users.size();
        }

        public class RecyclerViewHolderStudents extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView nameTextView;
            TextView roomTextView;
            ImageView imageView;
            View divider;
            ImageView favoritesImage;
            ImageView chat;

            private ItemClickListener clickListener;

            public RecyclerViewHolderStudents(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
                roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
                imageView = (ImageView) itemView.findViewById(R.id.rowImageView);
                favoritesImage = (ImageView) itemView.findViewById(R.id.favoritesImageView);
                chat = (ImageView) itemView.findViewById(R.id.chatImageView);
                divider = (View) itemView.findViewById(R.id.dividerView);
                itemView.setOnClickListener(this);

            }

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }


            @Override
            public void onClick(View v) {
                clickListener.onClick(v, getAdapterPosition(), false);
                Log.v("position: " + getAdapterPosition() + "", "");
            }

        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }
}


