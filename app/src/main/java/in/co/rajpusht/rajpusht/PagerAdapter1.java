package in.co.rajpusht.rajpusht;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Narendra on 5/24/2017.
 */

public class PagerAdapter1 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter1(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PreliminaryPW1Fragment tab1 = new PreliminaryPW1Fragment();
                return tab1;
            case 1:
                DiataryPW1Fragment tab2 = new DiataryPW1Fragment();
                return tab2;
            case 2:
                HeightWeightFragment tab3 = new HeightWeightFragment();
                return tab3;
//            case 3:
//                TabFragment1 tab4 = new TabFragment1();
//                return tab4;
//            case 4:
//                TabFragment1 tab5 = new TabFragment1();
//                return tab5;
//            case 5:
//                TabFragment1 tab6 = new TabFragment1();
//                return tab6;
//            case 6:
//                TabFragment1 tab7 = new TabFragment1();
//                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}