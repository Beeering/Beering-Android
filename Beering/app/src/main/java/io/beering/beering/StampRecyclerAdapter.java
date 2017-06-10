package io.beering.beering;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by hsun on 2017. 5. 31..
 */

public class StampRecyclerAdapter extends RecyclerView.Adapter<StampRecyclerAdapter.ViewHolder>{
    private int[] stamp_image = {R.drawable.stamp_1, R.drawable.stamp_2, R.drawable.stamp_3, R.drawable.stamp_4, R.drawable.stamp_5, R.drawable.stamp_6};
    private String[] stamp_name = {"국민 IPA", "ㅋ 맥주", "대동강 페일에일", "맥스", "카스 레몬", "기네스"};
    private String[] stamp_style = {"India Pale Ale", "India Pale Ale", "India Pale Ale", "India Pale Ale", "India Pale Ale", "Stout"};
    private String[] stamp_nation = {"South Korea", "South Korea", "South Korea", "South Korea", "South Korea", "Ireland"};
    private int[] stamp_rated = {3, 4, 0, 3, 1, 3};

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemStampImage;
        public TextView itemStampName;
        public TextView itemStampStyle;
        public TextView itemStampNation;
        public RatingBar itemStampRated;

        public ViewHolder(View itemView) {
            super(itemView);
            itemStampImage = (ImageView)itemView.findViewById(R.id.stamp_image);
            itemStampName = (TextView)itemView.findViewById(R.id.stamp_name);
            itemStampStyle = (TextView)itemView.findViewById(R.id.stamp_style);
            itemStampNation = (TextView)itemView.findViewById(R.id.stamp_nation);
            itemStampRated = (RatingBar) itemView.findViewById(R.id.stamp_rated);
        }
    }

    @Override
    public StampRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stamp_recycler_layout, parent ,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StampRecyclerAdapter.ViewHolder holder, int position) {
        holder.itemStampName.setText(stamp_name[position]);
        holder.itemStampStyle.setText(stamp_style[position]);
        holder.itemStampNation.setText(stamp_nation[position]);
        holder.itemStampImage.setImageResource(stamp_image[position]);
        holder.itemStampRated.setRating(stamp_rated[position]);
    }

    @Override
    public int getItemCount() {
        return stamp_name.length;
    }
}
