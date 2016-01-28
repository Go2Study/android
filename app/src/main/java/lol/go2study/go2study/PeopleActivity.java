package lol.go2study.go2study;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import Go2Study.Api.UsersApi;

/**
 * Created by Todor on 12/1/2015.
 */

public class  PeopleActivity extends AppCompatActivity {
    private CoordinatorLayout mCoordinator;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private final int nmTabs = 3;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private static int[] icons = {R.drawable.people, R.drawable.calendar, R.drawable.g2slogo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPeople);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);

        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        mTabLayout.setupWithViewPager(mPager);


     /*   for (int i = 0;i< mTabLayout.getTabCount();i++) {
            switch (i) {
                case 0:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.userphoto);
                    break;
                case 1:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.teacher);
                    break;
                case 2:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.group);
                    break;
            }
        }*/
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#330033"));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
          //      tab.getCustomView().setBackgroundColor(Color.parseColor("#330033"));
                //mTabLayout.getTabAt(tab.getPosition()).().setBackgroundColor(Color.parseColor("#000099"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new UsersFragment();
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
            return nmTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                   return  "USERS";
                case 1:
                    return "TEACHERS";

                case 2:
                   return  "GROUPS";

        }
            return  null;

    }

}}










