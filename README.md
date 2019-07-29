If the App execute on its first load, the app will check itself if the database exist, if not it will copy the database file from assets folder.

I use navigation drawer for convenient for the user, and easy to use.

The application compose of three functions movie activity, favorite activity and information activity.

I use fragment to separate the activity, MovieFragment which are all the movies from JSONObject will load to the gridview.

If the user will select an item from gridview list, it will display the information, the information contains fields track name,

primary genre, artwork of the movie, price and description. If the user will press the heart button, it means the movie's information

will insert to database which has a table called tbl_favMovie. 

FavoriteFragment which are all favorite movies from SqliteDatabase will load to gridview.

if the user will press the heart icon of an item, it will automatic remove from the list, it means it will be deleted from the database.

I use gridview layout with 3 columns becaus the pixels of the artwork is not good which is not appropriate for the user.

And I use Picasso library to display the image form imageUrl.