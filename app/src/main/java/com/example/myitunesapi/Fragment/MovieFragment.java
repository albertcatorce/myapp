package com.example.myitunesapi.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.myitunesapi.Activity.MovieInformation;
import com.example.myitunesapi.Adapter.MovieListAdapter;
import com.example.myitunesapi.Model.Movie;
import com.example.myitunesapi.R;
import com.example.myitunesapi.Utility.LastActivity;
import com.example.myitunesapi.Utility.ReadObject;
import com.example.myitunesapi.Utility.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private GridView gridview;
    private ArrayList<Movie> movieArrayList;
    private MovieListAdapter movieListAdapter;
    private JSONObject jsonObject;
    private JSONObject movieObject;
    private JSONArray jsonArray;
    private ReadObject readObject;
    private LastActivity lastActivity;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lastActivity = new LastActivity();

        gridview = view.findViewById(R.id.gridview);
        readObject = new ReadObject();
        movieArrayList = new ArrayList<>();
        lastActivity.getCalendar(getContext(), "MOVIE");


        //this will get the information from the result which place in jsonObject in a single position and send to another application to display
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    jsonArray = jsonObject.getJSONArray("results");

                    movieObject = jsonArray.getJSONObject(i);
                    Intent intent = new Intent(getActivity(), MovieInformation.class);
                    intent.putExtra("TRACKID", movieObject.getString("trackId"));
                    intent.putExtra("TRACKNAME", movieObject.getString("trackName"));
                    intent.putExtra("PRICE", movieObject.getString("collectionPrice"));
                    intent.putExtra("IMAGE", movieObject.getString("artworkUrl100"));
                    intent.putExtra("GENRE", movieObject.getString("primaryGenreName"));
                    intent.putExtra("RELEASEDATE", movieObject.getString("releaseDate"));
                    intent.putExtra("COUNTRY", movieObject.getString("country"));
                    intent.putExtra("DESCRIPTION", movieObject.getString("longDescription"));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //this runnable method will communicate from the given URL, and read all of information and store in JSON object which is a Place Holder.
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJson().execute(Uri.baseURL);
            }
        });

        return view;
    }

    //this method will get the information from result which stored in JSON OBJECT, and place to movieArraylist(),
    // and display the information by using the MovieListAdapter to gridview
    class ReadJson extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return readObject.readUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                jsonObject = new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i <= jsonArray.length(); i++) {
                    movieObject = jsonArray.getJSONObject(i);
                    movieArrayList.add(new Movie(
                            movieObject.getString("trackName"),
                            movieObject.getString("artworkUrl100"),
                            movieObject.getString("collectionPrice"),
                            movieObject.getString("primaryGenreName")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            movieListAdapter = new MovieListAdapter(getContext(), movieArrayList);
            gridview.setAdapter(movieListAdapter);
        }
    }

}
