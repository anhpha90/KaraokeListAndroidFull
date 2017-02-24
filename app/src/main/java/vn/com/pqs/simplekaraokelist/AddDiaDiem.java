package vn.com.pqs.simplekaraokelist;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import vn.com.pqs.model.BaiHat;

public class AddDiaDiem extends Activity {
ImageView back,bhmback,bhmplay,bhmnext;
    TextView bhms,bhcasi,bhtacgia,bhlyric,bhten;
    MediaPlayer mPlayer;
    String url;
    Boolean play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewbaihat);
        Bundle b = getIntent().getExtras();
        final BaiHat baiHat = (BaiHat) getIntent().getSerializableExtra("ka");
        initilizevar();
        url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";
        bhms.setText(baiHat.getTxtms());
        bhcasi.setText(baiHat.getTxtcs());
        bhtacgia.setText(baiHat.getTxtcs());
        bhlyric.setText(baiHat.getTxtLr());
        bhten.setText(baiHat.getTenBh());

        play = false;
        bhmplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               playmusic();
            }
        });
      }

    private void playmusic() {
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(url);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        mPlayer.start();
    }

    private void initilizevar() {
        back = (ImageView) findViewById(R.id.ibback);
        bhmplay = (ImageView) findViewById(R.id.bhmplay);
        bhmback = (ImageView) findViewById(R.id.bhmback);
        bhmnext = (ImageView) findViewById(R.id.bhmnext);
        bhms = (TextView) findViewById(R.id.bhms);
        bhcasi = (TextView) findViewById(R.id.bhcasi);
        bhtacgia = (TextView) findViewById(R.id.bhnhacsi);
        bhlyric = (TextView) findViewById(R.id.bhloi);
        bhten = (TextView) findViewById(R.id.bhten);
 }
}