package com.example.myitunesapi.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.myitunesapi.Activity.MovieInformation;
import com.example.myitunesapi.Adapter.FavListAdapter;
import com.example.myitunesapi.Database.DatabaseHelper;
import com.example.myitunesapi.Model.Movie;
import com.example.myitunesapi.Model.MovieSQLite;
import com.example.myitunesapi.R;
import com.example.myitunesapi.Utility.LastActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private List<MovieSQLite> movieList;
    private FavListAdapter favoriteAdapter;
    private GridView gridView;
    private LastActivity lastActivity;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        databaseHelper = new DatabaseHelper(getContext());
        lastActivity = new LastActivity();
        gridView = view.findViewById(R.id.gridview);


        lastActivity.getCalendar(getContext(), "FAVORITE");


        //it will load all item in database to gridview using favorite adapter class.
        movieList = databaseHelper.getFavorite();
        favoriteAdapter = new FavListAdapter(getContext(), movieList);
        gridView.setAdapter(favoriteAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieSQLite movie = movieList.get(i);
                Intent intent = new Intent(getActivity(), MovieInformation.class);
                intent.putExtra("TRACKID", movie.getTrackId());
                intent.putExtra("TRACKNAME", movie.getTrackName());
                intent.putExtra("PRICE", movie.getCollectionPrice());
                intent.putExtra("IMAGE", movie.getImage());
                intent.putExtra("GENRE", movie.getPrimaryGenreName());
                intent.putExtra("RELEASEDATE", movie.getReleaseDate());
                intent.putExtra("COUNTRY", movie.getCountry());
                intent.putExtra("DESCRIPTION", movie.getLongDescription());
                startActivity(intent);
            }
        });


        return view;
    }

}