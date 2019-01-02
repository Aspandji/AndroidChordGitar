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
import pandji.com.chordgitar.model.Chorddsr;

public class ChordDasar extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFireDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_dasar);
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
        mRef = mFireDatabase.getReference("ChordDasar");
    }


    //Search
    private void firebasesearch(String searchText){
        Query firebaseSearchQuery = mRef.orderByChild("Name").startAt(searchText).endAt(searchText +"\uf8ff");

        FirebaseRecyclerAdapter<Chorddsr, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Chorddsr, ViewHolder>(
                        Chorddsr.class,
                        R.layout.desaignchorddsr,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Chorddsr model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDeskripsi(), model.getImage());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mNameTv = view.findViewById(R.id.rNameTv);
                                TextView mDeskTv = view.findViewById(R.id.rDeskripsiTv);
                                ImageView mImageView = view.findViewById(R.id.rImageView);
                                //get data
                                String mName = mNameTv.getText().toString();
                                String mDesk = mDeskTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                //pass data
                                Intent intent = new Intent(view.getContext(), DetailChord.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes =stream.toByteArray();
                                intent.putExtra("Image", bytes);
                                intent.putExtra("Name", mName);
                                intent.putExtra("Deskripsi", mDesk);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
                    }
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Chorddsr, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Chorddsr, ViewHolder>(
                        Chorddsr.class,
                        R.layout.desaignchorddsr,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Chorddsr model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDeskripsi() ,model.getImage());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mNameTv = view.findViewById(R.id.rNameTv);
                                TextView mDeskTv = view.findViewById(R.id.rDeskripsiTv);
                                ImageView mImageView = view.findViewById(R.id.rImageView);
                                //get data
                                String mName = mNameTv.getText().toString();
                                String mDesk = mDeskTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                //pass data
                                Intent intent = new Intent(view.getContext(), DetailChord.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes =stream.toByteArray();
                                intent.putExtra("Image", bytes);
                                intent.putExtra("Name", mName);
                                intent.putExtra("Deskripsi", mDesk);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });

                        return viewHolder;
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
