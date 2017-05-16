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

public class BeerRecyclerAdapter extends RecyclerView.Adapter<BeerRecyclerAdapter.ViewHolder> {

    private String[] name = {
            "국민 IPA",
            "ㅋ IPA",
            "맥주 3",
            "맥주 4",
            "맥주 5",
            "맥주 6",
            "맥주 7" };
    private String[] style = {
            "India Pale Ale",
            "India Pale Ale",
            "Larger",
            "Larger",
            "Stout",
            "Ale",
            "Larger"};
    private String[] nation = {
            "South Korea",
            "Netherlands",
            "South Korea",
            "Japan",
            "China",
            "South Korea",
            "America" };
    private int[] images = {
            R.drawable.beer_1,
            R.drawable.beer_2,
            R.drawable.beer_3,
            R.drawable.beer_4,
            R.drawable.beer_5,
            R.drawable.beer_6,
            R.drawable.beer_7 };

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemBeerImage;
        public TextView itemName;
        public TextView itemStyle;
        public TextView itemNation;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBeerImage = (ImageView)itemView.findViewById(R.id.item_image_beer);
            itemName = (TextView)itemView.findViewById(R.id.item_name);
            itemStyle = (TextView)itemView.findViewById(R.id.item_style);
            itemNation = (TextView)itemView.findViewById(R.id.item_nation);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_beer, parent ,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemName.setText(name[position]);
        holder.itemStyle.setText(style[position]);
        holder.itemNation.setText(nation[position]);
        holder.itemBeerImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}
