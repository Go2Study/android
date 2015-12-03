package lol.go2study.go2study;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Models.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class Users_Fragment extends android.support.v4.app.Fragment  {

    ArrayList<String> people;

    public Users_Fragment() {
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
        recyclerView.setAdapter(new YourRecyclerAdapter2(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

      // return inflater.inflate(R.layout.fragment_users_, container, false);
    }




}

//CONTAINER
class YourRecyclerAdapter2 extends RecyclerView.Adapter<YourRecyclerAdapter2.YourRecyclerViewHolder2> {
    private ArrayList<String> list = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    List<Bitmap> bitMapList = new ArrayList<>();

    private LayoutInflater inflater;

    public YourRecyclerAdapter2(Context context) {
        //list = people;

        users = HomeActivity.userList;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public YourRecyclerAdapter2.YourRecyclerViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View root = inflater.inflate(R.layout.custom_row_tab, viewGroup, false);
        YourRecyclerViewHolder2 holder;
        holder = new YourRecyclerAdapter2.YourRecyclerViewHolder2(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(YourRecyclerViewHolder2 holder, int i) {
        holder.nameTextView.setText(users.get(i).getFirstName());
        holder.roomTextView.setText(users.get(i).getLastName());
        //yourRecyclerViewHolder.imageView.setImageBitmap(bitMapList.get(i));

    }


    @Override
    public int getItemCount() {
        return users.size();
    }
    public  class YourRecyclerViewHolder2 extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView roomTextView;
        ImageView imageView;

        public YourRecyclerViewHolder2(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
            imageView = (ImageView) itemView.findViewById(R.id.rowImageView);

        }
    }
}

