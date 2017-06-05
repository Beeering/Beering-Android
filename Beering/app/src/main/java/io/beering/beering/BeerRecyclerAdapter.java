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

public class BeerRecyclerAdapter extends RecyclerView.Adapter<BeerRecyclerAdapter.ViewHolder> {

    private List<Beer> beerList;
    private int currentPos;

    public BeerRecyclerAdapter(List<Beer> items) {
        this.beerList = items;
    }

    //view 재활용을 위한 viewholder
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

        final ViewHolder viewHolderBeer = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "맥주 누름", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), BeerDetailActivity.class);
                int beerId = viewHolderBeer.getAdapterPosition()+1;
                intent.putExtra("beer_id", beerId);
                v.getContext().startActivity(intent);

            }
        });

        return viewHolderBeer;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Beer item = beerList.get(position);
        holder.itemName.setText(item.getBeerName());
        holder.itemStyle.setText(item.getBeerStyle());
        holder.itemNation.setText(item.getBeerNation());
        holder.itemBeerImage.setImageURI(Uri.parse(item.getBeerImage()));
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }
}
