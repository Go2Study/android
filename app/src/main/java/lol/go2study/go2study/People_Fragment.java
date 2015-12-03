package lol.go2study.go2study;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class People_Fragment extends android.support.v4.app.Fragment {


    public People_Fragment() {
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
        recyclerView.setAdapter(new PeopleActivity.YourRecyclerAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
        //return inflater.inflate(R.layout.fragment_people_, container, false);
    }


}
