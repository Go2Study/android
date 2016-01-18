package lol.go2study.go2study;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import Go2Study.Api.UsersApi;
import Go2Study.Invoker.ApiException;
import lol.go2study.go2study.CallBack.UsersCallbacks;

import static android.animation.ValueAnimator.*;

/**
 * Created by Todor on 1/9/2016.
 */
public class FragmentGroupNameDescription extends android.support.v4.app.Fragment {


    private UsersApi usersApi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name_group,null);
        usersApi = new UsersApi();
        final EditText groupName = (EditText)view.findViewById(R.id.input_name);
        EditText groupDescription = (EditText)view.findViewById(R.id.input_description);
        final CircularProgressButton button = (CircularProgressButton)view.findViewById(R.id.circularButton2);
        setHasOptionsMenu(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case R.id.circularButton2:
                        Log.v("texttt", groupName.getText().toString());
                        if (button.getProgress() == 0) {
                            simulateSuccessProgress(button);

                        }

                        if (button.getProgress() == 100 ) {
                            Intent intent = new Intent(getActivity(), CreateGroupFragment.class);
                            intent.putExtra("groupName", groupName.getText());
                            CreateGroupFragment createGroupFragment = new CreateGroupFragment();
                            createGroupFragment.setArguments(intent.getExtras());
                            Toast.makeText(getContext(), groupName.getText(), Toast.LENGTH_SHORT).show();

                            UsersCallbacks usersCallbacks = new UsersCallbacks();
                            UsersCallbacks.GetUsersCallBack getUsersCallBack = usersCallbacks.new GetUsersCallBack();
                            try {
                                usersApi.usersGet(getUsersCallBack, "");
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (usersCallbacks.userList == null || usersCallbacks.userList.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {

                                }
                            }
                            Log.v("usesrssr", usersCallbacks.userList.toString());
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.frame, createGroupFragment, "fragment_screen");
                            ft.commit();

                        }

                        break;
                }
            }
        });


        return  view;
    }



    private void simulateSuccessProgress(final CircularProgressButton button) {
                 ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
                 widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                Log.v("valuee", value.toString());
            }
        });
                 widthAnimation.start();
             }





}
