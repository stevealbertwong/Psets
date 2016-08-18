package com.udacity.firebase.shoppinglistplusplus.ui;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.activeLists.AddListDialogFragment;
import com.udacity.firebase.shoppinglistplusplus.ui.activeLists.ShoppingListsFragment;
import com.udacity.firebase.shoppinglistplusplus.ui.meals.AddMealDialogFragment;
import com.udacity.firebase.shoppinglistplusplus.ui.meals.MealsFragment;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Represents the home screen of the app which
 * has a {@link ViewPager} with {@link ShoppingListsFragment} and {@link MealsFragment}
 */
public class MainActivity extends BaseActivity {
    private Firebase mUserRef;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ValueEventListener mUserRefListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /**
         * Create Firebase references
         */
        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);

        /**
         * Link layout elements from XML and setup the toolbar
         */
        initializeScreen();

        /**
         * Add ValueEventListeners to Firebase references
         * to control get data and control behavior and visibility of elements
         */
        mUserRefListener = mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                /**
                 * Set the activity title to current user name if user is not null
                 */
                if (user != null) {
                    /* Assumes that the first word in the user's name is the user's first name. */
                    String firstName = user.getName().split("\\s+")[0];
                    String title = firstName + "'s Lists";
                    setTitle(title);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG,
                        getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });

    }


    /**
     * Override onOptionsItemSelected to use main_menu instead of BaseActivity menu
     *
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Override onOptionsItemSelected to add action_settings only to the MainActivity
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /**
         * Open SettingsActivity with sort options when Sort icon was clicked
         */
        if (id == R.id.action_sort) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserRef.removeEventListener(mUserRefListener);
    }







    /**
     * Link layout elements from XML and setup the toolbar
     */
    public void initializeScreen() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // spiritual successor of the ActionBar. ToolBar's appearance and behavior can be more easily customized than the ActionBar.
        // existing ActionBar facilities (such as menu inflation and selection, ActionBarDrawerToggle, and so on) but want to have more control over its appearance
        //  showing multiple toolbars on the screen, spanning only part of the width
        // Unlike Action BAr, Toolbar is just a ViewGroup and can be styled and positioned like any other view
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        /**
         * Create SectionPagerAdapter, set it as adapter to viewPager with setOffscreenPageLimit(2)
         *
         * FragmentManager fm = getSupportFragmentManager()
         **/
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        /**
         * Setup the mTabLayout with view pager
         */
        tabLayout.setupWithViewPager(viewPager);
    }






    /**
     * Fragment_shopping_lists.xml -> android.support.design.widget.FloatingActionButton-> android:onClick="showAddListDialog"
     *
     * Create an instance of the AddList dialog fragment and show it
     *
     */
    // ???????????????????????????????????????????????????? WHERE DOES mEncodedEmail come from
    public void showAddListDialog(View view) {
        DialogFragment dialog = AddListDialogFragment.newInstance(mEncodedEmail);
        // show addListDialogFragment.setArguments(bundle), bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        dialog.show(MainActivity.this.getFragmentManager(), "AddListDialogFragment");
    }






    /**
     * Create an instance of the AddMeal dialog fragment and show it
     */
    public void showAddMealDialog(View view) {
        /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = AddMealDialogFragment.newInstance();
        dialog.show(MainActivity.this.getFragmentManager(), "AddMealDialogFragment");
    }









    /**
     * SectionPagerAdapter class that extends FragmentStatePagerAdapter to save fragments state
     */
    public class SectionPagerAdapter extends FragmentStatePagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Use positions (0 and 1) to find and instantiate fragments with newInstance()
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            /**
             * Set fragment to different fragments depending on position in ViewPager
             */
            switch (position) {
                case 0:
                    fragment = ShoppingListsFragment.newInstance(mEncodedEmail);
                    break;
                case 1:
                    fragment = MealsFragment.newInstance();
                    break;
                /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                case 2:
                fragment = ProfileFragment.newInstance(); // could do profile edit

                case 3:
                fragment = PictureFragment.newInstance(); // family share instagram

                case 4:
                fragment = ContactFragment.newInstance();

                 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

                default:
                    fragment = ShoppingListsFragment.newInstance(mEncodedEmail);
                    break;
            }

            return fragment;
        }



        @Override
        public int getCount() {
            return 2; // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ return 5;
        }










        /**
         * Set string resources as titles for each fragment by it's position
         *
         * @param position
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.pager_title_shopping_lists);
                case 1:

                    /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                case 2:
                return getString(R.string.pager_title_shopping_lists);

                case 3:
                return getString(R.string.pager_title_shopping_lists);

                case 4:
                return getString(R.string.pager_title_shopping_lists);

                 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
                default:
                    return getString(R.string.pager_title_meals);
            }
        }
    }
}
