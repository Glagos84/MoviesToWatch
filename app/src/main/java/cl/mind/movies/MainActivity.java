package cl.mind.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.mind.movies.models.Movie;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    public static final String MOVIE_ID = "cl.mind.movies.KEY.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText nameMovieEt = (EditText) findViewById(R.id.movieNameEt);
        Button buttonSave = (Button) findViewById(R.id.saveMovieBtn);
        Button buttonLastMovie = (Button) findViewById(R.id.lastMovieBtn);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nameMovieEt.getText().toString().trim().length() > 0) {

                    Movie movie = new Movie();
                    movie.setName(nameMovieEt.getText().toString());
                    movie.setWatched(false);
                    movie.save();
                    movies = getMovies();
                    Toast.makeText(MainActivity.this, "SE HA GUARDADO LA PELÍCULA", Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(MainActivity.this, "INGRESE UN NOMBRE PELÍCULA VALIDO", Toast.LENGTH_SHORT).show();

                }


            }
        });

        buttonLastMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int listSize = movies.size();

                if (listSize > 0){
                    int lastElement = movies.size()-1;

                    Movie lastMovie = movies.get(lastElement);
                    long id = lastMovie.getId();

                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, id);
                    startActivity(intent);
                }else{


                    Toast.makeText(MainActivity.this, "No hay películas", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    private List<Movie> getMovies() {

        //movies.add(new Movie());
        return Movie.find(Movie.class, "watched = 0");


    }

    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();

    }
}
