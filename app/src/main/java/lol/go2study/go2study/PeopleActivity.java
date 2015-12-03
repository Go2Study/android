package lol.go2study.go2study;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.plus.People;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;
import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Models.User;

import static android.graphics.BitmapFactory.*;

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


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle arguments = getArguments();
            int pageNumber = arguments.getInt(ARG_PAGE);
            RecyclerView recyclerView = new RecyclerView(getActivity());
            recyclerView.setAdapter(new YourRecyclerAdapter(getActivity()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return recyclerView;
        }
    }

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
                    frag = new People_Fragment();
                    break;
                case 1:
                    frag = new Users_Fragment();
                    break;
                case 2:
                    frag = new Favorites_Fragment();
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
                    title = "STAFF";
                    break;
                case 1:
                    title = "USERS";
                    break;
                case 2:
                    title = "FAVORITES";
                    break;
            }
            return title;
        }

    }

    //CONTAINER
   public static class YourRecyclerAdapter extends RecyclerView.Adapter<YourRecyclerAdapter.YourRecyclerViewHolder> {
        private ArrayList<String> list = new ArrayList<>();
        private  List<Person> personArray = new ArrayList<>();

        List<Bitmap> bitMapList = new ArrayList<>();

        private LayoutInflater inflater;

        public YourRecyclerAdapter(Context context)
        {
            //list = people;

            personArray= HomeActivity.people;
            inflater = LayoutInflater.from(context);


        }



        @Override
        public YourRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View root = inflater.inflate(R.layout.custom_row_tab, viewGroup, false);
            YourRecyclerViewHolder holder = new YourRecyclerViewHolder(root);
            return holder;
        }

        @Override
        public void onBindViewHolder(YourRecyclerViewHolder yourRecyclerViewHolder, int i) {
            yourRecyclerViewHolder.nameTextView.setText(personArray.get(i).getGivenName());
            yourRecyclerViewHolder.roomTextView.setText(personArray.get(i).getOffice());
            //yourRecyclerViewHolder.imageView.setImageBitmap(bitMapList.get(i));

        }

        @Override
        public int getItemCount() {
            return personArray.size();
        }

     public  class YourRecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView nameTextView;
            TextView roomTextView;
            ImageView imageView;

            public YourRecyclerViewHolder(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
                roomTextView = (TextView) itemView.findViewById(R.id.roomTextView);
                imageView = (ImageView) itemView.findViewById(R.id.rowImageView);

            }
        }

    }
}
