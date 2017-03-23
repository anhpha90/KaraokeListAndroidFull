package vn.com.pqs.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import vn.com.pqs.model.Song;
import vn.com.pqs.simplekaraokelist.MainActivity;
import vn.com.pqs.simplekaraokelist.R;

/**
 * Created by long on 03/12/2016.
 */
public class SongAdapter extends ArrayAdapter<Song> {
    //đối số 1: màn hình sử dụng layout này (giao diện này)
    Activity context;
    //layout cho từng dòng muốn hiển thị (làm theo khách hàng)
    int resource;
    //danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    List<Song> objects;
    MainActivity main2 = (MainActivity) getContext();

    public SongAdapter(Activity context, int resource, List<Song> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource, null);
        TextView txtMs = (TextView) row.findViewById(R.id.ms);
        TextView txtBh = (TextView) row.findViewById(R.id.bh);
        TextView txtCs = (TextView) row.findViewById(R.id.cs);

        TextView txtLr = (TextView) row.findViewById(R.id.lr);
        ImageButton imglike = (ImageButton) row.findViewById(R.id.imageButton);
        final Song song = this.objects.get(position);
        txtMs.setText(song.getId());
        txtBh.setText(song.getMn());

        txtCs.setText(song.getSinger());
        txtLr.setText(song.getSlyric());

               imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               xulythich(song,position);
            }
        });
        imglike.setImageResource(song.getImg());
        return row;
    }

    public void xulythich(Song song, int pos) {
        String mschon = song.getId().toString();

        if(song.isLike()){
         updateThich(mschon,false);
         song.setLike(false);
         song.setImg(R.drawable.addfav);
          main2.dsYeuthich.remove(song);
        }else{
            updateThich(mschon,true);
            song.setLike(true);
           song.setImg(R.drawable.added);
            main2.dsYeuthich.add(song);

        }
       //main2.YtAdapter.notifyDataSetChanged();
      // main2.DsAdapter.notifyDataSetChanged();
       if(main2.baiHatAdapter!=null){main2.baiHatAdapter.notifyDataSetChanged();}
          }
    public void updateThich(String ms,boolean thich) {
        ContentValues values = new ContentValues();
        if(thich==false){
        values.put("LIKE","0");}
        else{
            values.put("LIKE","1");
        }
       int ret = main2.database.update("Arirang",values,"MABH='"+ms+"'",null);

       }
}
