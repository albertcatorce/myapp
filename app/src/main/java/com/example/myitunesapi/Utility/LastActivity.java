package com.example.myitunesapi.Utility;

import android.content.Context;
import com.example.myitunesapi.Database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LastActivity {

    private DatabaseHelper databaseHelper;

    //this method will save the last activity and date of last visited to sqlite database

    public void getCalendar(Context context, String activity) {
        databaseHelper = new DatabaseHelper(context);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = dateFormat.format(c);

        try {
            databaseHelper.addLastActivity(activity, formatDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
