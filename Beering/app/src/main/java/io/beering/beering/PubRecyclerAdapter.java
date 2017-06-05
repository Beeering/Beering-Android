package io.beering.beering;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by hsun on 2017. 5. 16..
 */

public class PubRecyclerAdapter extends RecyclerView.Adapter<PubRecyclerAdapter.ViewHolder> {

    private List<Pub> pubList;

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

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "펍 누름", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PubDetailActivity.class);
                v.getContext().startActivity(intent);

                // intent에 extra로 position줘야함
            }
        });

        ViewHolder viewHolderPub = new ViewHolder(v);

        return viewHolderPub;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pub item = pubList.get(position);
        holder.itemKorName.setText(item.getPubKorName());
        holder.itemEngName.setText(item.getPubEngName());
        holder.itemPubImage.setImageURI(Uri.parse(item.getPubImage()));
    }

    @Override
    public int getItemCount() {
        return pubList.size();
    }
}
