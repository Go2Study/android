package lol.go2study.go2study;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;

/**
 * Created by Todor on 12/1/2015.
 */
public class PeopleActivity extends AppCompatActivity {
    // Need this to link with the Snackbar
    private CoordinatorLayout mCoordinator;
    //Need this to set the title of the app bar
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
   // private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private UsersApi userApi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        //COLLAPSING
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        //TABS
        mDrawerLayout = (DrawerLayout) findViewById(R.id.people_drawer_layout_tabs);
        //mToolbar = (Toolbar) findViewById(R.id.people_app_bar);
        //setSupportActionBar(mToolbar);

        //drawerToggle button top-left
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);
        //Notice how the Tab Layout links with the Pager Adapter
        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        userApi = new UsersApi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public static class MyFragment extends Fragment {
        public static final java.lang.String ARG_PAGE = "arg_page";
        public MyFragment() {

        }

        public static MyFragment newInstance(int pageNumber) {
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber + 1);
            myFragment.setArguments(arguments);
            return myFragment;
        }


        //peopleActivity uses
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle arguments = getArguments();
            int pageNumber = arguments.getInt(ARG_PAGE);
            RecyclerView recyclerView = new RecyclerView(getActivity());
            recyclerView.setAdapter(new StaffFragment.YourRecyclerAdapter(getActivity()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return recyclerView;
        }
    }
*/
    //Adapter used to populate tabs
     class PagerAdapter extends FragmentStatePagerAdapter {


        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }




        @Override
        public Fragment getItem(int position) {
            //PeopleActivity.MyFragment myFragment = PeopleActivity.MyFragment.newInstance(position);
            Fragment frag = null;
            switch ( position) {
                case 0:
                    frag = new StudentsFragment1();
                    break;
                case 1:
                    frag = new StaffFragment();
                    break;
                case 2:
                    frag = new GroupFragment();
                    break;
            }
            return frag;
        }




        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {


            String title = "";
            switch ( position) {
                case 0:
                    title = "STUDENTS";
                    break;
                case 1:
                    title = "STAFF";
                    break;
                case 2:
                    title = "GROUPS";
                    break;
            }
            return title;
        }

    }


}
