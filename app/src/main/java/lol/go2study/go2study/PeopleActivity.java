package lol.go2study.go2study;

import android.app.SearchManager;
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
import Go2Study.Api.UsersApi;

/**
 * Created by Todor on 12/1/2015.
 */

public class  PeopleActivity extends AppCompatActivity {
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
    private int[] icons = {R.drawable.people, R.drawable.calendar, R.drawable.g2slogo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        //COLLAPSING
       // mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        //TABS
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPeople);
        setSupportActionBar(toolbar);


        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);

        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        mTabLayout.setupWithViewPager(mPager);


        for (int i = 0;i< mTabLayout.getTabCount();i++)
        {
            switch (i)
            {
                case 0:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.message);
                    break;
                case 1:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.logo);
                    break;
                case 2:
                    mTabLayout.getTabAt(i).setIcon(R.drawable.settings1);
                    break;
            }
        }
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        userApi = new UsersApi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
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
                    frag = new StudentsFragment();

                    break;
                case 1:
                    frag = new StaffFragment();
                    break;
                /*case 2:
                    frag = new GroupFragment();

                    break;*/
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";

        }

    }

}
