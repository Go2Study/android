package lol.go2study.go2study;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Models.User;
import lol.go2study.go2study.CallBack.GroupsCallbacks;

public class GroupActivity extends AppCompatActivity
{
    private GroupsApi groupsApi;
    private List<Person> people;
    private List<User> userList;
   private List<String> pcnList;
    String nameOfGroup;
    String description;
    private ViewPager mPager;
    //private PagerAdapter mAdapter;

    GroupsCallbacks groupsCallbacks;
    GroupsCallbacks.PostGroupCallBack postGroupCallBack;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new FragmentGroupNameDescription(), FragmentGroupNameDescription.class.getName());
        // Commit the transaction
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

    }
}