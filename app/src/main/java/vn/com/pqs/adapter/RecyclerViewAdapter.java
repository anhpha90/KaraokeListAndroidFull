package vn.com.pqs.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viethoa.RecyclerViewFastScroller;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.com.pqs.model.Song;
import vn.com.pqs.simplekaraokelist.MainActivity;
import vn.com.pqs.simplekaraokelist.R;
import vn.com.pqs.simplekaraokelist.ViewBaiHat;

/**
 * Created by VietHoa on 07/10/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
    implements RecyclerViewFastScroller.BubbleTextGetter {
Context context;
    List<Song> dataset;
    MainActivity main1 ;
    public RecyclerViewAdapter(List<Song> dataset, MainActivity main1,Context context) {
        this.dataset = dataset;
        this.main1 = main1;
        this.context = context;
    }

    @Override
    public int getItemCount() {
       if (dataset == null)
            return 0;
        return dataset.size();
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Song song = this.dataset.get(position);
        holder.ms.setText(song.getId());
        holder.bhName.setText(song.getMn());
        holder.lr.setText(song.getSlyric());
        holder.cs.setText(song.getSinger());
        holder.baihatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mcon = v.getContext();
                Intent intent = new Intent(mcon, ViewBaiHat.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("ka",song);
                intent.putExtras(mBundle);
                mcon.startActivity(intent);

//                Intent intent = new Intent(v.getContext(),LienHe.class);
//                context.startActivity(intent);
                          }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mschon = song.getId().toString();

               if(song.isLike()){
              song.setLike(false);
              song.setImg(R.drawable.addfav);
if(main1.listKa=="A"){
              updateThich(mschon,false);
              main1.dsYeuthich.remove(song);}
                   if(main1.listKa=="C"){updateThichCa(mschon,false);
                       main1.dsYeuthichCa.remove(song);}


          }else{
              song.setLike(true);
              song.setImg(R.drawable.added);
                   if(main1.listKa=="A"){ updateThich(mschon,true);
                   main1.dsYeuthich.add(song);}
                   if(main1.listKa=="C"){ updateThichCa(mschon,true);
                       main1.dsYeuthichCa.add(song);}
                               }
      main1.recyclerViewAdapter.notifyDataSetChanged();
      main1.recyclerViewAdapterCa.notifyDataSetChanged();

      main1.ytRecle.notifyDataSetChanged();
      main1.ytRecleCa.notifyDataSetChanged();

     if(main1.rvTimKiem!=null) main1.rvTimKiem.notifyDataSetChanged();

      //mainActivity.recyclerViewAdapter.notifyDataSetChanged();
                   }
        });
        holder.imageButton.setImageResource(song.getImg());
    }

    public void updateThichCa(String ms,boolean thich) {
        ContentValues values = new ContentValues();
        if(thich==false){
            values.put("LIKE","0");}
        else{
            values.put("LIKE","1");
        }
        int ret = main1.database.update("Cali",values,"ID='"+ms+"'",null);

    }
    public void updateThich(String ms,boolean thich) {
        ContentValues values = new ContentValues();
        if(thich==false){
            values.put("LIKE","0");}
        else{
            values.put("LIKE","1");
        }
        int ret = main1.database.update("Arirang",values,"ID='"+ms+"'",null);

    }

      @Override
    public String getTextToShowInBubble(int pos) {

        if (pos < 0 || pos >= dataset.size())
            return null;

        String name = dataset.get(pos).getSmn();
        String strAlpha = name.substring(0, 1);
        if (name == null || name.length() < 1)
            return null;
        return strAlpha;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ms)
        TextView ms;
        @Bind(R.id.bh)
        TextView bhName;
        @Bind(R.id.baihatview)
        LinearLayout baihatView;
        @Bind(R.id.cs)
        TextView cs;
        @Bind(R.id.lr)
        TextView lr;
        @Bind(R.id.imageButton)
        ImageButton imageButton;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
