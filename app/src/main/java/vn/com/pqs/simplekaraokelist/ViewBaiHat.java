package vn.com.pqs.simplekaraokelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.pqs.model.BaiHat;

public class ViewBaiHat extends Activity {
ImageView back;
    TextView bhms,bhcasi,bhtacgia,bhlyric,bhten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewbaihat);
        Bundle b = getIntent().getExtras();
        final BaiHat baiHat = (BaiHat) getIntent().getSerializableExtra("ka");
        initilizevar();
        bhms.setText(baiHat.getTxtms());
        bhcasi.setText(baiHat.getTxtcs());
        bhtacgia.setText(baiHat.getTxtcs());
        bhlyric.setText(baiHat.getTxtLr());
        bhten.setText(baiHat.getTenBh());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                             finish();

            }
        });
      }


    private void initilizevar() {
        back = (ImageView) findViewById(R.id.bhbtnback);
        bhms = (TextView) findViewById(R.id.bhms);
        bhcasi = (TextView) findViewById(R.id.bhcasi);
        bhtacgia = (TextView) findViewById(R.id.bhnhacsi);
        bhlyric = (TextView) findViewById(R.id.bhloi);
        bhten = (TextView) findViewById(R.id.bhten);

 }


}