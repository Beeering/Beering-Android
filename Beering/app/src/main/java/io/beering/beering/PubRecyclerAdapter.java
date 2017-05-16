package io.beering.beering;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hsun on 2017. 5. 16..
 */

public class PubRecyclerAdapter extends RecyclerView.Adapter<PubRecyclerAdapter.ViewHolder> {

    private String[] kor_name = {
            "더부스 브루잉",
            "갈매기 브루",
            "펍 3",
            "펍 4",
            "펍 5",
            "펍 6",
            "펍 7" };
    private String[] eng_name = {
            "The Booth Brewing",
            "Galmegi brewing co.",
            "Pub 3",
            "Pub 4",
            "Pub 5",
            "Pub 6",
            "Pub 7"};

    private int[] images = {
            R.drawable.pub_1,
            R.drawable.pub_2,
            R.drawable.pub_3,
            R.drawable.pub_4,
            R.drawable.pub_5,
            R.drawable.pub_6,
            R.drawable.pub_7 };

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
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemKorName.setText(kor_name[position]);
        holder.itemEngName.setText(eng_name[position]);
        holder.itemPubImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return kor_name.length;
    }
}
