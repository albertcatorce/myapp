package com.example.myitunesapi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myitunesapi.Database.DatabaseHelper;
import com.example.myitunesapi.R;
import com.squareup.picasso.Picasso;

public class MovieInformation extends AppCompatActivity {
    private ImageView image, image_header;
    private ImageButton btn_addFav;
    private TextView trackName, price, genre, releaseDate, country, longDescription;
    private Toolbar toolbar;
    private DatabaseHelper databaseHelper;
    private static String trackId;
    private String trackname, imageURL, collectionPrice, primaryGenre, releaseDateStr, countryStr, description;

    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        databaseHelper = new DatabaseHelper(this);

        toolbar = findViewById(R.id.toolbar);
        price = findViewById(R.id.txt_price);
        genre = findViewById(R.id.txt_genre);
        image = findViewById(R.id.image);
        image_header = findViewById(R.id.image_header);
        trackName = findViewById(R.id.txt_trackName);
        releaseDate = findViewById(R.id.txt_releaseDate);
        country = findViewById(R.id.txt_country);
        btn_addFav = findViewById(R.id.btn_addFav);
        longDescription = findViewById(R.id.txt_longDescription);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        displayInformation();
        getFilteredFav();

        btn_addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getFilteredFav();

                //this will check if the favorite movie already in database, if not then it'l save.
                if (cursor.getCount() == 0) {
                    btn_addFav.setImageResource(R.drawable.heartoff);
                    boolean isInserted = databaseHelper.addFavorite(trackId, trackname, imageURL, collectionPrice, primaryGenre, releaseDateStr, countryStr, description);
                    if (isInserted) {
                        Toast.makeText(MovieInformation.this, "Added to favorite", Toast.LENGTH_SHORT).show();
                        btn_addFav.setImageResource(R.drawable.hearton);
                    } else {
                        Toast.makeText(MovieInformation.this, "Failed to add favorite", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MovieInformation.this, "Already in favorite.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getFilteredFav() {
        cursor = databaseHelper.filterFav(trackId);
        if (cursor.getCount() == 0) {
            btn_addFav.setImageResource(R.drawable.heartoff);
        } else {
            btn_addFav.setImageResource(R.drawable.hearton);
        }
    }

    private void displayInformation() {
        //getting all information from intent

        trackId = getIntent().getStringExtra("TRACKID");
        trackname = getIntent().getStringExtra("TRACKNAME");
        imageURL = getIntent().getStringExtra("IMAGE");
        collectionPrice = getIntent().getStringExtra("PRICE");
        primaryGenre = getIntent().getStringExtra("GENRE");
        releaseDateStr = getIntent().getStringExtra("RELEASEDATE");
        countryStr = getIntent().getStringExtra("COUNTRY");
        description = getIntent().getStringExtra("DESCRIPTION");

        String[] splitText = releaseDateStr.split("T");

        price.setText("Price : " + collectionPrice);
        genre.setText("Genre : " + primaryGenre);
        longDescription.setText(description);
        trackName.setText(trackname);
        releaseDate.setText("Released Date : " + splitText[0]);
        country.setText("Country : " + countryStr);
        Picasso
                .with(this)
                .load(imageURL)
                .into(image);
//
//        Picasso
//                .with(this)
//                .load(imageURL)
//                .into(image_header);
    }


}