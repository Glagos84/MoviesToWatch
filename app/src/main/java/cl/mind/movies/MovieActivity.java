package cl.mind.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import cl.mind.movies.models.Movie;

import static android.R.attr.id;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        long movieId = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);

        movie = Movie.findById(Movie.class, movieId);
        checkBox = (CheckBox) findViewById(R.id.movieCb);

        getSupportActionBar().setTitle(movie.getName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(checkBox.isChecked());
        movie.save();

    }
}
