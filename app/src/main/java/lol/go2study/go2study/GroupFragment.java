package lol.go2study.go2study;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.GroupsApi;
import FontysICT.Api.PeopleApi;
import Go2Study.Api.UserGroupsApi;
import Go2Study.Invoker.ApiException;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends android.support.v4.app.Fragment {


    public Go2Study.Api.GroupsApi userGroupsApi;

    public GroupFragment() {
        // Required empty public constructor

    }

    public Callback createGroup = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.v("ERROR","NO NO GROUP CREATED");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if(response.isSuccessful())
            {
                Log.v("SUCCESS","GROUP CREATED");
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("TETE","TETE");
        userGroupsApi = new Go2Study.Api.GroupsApi();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        FloatingActionButton button = (FloatingActionButton)rootView.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FAKA FAKA",
                        Toast.LENGTH_SHORT).show();
                List<Integer> pcsArray = new ArrayList<Integer>();
                pcsArray.add(1);
                pcsArray.add(2);
                pcsArray.add(3);
                pcsArray.add(4);
                Log.v("String", pcsArray.toString());

                try {
                    userGroupsApi.groupsPost(createGroup,"Todors's groups", pcsArray, "HONEY" );
                } catch (ApiException e) {
                    e.printStackTrace();
                }


            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}
