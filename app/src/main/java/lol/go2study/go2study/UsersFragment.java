package lol.go2study.go2study;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Go2Study.Api.UsersApi;
import Go2Study.Models.User;
import lol.go2study.go2study.CallBack.UsersCallbacks;
import lol.go2study.go2study.Models.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends android.support.v4.app.Fragment {

    List<User> users;
    SharedPreferences pref;
    private OAuthSettings settings;
    private SwipeRefreshLayout swipeContainer;
    private UsersApi usersApi;
    protected List<Bitmap> images;
    private YourRecyclerAdapter adapter;

    public UsersFragment() {
        // Required empty public constructor

    }



    private List<Bitmap> BitMapImages(List<User> userList)
    {
        List<byte[]> listOfImages = new ArrayList<>();

        for (User u :userList) {
            byte[] data = null;
            try {
                if(u.getPhoto() != "" && u.getPhoto() != null && !u.getPhoto().equals("") ) {
                  // String[] safe = u.getPhoto().split("=");
                   // data = u.getPhoto().getBytes("UTF-8");
                    byte[] byteImage = base64Decode( u.getPhoto());
                    listOfImages.add(byteImage);
                }
                else
                {
                    Drawable d = getResources().getDrawable(R.drawable.profile);
                    Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] bitmapData = stream.toByteArray();
                    listOfImages.add(bitmapData);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        List<Bitmap> temp = new ArrayList<>();
        Log.v("List of IMG Length", String.valueOf(listOfImages.size()));
        for (byte[] b:listOfImages) {
            Bitmap bitmap   = BitmapFactory.decodeByteArray(b, 0, b.length);
            temp.add(bitmap);
        }
        return  temp;
    }

    private static byte[] base64Decode(String input)    {
        if (input.length() % 4 != 0)    {
            throw new IllegalArgumentException("Invalid base64 input");
        }
        byte decoded[] = new byte[((input.length() * 3) / 4) - (input.indexOf('=') > 0 ? (input.length() - input.indexOf('=')) : 0)];
        char[] inChars = input.toCharArray();
        int j = 0;
        int b[] = new int[4];
        for (int i = 0; i < inChars.length; i += 4)     {
            // This could be made faster (but more complicated) by precomputing these index locations
            b[0] = input.indexOf(inChars[i]);
            b[1] = input.indexOf(inChars[i + 1]);
            b[2] = input.indexOf(inChars[i + 2]);
            b[3] = input.indexOf(inChars[i + 3]);
            decoded[j++] = (byte) ((b[0] << 2) | (b[1] >> 4));
            if (b[2] < 64)      {
                decoded[j++] = (byte) ((b[1] << 4) | (b[2] >> 2));
                if (b[3] < 64)  {
                    decoded[j++] = (byte) ((b[2] << 6) | b[3]);
                }
            }
        }

        return decoded;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView  staffListView = (ListView) view.findViewById(R.id.listViewUsers);
        images =  BitMapImages(users);
        Log.v("phototos", images.toString());
        adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, users);
        staffListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        staffListView.setItemsCanFocus(false);
        swipeContainer.setRefreshing(false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((PeopleActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("search query submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users,container,false);


        pref = this.getActivity().getSharedPreferences("TokenPref", Context.MODE_PRIVATE);
        settings = new OAuthSettings();
        usersApi = new UsersApi();
        setHasOptionsMenu(true);

        final JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        final String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        swipeContainer = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainerUsers);
        final ListView staffListView = (ListView)rootView.findViewById(R.id.listViewUsers);

        try {

            //UserModel.deleteAll();
            users = UserModel.getAllUsers();
            if(users == null || users.size() == 0)
            {
                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {
                            UsersCallbacks usersCallbacks = new UsersCallbacks();
                            UsersCallbacks.GetUsersCallBack getUsersCallBack = usersCallbacks.new GetUsersCallBack();
                            usersApi.usersGet(getUsersCallBack, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (usersCallbacks.userList == null || usersCallbacks.userList.isEmpty()) {
                                if (System.currentTimeMillis() - timestampNow >= 6000l) {
                                    // swipeContainer.setRefreshing(false);
                                }
                            }
                            users = usersCallbacks.userList;


                        }  catch (Go2Study.Invoker.ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
                if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
                    if (WelcomeActivity.isLoggedIn(accessJSON)) {
                        try {

                            UsersCallbacks usersCallbacks = new UsersCallbacks();
                            UsersCallbacks.GetUsersCallBack getUsersCallBack = usersCallbacks.new GetUsersCallBack();
                            usersApi.usersGet(getUsersCallBack, "");

                            // Wait for getting the people from Fontys API
                            long timestampNow = System.currentTimeMillis();
                            while (usersCallbacks.userList == null || usersCallbacks.userList.isEmpty()){
                                if (System.currentTimeMillis() - timestampNow >= 6000l){
                                    // swipeContainer.setRefreshing(false);
                                }
                            }

                           users = usersCallbacks.userList;

                             adapter = new YourRecyclerAdapter(getContext(), R.layout.custom_row_staff_user, UsersFragment.this.users);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    staffListView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    staffListView.setItemsCanFocus(false);

                                }
                            });

                            swipeContainer.setRefreshing(false);

                        }catch (Go2Study.Invoker.ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        return rootView;

    }



    public  class YourRecyclerAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> users;
        private MLRoundedImageView roundedImageView;



        public YourRecyclerAdapter(Context context, int resource, List<User> users) {
            super(context, resource,users );
            this.context = context;
            this.users = users;
            this.roundedImageView =  new MLRoundedImageView(context);


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_row_staff_user, null);
            }

            User p = users.get(position);


            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.nameTextViewStaff);
                TextView tt2 = (TextView) v.findViewById(R.id.roomTextViewStaff);
                ImageView image = (ImageView)v.findViewById(R.id.rowImageViewStaffUser);  //for the image

                if (tt1 != null) {
                    tt1.setText(p.getFirstName() + "," + p.getLastName());
                }

                if (tt2 != null) {
                    tt2.setText(p.getEmail());
                }

                if(image != null)
                {
                    Bitmap bitmap = images.get(position);
                    if(  bitmap != null) {
                         Bitmap roundedImage = roundedImageView.getCroppedBitmap(images.get(position), 90);
                        image.setImageBitmap(roundedImage);
                    }
                }

            }

            return v;
        }
    }
}


