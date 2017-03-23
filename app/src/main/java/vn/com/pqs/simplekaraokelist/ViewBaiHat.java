package vn.com.pqs.simplekaraokelist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import vn.com.pqs.model.Song;

public class ViewBaiHat extends Activity {
//ImageView back;
    TextView bhms,bhcasi,bhtacgia,bhlyric,bhten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewbaihat);
        Bundle b = getIntent().getExtras();
        final Song song = (Song) getIntent().getSerializableExtra("ka");
        initilizevar();
        bhms.setText(song.getId());
        bhcasi.setText(song.getSinger());
        bhtacgia.setText(song.getComposer());
        bhlyric.setText(song.getLyric());
        bhten.setText(song.getMn());


//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                             finish();
//
//            }
//        });
      }


    private void initilizevar() {
//        back = (ImageView) findViewById(R.id.bhbtnback);
        bhms = (TextView) findViewById(R.id.bhms);
        bhcasi = (TextView) findViewById(R.id.bhcasi);
        bhtacgia = (TextView) findViewById(R.id.bhnhacsi);
        bhlyric = (TextView) findViewById(R.id.bhloi);
        bhten = (TextView) findViewById(R.id.bhten);

 }


}