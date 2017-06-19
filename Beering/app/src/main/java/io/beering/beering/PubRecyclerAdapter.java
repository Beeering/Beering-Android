package io.beering.beering;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by hsun on 2017. 5. 16..
 */

public class PubRecyclerAdapter extends RecyclerView.Adapter<PubRecyclerAdapter.ViewHolder> {

    private List<Pub> pubList;

    ///
    private int[] tmpImage = {R.drawable.pub_1, R.drawable.pub_2, R.drawable.pub_3};

    public PubRecyclerAdapter(List<Pub> items) {
        this.pubList = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemPubImage;
        public TextView itemKorName;
        public TextView itemEngName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemPubImage = (ImageView)itemView.findViewById(R.id.item_image_pub);
            itemKorName = (TextView)itemView.findViewById(R.id.item_kor_name);
            itemEngName = (TextView)itemView.findViewById(R.id.item_eng_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_pub, parent ,false);

        final ViewHolder viewHolderPub = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PubDetailActivity.class);
                Log.d("..........!!", getApplicationContext()+"");

                try{ Thread.sleep(1000);}
                catch(Exception e){}




                int pubId = viewHolderPub.getAdapterPosition()+1;
                intent.putExtra("pub_id", pubId);
                v.getContext().startActivity(intent);
            }
        });

        return viewHolderPub;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pub item = pubList.get(position);
        holder.itemKorName.setText(item.getPubKorName());
        holder.itemEngName.setText(item.getPubEngName());
        holder.itemPubImage.setImageResource(tmpImage[position % 3]);
    }

    @Override
    public int getItemCount() {
        return pubList.size();
    }
}
