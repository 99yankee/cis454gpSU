package com.cis454.gpsu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

public class Activity_CoverPage extends ActionBarActivity implements Fragment_CoverPage.OnButtonSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coverpage);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new Fragment_CoverPage())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cover_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        getFragmentManager().beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(id))
                .commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonItemSelected(int id) {
        Intent intent;

        switch (id) {
            case R.id.mapbutton:
                intent = new Intent(this, Maps.class);
                startActivity(intent);
                break;
            case R.id.busbutton:
                getFragmentManager().beginTransaction()
                        .replace(R.id.busing, new PlaceholderFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.weatherbutton:
                intent = new Intent(this, Activity_Weather.class);
                startActivity(intent);
                break;
            case R.id.settingsbutton:
                intent = new Intent(this, Directions.class);
                startActivity(intent);                /*getFragmentManager().beginTransaction()
                        .replace(R.id.settings, new PlaceholderFragment())
                        .addToBackStack(null)
                        .commit();*/
                break;

            default:
                break;
        }
        /*
        switch (id) {
            case R.id.button1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new PlaceholderFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.button2:
                intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
         */
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount()!=0){
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        private static final String ARG_OPTION = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_OPTION, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_busing, container, false);

           /* int option = getArguments().getInt(ARG_OPTION);

            switch (option) {
                case R.id.busbutton:
                    rootView = inflater.inflate(R.layout.fragment_busing, container, false);
                    break;
                case R.id.settingsbutton:
                    rootView = inflater.inflate(R.layout.fragment_settings, container, false);
                    break;
            }*/
            return rootView;
        }
    }
}
