package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAURAV on 05-12-2015.
 */
public class ForecastFragment extends Fragment {

    public static final String msg = "Fragment Android: ";
    //public static final String EXTRA_MSG = "com.example.android.sunshine.app";

    ArrayAdapter<String> mForecastAdapter;
    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    public void updateWeather(){
        //FetchWeatherTask task1 = new FetchWeatherTask();
        FetchWeatherTask task1 = new FetchWeatherTask(getActivity(),mForecastAdapter);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = sharedPref.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        Log.v(getActivity().getPackageName(), "String location: "+location);
        task1.execute(location);
    }

    @Override
    public void onStart(){
        super.onStart();
        updateWeather();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                /*FetchWeatherTask task1 = new FetchWeatherTask();
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String location = sharedPref.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
                Log.v(getActivity().getPackageName(), "String location: "+location);
                task1.execute(location);*/
                updateWeather();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //setHasOptionsMenu(true);
        List<String> weekForecast = new ArrayList<>(Arrays.asList(""));

        mForecastAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                weekForecast
        );

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String forecast = mForecastAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, forecast);  // Notice the use of Intent.EXTRA_TEXT
                startActivity(intent);
            }
        });

        return rootView;
    }// Putting in the HTTP request code snippet in here


}