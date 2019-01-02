package pandji.com.chordgitar;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailChord extends AppCompatActivity {

    TextView mNameTv, mDetailTv;
    ImageView mImageIv;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chord);

        //action bar

        mNameTv = findViewById(R.id.NameTv);
        mDetailTv = findViewById(R.id.deskripsi);
        mImageIv = findViewById(R.id.imageView);

        //get data from intent
        byte[] bytes = getIntent().getByteArrayExtra("Image");
        String name = getIntent().getStringExtra("Name");
        String desk = getIntent().getStringExtra("Deskripsi");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mNameTv.setText(name);
        mDetailTv.setText(desk);
        mImageIv.setImageBitmap(bmp);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
