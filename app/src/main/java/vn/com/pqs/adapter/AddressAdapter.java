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
import vn.com.pqs.model.Address;
import vn.com.pqs.simplekaraokelist.MainActivity;
import vn.com.pqs.simplekaraokelist.R;

/**
 * Created by VietHoa on 07/10/15.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>
    {

    List<Address> dataset;
    MainActivity main1 ;
    public AddressAdapter(List<Address> dataset, MainActivity main1) {
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
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsad, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Address address = this.dataset.get(position);
        holder.tenQuan.setText(address.getName());
        holder.diachi.setText(address.getAd());
        holder.sdt.setText(address.getPhone());
        holder.giaca.setText(address.getPrice());
        final String check = address.getAd();
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
          @Bind(R.id.giaca)
          TextView giaca;
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
