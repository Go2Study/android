package lol.go2study.go2study;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Invoker.ApiException;
import Go2Study.Models.User;
import lol.go2study.go2study.CallBack.GroupsCallbacks;
import lol.go2study.go2study.Models.UserModel;
import lol.go2study.go2study.CallBack.GroupsCallbacks;

/**
 * Created by Todor on 1/9/2016.
 */
public class CreateGroupFragment extends android.support.v4.app.Fragment {


    private GroupsApi groupsApi;
    private List<Person> people;
    private List<User> userList;
    int itemPosition = -1;
    private List<String> pcnList;
    GroupsCallbacks groupsCallbacks;
    GroupsCallbacks.PostGroupCallBack postGroupCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


       final View v = inflater.inflate(R.layout.activity_add_group,container,false);
        setHasOptionsMenu(true);

       final ListView usersListView = (ListView)v.findViewById(R.id.listViewAddGroup);

        groupsApi = new GroupsApi();
        userList = new ArrayList();
        List<UserModel> dbUsers =  new Select().from(UserModel.class)
                .orderBy("firstName ASC").limit(100).execute();


        for (UserModel model:dbUsers) {
            User user = new User();
            user.setFirstName(model.firstName);
            user.setEmail(model.email);
            user.setPcn(model.pcn);
            user.setLastName(model.lastName);
            user.setClassName(model.className);
            userList.add(user);

        }

        groupsCallbacks = new GroupsCallbacks();
        postGroupCallBack = groupsCallbacks.new PostGroupCallBack();
        pcnList = new ArrayList<>();






        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int j = 0; j < parent.getChildCount(); j++)
                    parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                view.setBackgroundColor(Color.RED);
                itemPosition = position;

                //view.setSelected(true);
                //GET user and PCN to list
                User t = (User) parent.getItemAtPosition(position);
                pcnList.add(t.getPcn());

                Toast.makeText(getContext(), t.getPcn(), Toast.LENGTH_SHORT).show();
                drawImages(v, t.getPcn());



            }
        });

        usersListView.setAdapter(new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, userList));
        usersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        return v;
    }

    private void drawImages(View view,String pcn) {

        LinearLayout ll = (LinearLayout)view.findViewById(R.id.linearlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);
        lp.setMargins(-10, 0, -5, 0);

        ImageView  image = new ImageView(getContext());
        image.setPadding(10,10,10,10);
        Drawable myDrawable = getResources().getDrawable(
                    R.drawable.logo);

        image.setImageDrawable(myDrawable);
        image.setScaleType(ImageView.ScaleType.FIT_START);
        ll.addView(image,lp);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_group, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.goceate_group:
                GroupsCallbacks groupsCallbacks = new GroupsCallbacks();
                GroupsCallbacks.PostGroupCallBack postGroupCallBack = groupsCallbacks.new PostGroupCallBack();
                String groupName = getArguments().get("groupName").toString();
                /*f (groupName != null) {
                    if (extras.getString("groupName") != null) {
                        groupName = extras.getString("groupName");
                    }
                }*/

                if(pcnList.size() > 0) {
                    try {
                        groupsApi.groupsPost(postGroupCallBack,groupName,pcnList);
                        Toast.makeText(getContext(),"Post Succssfull", Toast.LENGTH_SHORT).show();
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public  class YourRecyclerAdapter extends ArrayAdapter<User> {
        private List<Person> personArray;
        //List<Bitmap> bitMapList = new ArrayList<>();                     ///////////////NEEDS TO CHANGE GET THE THE BITMAP IMAGES THROUGH THE CONSTRUCTOR !!!!
        private Context context;
        private List<User> userList;
        private MLRoundedImageView roundedImageView;
        List<Bitmap> bitMapList = new ArrayList<>();
        private int selectedItem = -1;


        public YourRecyclerAdapter(Context context, int resource, List<User> userList) {
            super(context, resource,userList );
            this.context = context;
            this.userList = userList;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_row_staff_user, null);

            }

            User p = getItem(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextViewStaff);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextViewStaff);

                if (tt1 != null) {
                    tt1.setText(p.getFirstName() + "," + p.getLastName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getEmail());
                }

            }

            return v;
        }




    }
}
