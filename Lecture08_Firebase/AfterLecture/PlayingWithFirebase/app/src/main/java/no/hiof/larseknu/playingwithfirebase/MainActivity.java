package no.hiof.larseknu.playingwithfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import no.hiof.larseknu.playingwithfirebase.adapter.MovieRecyclerAdapter;
import no.hiof.larseknu.playingwithfirebase.model.Movie;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;

    private List<Movie> movieList;
    private List<String> movieListKeys;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference moviedataReference;
    private  ChildEventListener childEventListener;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    private RecyclerView recyclerView;
    private MovieRecyclerAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        movieListKeys = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        moviedataReference = firebaseDatabase.getReference("movies");

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        // Run this _once_ to set up some testdata in your firebase database
        // generateTestData();

        createAuthenticationListener();
        createDatabaseReadListener();

        setUpRecyclerView();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (firebaseAuthStateListener != null)
            firebaseAuth.addAuthStateListener(firebaseAuthStateListener);

        if (childEventListener != null)
            moviedataReference.addChildEventListener(childEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (firebaseAuthStateListener != null)
            firebaseAuth.removeAuthStateListener(firebaseAuthStateListener);

        if (childEventListener != null) {
            moviedataReference.removeEventListener(childEventListener);
        }

        movieList.clear();
        movieListKeys.clear();
        movieAdapter.notifyDataSetChanged();
    }


    private void createAuthenticationListener() {
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    private void createDatabaseReadListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Movie movie = dataSnapshot.getValue(Movie.class);
                String movieKey = dataSnapshot.getKey();
                movie.setUid(movieKey);

                if (!movieList.contains(movie)) {
                    movieList.add(movie);
                    movieListKeys.add(movieKey);
                    movieAdapter.notifyItemInserted(movieList.size()-1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Movie movie = dataSnapshot.getValue(Movie.class);
                String movieKey = dataSnapshot.getKey();
                movie.setUid(movieKey);

                int position = movieListKeys.indexOf(movieKey);

                movieList.set(position, movie);
                movieAdapter.notifyItemChanged(position);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Movie removedMovie = dataSnapshot.getValue(Movie.class);
                String movieKey = dataSnapshot.getKey();
                removedMovie.setUid(movieKey);
                int position = movieListKeys.indexOf(movieKey);
                movieList.remove(removedMovie);
                movieListKeys.remove(position);
                movieAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.movieRecyclerView);
        movieAdapter = new MovieRecyclerAdapter(this, movieList);

        movieAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);

                Movie movie = movieList.get(position);

                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_UID, movie.getUid());

                startActivity(intent);
            }
        });

        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Toast.makeText(this, "Signed in as " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_item:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void generateTestData() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(null, "Iron Man 3", "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.", "2013-04-18", null));
        movies.add(new Movie(null, "Donnie Darko", "A troubled teenager is plagued by visions of a man in a large rabbit suit who manipulates him to commit a series of crimes, after he narrowly escapes a bizarre accident.", "2001-10-26", null));
        movies.add(new Movie(null, "Pulp Fiction", "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.", "1994-10-14", null));
        movies.add(new Movie(null, "Spirited Away", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", "2001-06-20", null));
        movies.add(new Movie(null, "Star Wars: The Force Awakens", "Thirty years after defeating the Galactic Empire, Han Solo and his allies face a new threat from the evil Kylo Ren and his army of Stormtroopers.", "2015-12-14", null));
        movies.add(new Movie(null, "Up", "Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway.", "2009-09-25", null));
        movies.add(new Movie(null, "The Hobbit", "Bilbo Baggins, a hobbit enjoying his quiet life, is swept into an epic quest by Gandalf the Grey and thirteen dwarves who seek to reclaim their mountain home from Smaug, the dragon.", "2012-12-06", null));
        movies.add(new Movie(null, "Captain America: Civil War", "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.", "2016-04-12", null));
        movies.add(new Movie(null, "Deadpool", "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.", "2016-02-12", null));

        for (Movie movie : movies) {
            moviedataReference.push().setValue(movie);
        }
    }
}
