package com.example.myitunesapi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myitunesapi.Database.DatabaseHelper;
import com.example.myitunesapi.Model.Movie;
import com.example.myitunesapi.Model.MovieSQLite;
import com.example.myitunesapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavListAdapter extends BaseAdapter {

    private Context context;
    private List<MovieSQLite> movieList;
    private DatabaseHelper databaseHelper;

    public FavListAdapter(Context context, List<MovieSQLite> movieList) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
       databaseHelper = new DatabaseHelper(context);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.movielist_item, null);
            viewHolder = new ViewHolder();

            viewHolder.image = view.findViewById(R.id.image);
            viewHolder.trackName = view.findViewById(R.id.trackName);
            viewHolder.collectionPrice = view.findViewById(R.id.collectionPrice);
            viewHolder.primaryGenreName = view.findViewById(R.id.primaryGenreName);
            viewHolder.btn_fav = view.findViewById(R.id.btn_fav);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final MovieSQLite movie = (MovieSQLite) getItem(i);
        Picasso
                .with(context)
                .load(movie.getImage())
                .into(viewHolder.image);
        viewHolder.trackName.setText(movie.getTrackName());
        viewHolder.collectionPrice.setText("$" + movie.getCollectionPrice());
        viewHolder.primaryGenreName.setText(movie.getPrimaryGenreName());


        viewHolder.btn_fav.setVisibility(View.VISIBLE);

        viewHolder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedFav = databaseHelper.removeFav(movie.getTrackName());
                if (deletedFav > 0){
                    movieList.remove(i);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Failed to delete this item.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private class ViewHolder {
        TextView trackName;
        ImageView image;
        TextView collectionPrice;
        TextView primaryGenreName;
        ImageView btn_fav;
    }
}
