package io.beering.beering;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

/**
 * Created by hsun on 2017. 5. 17..
 */

public class ReviewDialog extends Dialog {

    ImageButton cancelBtn;
    Button completeBtn;
    EditText reviewText;
    RatingBar starRating;

    private String reviewTextContent;
    private Float reviewStarRating;


    public ReviewDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cancelBtn = (ImageButton)findViewById(R.id.cancel_button);
        completeBtn = (Button)findViewById(R.id.complete_button);
        starRating = (RatingBar)findViewById(R.id.review_star_rating);
        reviewText = (EditText)findViewById(R.id.review_beer_text);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewTextContent = reviewText.getText().toString();
                reviewStarRating = starRating.getRating();
                dismiss();
            }
        });

        //맥주 정보 받아서 set하는 거 필요.


    }
}
