package com.example.myitunesapi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myitunesapi.Model.Movie;
import com.example.myitunesapi.Model.MovieSQLite;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "movie.db";
    public final static String DB_LOCATION = "data/data/com.example.myitunesapi/databases/";
    public final static String TBL_FAVMOVIE = "tbl_favMovie";
    public final static String fav_COL_0 = "TRACKID";
    public final static String fav_COL_1 = "TRACKNAME";
    public final static String fav_COL_2 = "IMAGEURL";
    public final static String fav_COL_3 = "COLLECTIONPRICE";
    public final static String fav_COL_4 = "PRIMARYGENRENAME";
    public final static String fav_COL_5 = "DATERELEASE";
    public final static String fav_COL_6 = "COUNTRY";
    public final static String fav_COL_7 = "DESCRIPTION";

    public final static String TBL_ACTIVITY = "tbl_activity";
    public final static String act_COL_1 = "ACTIVITY";
    public final static String act_COL_2 = "DATE";


    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // openning database everytime it has a transaction
    public void openDatabase() {
        String path = context.getDatabasePath(DB_NAME).getPath();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }
    // closing database after the transaction happend
    public void closeDatabase() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }


    // adding of favorite movies
    public boolean addFavorite(String trackid,
                               String trackName,
                               String imageUrl,
                               String collectionPrice,
                               String primaryGenreName,
                               String dateRelease,
                               String country,
                               String description) {
        sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fav_COL_0, trackid);
        contentValues.put(fav_COL_1, trackName);
        contentValues.put(fav_COL_2, imageUrl);
        contentValues.put(fav_COL_3, collectionPrice);
        contentValues.put(fav_COL_4, primaryGenreName);
        contentValues.put(fav_COL_5, dateRelease);
        contentValues.put(fav_COL_6, country);
        contentValues.put(fav_COL_7, description);

        long isInserted = sqLiteDatabase.insert(TBL_FAVMOVIE, null, contentValues);
        if (isInserted == -1)
            return false;
        else
            return true;
    }

    //this query will filter if the movie already in the database by trackid,
    //if it is not in the list, then save the movie information to favorite
    public Cursor filterFav(String trackId) {
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM '" + TBL_FAVMOVIE + "' WHERE TRACKID ='" + trackId + "'", null);
        return cursor;
    }


    //this method has a query that will load up all the movie's information to fragment favorite
    public List<MovieSQLite> getFavorite() {
        MovieSQLite movieSQLite = null;
        List<MovieSQLite> movieList = new ArrayList<>();
        openDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TBL_FAVMOVIE, null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            movieSQLite = new MovieSQLite(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8));
            movieList.add(movieSQLite);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return movieList;
    }

    //this method has a query that will the update last activity with parameter activity name and date
    public boolean addLastActivity(String activityName, String date) {
        sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(act_COL_1, activityName);
        contentValues.put(act_COL_2, date);

        long isInserted = sqLiteDatabase.update(TBL_ACTIVITY, contentValues, "ID='" + 1 + "'", null);
        if (isInserted == -1)
            return false;
        else
            return true;
    }

    //this method has a query that will generate a last activity from database
    public Cursor getlastActivity() {
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM '" + TBL_ACTIVITY + "'", null);
        return cursor;
    }

    //this method has a query to delete the fav. movie from favorite frament
    public Integer removeFav(String trackName) {
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TBL_FAVMOVIE, "TRACKNAME = ?", new String[]{trackName});
    }

}
