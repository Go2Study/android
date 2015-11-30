package lol.go2study.go2study;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by abdulfatax on 11/22/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Staff();
                break;
            case 1:
                frag=new Groups();
                break;
            case 2:
                frag=new Students();
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
        String title=" ";
        switch (position){
            case 0:
                title="Staff";
                break;
            case 1:
                title="Groups";
                break;
            case 2:
                title="Students";
                break;
        }

        return title;
    }
}
