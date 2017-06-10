package io.beering.beering;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by hsun on 2017. 6. 10..
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>{
    private int[] comment_image = {R.drawable.face_1, R.drawable.face_2};
    private String[] comment_name = {"김첨지", "박철수"};
    private String[] comment_date = {"2017.05.07", "2017.05.10"};
    private int[] comment_rated = {3, 1};
    private String[] comment_text = {"갓 짠 라임이 떠오르는 은은하고 신선한 과일향! 쓴 맛은 덜하고 부드럽게 넘어가는 목넘김을 자랑하는 키읔 IPA. 산뜻하고 깨끗한 첫 잔으로 제격이다.", "맛없어요."};

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemCommentImage;
        public TextView itemCommentName;
        public TextView itemCommentDate;
        public RatingBar itemCommentRated;
        public TextView itemCommentText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemCommentImage = (ImageView)itemView.findViewById(R.id.comment_image);
            itemCommentName = (TextView)itemView.findViewById(R.id.comment_name);
            itemCommentDate = (TextView)itemView.findViewById(R.id.comment_date);
            itemCommentRated = (RatingBar)itemView.findViewById(R.id.comment_rated);
            itemCommentText = (TextView)itemView.findViewById(R.id.comment_text);

            // 맥주리뷰 프로필 사진 원형으로
            itemCommentImage.setBackground(new ShapeDrawable(new OvalShape()));
            itemCommentImage.setClipToOutline(true);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent ,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemCommentName.setText(comment_name[position]);
        holder.itemCommentImage.setImageResource(comment_image[position]);
        holder.itemCommentDate.setText(comment_date[position]);
        holder.itemCommentRated.setRating(comment_rated[position]);
        holder.itemCommentText.setText(comment_text[position]);
    }

    @Override
    public int getItemCount() {
        return comment_name.length;
    }
}
