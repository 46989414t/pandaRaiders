package com.tallahassee.pandaraiders.Competicion;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.UserProfile;

public class Competicion extends AppCompatActivity {
    public UserProfile userProf;

    public static Bundle bundle;
    public static String email;
    public static String nombre;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competicion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        bundle = getIntent().getExtras();
        email = bundle.getString("usuarioEmail");
        nombre = bundle.getString("usuarioNombre");

        System.out.println("desde Competicion previo usuario " + email + "--" + nombre);

        /*userProf.setEmail(email);
        userProf.setName(nombre);*/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_competicion, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "COMPETIDORES";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {


            System.out.println("desde Competicion en instance usuario "+email+"--"+nombre);

            System.out.println("NUMERO SECCION "+sectionNumber);
            Fragment fragment = null;
            switch(sectionNumber){
                case 1:
                    fragment = new CompeticionFragment1(email, nombre);
                    break;
                case 2:
                    fragment = new CompeticionFragment2();
                    //fragment = new RaggaeMusicFragment();
                    break;
                case 3:
                    fragment = new CompeticionFragment3();
                    //fragment = new RapMusicFragment();
                    break;
            }
            return fragment;
/*
            if(sectionNumber==1){
                System.out.println("SECCION 1 "+sectionNumber);
                CompeticionFragment1 fragment = new CompeticionFragment1();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
                //return cf1;
            }
            else if(sectionNumber==2){
                System.out.println("SECCION 2 "+sectionNumber);
                CompeticionFragment2 fragment = new CompeticionFragment2();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
                //return cf2;
            }
            else if(sectionNumber==3){
                System.out.println("SECCION 3 "+sectionNumber);
                CompeticionFragment3 fragment = new CompeticionFragment3();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
                //return cf3;
            }


            /*PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;*/

 //           return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_competicion, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
