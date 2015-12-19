package lol.go2study.go2study;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Group;
import Go2Study.Models.User;

import static lol.go2study.go2study.HomeActivity.userList;
import static lol.go2study.go2study.R.id.listViewAddGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends android.support.v4.app.Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        FloatingActionButton button = (FloatingActionButton)rootView.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(GroupFragment.this, AddGroupActivity.class);
                Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}
