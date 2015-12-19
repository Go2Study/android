package lol.go2study.go2study;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Models.User;

public class AddGroupActivity extends AppCompatActivity
{
    private GroupsApi groupsApi;

    public Callback postNewGroups = new Callback() {

        @Override
        public void onFailure(Request request, IOException e) {
            //do something to indicate error
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                String responseRaw = response.body().string();
                Log.v(" The response  is is:::", responseRaw.toString());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupsApi = new GroupsApi();

        final List<String> allUsers = new ArrayList<String>();
            for (User user : HomeActivity.userList)
            {
                allUsers.add(user.getFirstName() + " " +user.getLastName()+ ", "  + user.getPcn());
            }

        final EditText name = (EditText)findViewById(R.id.EditTextName) ;
        final EditText description = (EditText)findViewById(R.id.EditDescription) ;
        final Button createGroup = (Button)findViewById(R.id.btnCreateGroup) ;

        ListView users = (ListView)findViewById(R.id.listViewAddGroup);
        final List<String> pcnNumbers = new ArrayList<>();

        users.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, allUsers));
        users.setItemsCanFocus(false);
        // we want multiple clicks
        users.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int j = 0; j < adapterView.getChildCount(); j++) {
                    adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                    //change the background color of the selected element
                    view.setBackgroundColor(Color.CYAN);
                    String t = adapterView.getItemAtPosition(position).toString();

                    CheckedTextView ctv = (CheckedTextView) view;
                    if (ctv.isChecked()) {
                        for (User user : HomeActivity.userList) {
                            if (t.contains(user.getPcn())) {
                                //pcnNumbers.add(user.getPcn().toString());
                                if (!pcnNumbers.contains(user.getPcn())) {
                                    pcnNumbers.add(user.getPcn());
                                }
                            }
                        }
                    } else {
                        for (User user : HomeActivity.userList) {
                            if (t.contains(user.getPcn())) {
                                //pcnNumbers.add(user.getPcn().toString());
                                if (pcnNumbers.contains(user.getPcn())) {
                                    pcnNumbers.remove(user.getPcn());
                                }
                            }
                        }
                    }

                }

            }
        });
        createGroup.setOnClickListener(new View.OnClickListener() {
            List<Integer> pcn = new ArrayList<Integer>();

            @Override
            public void onClick(View v) {
                String groupDetails = name.getText().toString() + " " + description.getText().toString() + " " + pcnNumbers.toString();

                for (String s : pcnNumbers) {
                    char[] cArray = s.toCharArray();
                    char[] array2 = Arrays.copyOfRange(cArray, 1, 7);
                    String b = new String(array2);
                    pcn.add(Integer.parseInt(b));
                }
                try {
                    groupsApi.groupsPost(postNewGroups, name.toString(), pcn, description.toString());
                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(AddGroupActivity.this, groupDetails, Toast.LENGTH_SHORT).show();
                Toast.makeText(AddGroupActivity.this, pcn.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}