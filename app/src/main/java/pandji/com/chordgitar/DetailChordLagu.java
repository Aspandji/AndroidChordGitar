package pandji.com.chordgitar;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailChordLagu extends AppCompatActivity {

    TextView mJudulTv, mChordTv;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chord_lagu);

        //action bar
        mJudulTv = findViewById(R.id.JudulTv);
        mChordTv = findViewById(R.id.ChordTv);

        //get data from intent
        String judul = getIntent().getStringExtra("Judul");
        String chord = getIntent().getStringExtra("Chord");

        mJudulTv.setText(judul);
        mChordTv.setText(chord);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
