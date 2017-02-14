package vn.com.pqs.adapter;

import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.viethoa.RecyclerViewFastScroller;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.com.pqs.model.BaiHat;
import vn.com.pqs.simplekaraokelist.MainActivity;
import vn.com.pqs.simplekaraokelist.R;

/**
 * Created by VietHoa on 07/10/15.
 */
public class DiaDiemAdapter extends RecyclerView.Adapter<DiaDiemAdapter.ViewHolder>
    implements RecyclerViewFastScroller.BubbleTextGetter {

    List<BaiHat> dataset;
    MainActivity main1 ;
    public DiaDiemAdapter(List<BaiHat> dataset, MainActivity main1) {
        this.dataset = dataset;
        this.main1 = main1;
    }

    @Override
    public int getItemCount() {
       if (dataset == null)
            return 0;
        return dataset.size();
    }

    @Override
    public DiaDiemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BaiHat baiHat = this.dataset.get(position);
        holder.ms.setText(baiHat.getTxtms());
        holder.bhName.setText(baiHat.getTenBh());
        holder.lr.setText(baiHat.getTxtLr());
        holder.cs.setText(baiHat.getTxtcs());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mschon = baiHat.getTxtms().toString();

               if(baiHat.getThich()){
              baiHat.setThich(false);
              baiHat.setImg(R.drawable.addfav);
              updateThich(mschon,false);
              main1.dsYeuthich.remove(baiHat);

          }else{
              baiHat.setThich(true);
              baiHat.setImg(R.drawable.added);
                   updateThich(mschon,true);
                   main1.dsYeuthich.add(baiHat);
               }
      main1.recyclerViewAdapter.notifyDataSetChanged();
                main1.ytRecle.notifyDataSetChanged();

      //mainActivity.recyclerViewAdapter.notifyDataSetChanged();
                   }
        });
        holder.imageButton.setImageResource(baiHat.getImg());
    }

    public void updateThich(String ms,boolean thich) {
        ContentValues values = new ContentValues();
        if(thich==false){
            values.put("YEUTHICH","0");}
        else{
            values.put("YEUTHICH","1");
        }
        int ret = main1.database.update("ArirangSongList",values,"MABH='"+ms+"'",null);

    }

    @Override
    public String getTextToShowInBubble(int pos) {

        if (pos < 0 || pos >= dataset.size())
            return null;

        String name = dataset.get(pos).getTenviettat();
        String strAlpha = name.substring(0, 1);
        if (name == null || name.length() < 1)
            return null;

        strAlpha.replace("1","#");
        strAlpha.replace("2","#");
        strAlpha.replace("9","#");
        return strAlpha;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ms)
        TextView ms;
        @Bind(R.id.bh)
        TextView bhName;
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
