package com.example.myitunesapi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myitunesapi.Model.Movie;
import com.example.myitunesapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movieList;

    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.movielist_item, null);
            viewHolder = new ViewHolder();

            viewHolder.image = view.findViewById(R.id.image);
            viewHolder.trackName = view.findViewById(R.id.trackName);
            viewHolder.collectionPrice = view.findViewById(R.id.collectionPrice);
            viewHolder.primaryGenreName = view.findViewById(R.id.primaryGenreName);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Movie movie = (Movie) getItem(i);
        Picasso
                .with(context)
                .load(movie.getImage())
                .into(viewHolder.image);
        viewHolder.trackName.setText(movie.getTrackName());
        viewHolder.collectionPrice.setText("$" + movie.getCollectionPrice());
        viewHolder.primaryGenreName.setText(movie.getPrimaryGenreName());


        return view;
    }

    private class ViewHolder {
        TextView trackName;
        ImageView image;
        TextView collectionPrice;
        TextView primaryGenreName;
    }
}
