package pandji.com.chordgitar;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        logo.startAnimation(myanim);
        final Intent i = new Intent(this,HalmanAwal.class);
        Thread timer = new Thread(){
          public void run(){
              try{
                  sleep(5000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
              finally {
                  startActivity(i);
                  finish();
              }
          }
        };
        timer.start();
    }
}
