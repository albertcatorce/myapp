package com.example.myitunesapi.Utility;

import android.content.Context;
import android.util.Log;

import com.example.myitunesapi.Database.DatabaseHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImportDB {

    //This method will copy database to file from asset to application
    public boolean copyDatabase(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            InputStream inputStream = context.getAssets().open(databaseHelper.DB_NAME);
            String outFileName = databaseHelper.DB_LOCATION + databaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("ALBERT", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
