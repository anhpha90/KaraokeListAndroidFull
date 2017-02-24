package vn.com.pqs.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    {

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsad, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BaiHat diaDiem = this.dataset.get(position);
                holder.tenQuan.setText(diaDiem.getTenBh());
        holder.diachi.setText(diaDiem.getTxtcs());
        holder.sdt.setText(diaDiem.getTenviettat());
        final String check = diaDiem.getTxtcs();
        holder.loca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mcon = v.getContext();
                String map = "http://maps.google.co.in/maps?q=" + check;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                mcon.startActivity(i);
            }
        });

    }
      public static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tenquan)
        TextView tenQuan;
        @Bind(R.id.address)
        TextView diachi;
        @Bind(R.id.sdt)
        TextView sdt;
@Bind(R.id.loca)
ImageView loca;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
