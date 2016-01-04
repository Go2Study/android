package lol.go2study.go2study;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.People;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Invoker.ApiException;
import Go2Study.Models.Group;
import Go2Study.Models.User;

public class AddGroupActivity extends AppCompatActivity
{
    private GroupsApi groupsApi;
    private List<Person> people;
    private List<User> userList;
   private List<String> pcnList;
    String nameOfGroup;
    String description;

    public Callback postNewGroupCallBack = new Callback() {

        @Override
        public void onFailure(Request request, IOException e) {
            //do something to indicate error
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                //String responseRaw = response.body().string();
                Log.v(" The response  is is:::", "SUCCESSFULLL POST");
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_group, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.new_group));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_group:
               // newGame();



                try {
                    if(pcnList.size() > 0) {
                        groupsApi.groupsPost(postNewGroupCallBack,nameOfGroup, pcnList, description);
                        Toast.makeText(getBaseContext(),pcnList.toString(),Toast.LENGTH_SHORT).show();

                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarNewGroup);
        setSupportActionBar(toolbar);
        groupsApi = new GroupsApi();


        pcnList = new ArrayList<>();

        //ADD string pcn


        ListView usersListView = (ListView)findViewById(R.id.listViewAddGroup);
        TextView groupName = (TextView)findViewById(R.id.EditTextName);
        TextView groupGroupDesc = (TextView)findViewById(R.id.EditDescription);

        description = groupGroupDesc.getText().toString();
        nameOfGroup = groupName.getText().toString();

      //  usersListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, allUsers));
        usersListView.setAdapter(new YourRecyclerAdapter(this, R.layout.custom_row_groupadd, userList));
        usersListView.setItemsCanFocus(false);
        // we want multiple clicks
        usersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(),userList.get(position).getDisplayName(),Toast.LENGTH_SHORT).show();

                for (int j = 0; j < parent.getChildCount(); j++)
                    parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);


                view.setBackgroundColor(Color.LTGRAY);
                view.setSelected(true);
                User t = (User) parent.getItemAtPosition(position);
                pcnList.add(t.getPcn());
                Toast.makeText(getBaseContext(), t.getPcn(), Toast.LENGTH_SHORT).show();
                Log.v("pcn", pcnList.toString());

            }
        });


    }

    public  class YourRecyclerAdapter extends ArrayAdapter<User> {
    private List<Person> personArray;
    //List<Bitmap> bitMapList = new ArrayList<>();                     ///////////////NEEDS TO CHANGE GET THE THE BITMAP IMAGES THROUGH THE CONSTRUCTOR !!!!
    private Context context;
    private List<User> userList;
    private LayoutInflater inflater;
    private MLRoundedImageView roundedImageView;
    List<Bitmap> bitMapList = new ArrayList<>();


    public YourRecyclerAdapter(Context context, int resource, List<User> userList) {
        super(context, resource,userList );
        this.context = context;
        this.userList = userList;
        // this.bitMapList = HomeActivity.staffImages;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_row_groupadd, null);
        }

        User p = getItem(position);


        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.nameTextView);
            TextView tt2 = (TextView) v.findViewById(R.id.roomTextView);
            ImageView image = (ImageView)v.findViewById(R.id.rowImageView);  //for the image

            if (tt1 != null) {
                tt1.setText(p.getDisplayName());
            }

            if (tt2 != null) {
                tt2.setText(p.getEmail());
            }
            // if(image != null) {
            //   Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitMapList.get(position),90);
            // image.setImageBitmap(roundedImage);
            //}


        }

        return v;
    }

    ////////////////////////////////////////////// //CONTAINER AND PLACEHOLDER


}
}