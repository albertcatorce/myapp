package com.example.myitunesapi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myitunesapi.Database.DatabaseHelper;
import com.example.myitunesapi.Fragment.FavoriteFragment;
import com.example.myitunesapi.Fragment.MovieFragment;
import com.example.myitunesapi.R;
import com.example.myitunesapi.Utility.ImportDB;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private DatabaseHelper databaseHelper;
    private String toolbarTilte;
    private ImportDB importDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        importDB = new ImportDB();
        getDbFile();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);


        String lastActivity = null;
        String lastVisitedStr = null;
        Cursor cursor = databaseHelper.getlastActivity();


        //this if statement will determine what user's last activity visited, as well as when the last date visited
        if (cursor.moveToFirst()) {
            lastActivity = cursor.getString(1);
            lastVisitedStr = cursor.getString(2);
        }
        if (lastVisitedStr != null) {
            toolbar.setSubtitle("Last visited : " + lastVisitedStr);
        }
        getSupportActionBar().setTitle(lastActivity);

        if (savedInstanceState == null) {
            if (lastActivity.equals("FAVORITE")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteFragment()).commit();

            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovieFragment()).commit();

            }
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieFragment()).commit();
                        toolbarTilte = "MOVIE";
                        break;

                    case R.id.nav_fav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                        toolbarTilte = "FAVORITE";
                        break;

                }
                toolbar.setTitle(toolbarTilte);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //this method will get the dbfile from asset and import to application
    private void getDbFile() {
        File databaseFile = this.getDatabasePath(databaseHelper.DB_NAME);
        if (databaseFile.exists() == false) {
            databaseHelper.getReadableDatabase();
            if (importDB.copyDatabase(this)) {
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy database error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

}
