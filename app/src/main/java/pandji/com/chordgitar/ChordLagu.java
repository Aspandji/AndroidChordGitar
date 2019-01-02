package pandji.com.chordgitar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

import pandji.com.chordgitar.Adapter.ViewHolder;
import pandji.com.chordgitar.Adapter.ViewHolder2;
import pandji.com.chordgitar.model.Chordlagu;

public class ChordLagu extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFireDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_lagu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar

        //ReclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //mengatur layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //firebase
        mFireDatabase = FirebaseDatabase.getInstance();
        mRef = mFireDatabase.getReference("ChordLagu");
    }

    private void firebasesearch(String searchText) {
        Query firebaseSearchQuery = mRef.orderByChild("Judul").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerAdapter <Chordlagu, ViewHolder2> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Chordlagu, ViewHolder2>(
                        Chordlagu.class,
                        R.layout.desaignchordlagu,
                        ViewHolder2.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder2 viewHolder, Chordlagu model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getChord() ,model.getImage());
                    }

                    @Override
                    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder2 viewHolder2 = super.onCreateViewHolder(parent, viewType);

                        viewHolder2.setOnClickListener(new ViewHolder2.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mJudulTv = view.findViewById(R.id.rJudulTv);
                                TextView mChordTv = view.findViewById(R.id.rChordTv);
                                //get data
                                String mJudul = mJudulTv.getText().toString();
                                String mChord = mChordTv.getText().toString();

                                //pass data
                                Intent intent = new Intent(view.getContext(), DetailChordLagu.class);
                                intent.putExtra("Judul", mJudul);
                                intent.putExtra("Chord", mChord);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder2;
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter <Chordlagu, ViewHolder2> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Chordlagu, ViewHolder2>(
                        Chordlagu.class,
                        R.layout.desaignchordlagu,
                        ViewHolder2.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder2 viewHolder, Chordlagu model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getJudul(), model.getChord() ,model.getImage());
                    }

                    @Override
                    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder2 viewHolder2 = super.onCreateViewHolder(parent, viewType);

                        viewHolder2.setOnClickListener(new ViewHolder2.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mJudulTv = view.findViewById(R.id.rJudulTv);
                                TextView mChordTv = view.findViewById(R.id.rChordTv);
                                //get data
                                String mJudul = mJudulTv.getText().toString();
                                String mChord = mChordTv.getText().toString();

                                //pass data
                                Intent intent = new Intent(view.getContext(), DetailChordLagu.class);
                                intent.putExtra("Judul", mJudul);
                                intent.putExtra("Chord", mChord);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder2;
                    }
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebasesearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebasesearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_search){
            //TODO
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
