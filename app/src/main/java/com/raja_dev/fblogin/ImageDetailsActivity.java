package com.raja_dev.fblogin;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.raja_dev.fblogin.com.raja_dev.fblogin.adapter.FeedItem;
import com.squareup.picasso.Picasso;

/**
 * Created by raja_dev on 5/1/16.
 */
public class ImageDetailsActivity extends Activity implements View.OnClickListener{

    ImageView imgPhoto, imgPrev, imgNext;
    TextView txtDetails;
    int size, postion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        imgPrev = (ImageView) findViewById(R.id.imgPrev);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        txtDetails = (TextView) findViewById(R.id.txtDetails);

        imgNext.setOnClickListener(this);
        imgPrev.setOnClickListener(this);

        postion = getIntent().getExtras().getInt("position");
        size = MainActivity.feedsList.size();
        updateView(postion);


    }

    private void updateView(int position){
        if(position == (size-1)){
            imgPrev.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.GONE);
        } else if (position == 0){
            imgPrev.setVisibility(View.GONE);
            imgNext.setVisibility(View.VISIBLE);
        } else{
            imgPrev.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.VISIBLE);
        }

        FeedItem feedItem = MainActivity.feedsList.get(position);

        Picasso.with(this).load(feedItem.getThumbnail())
                .error(R.drawable.com_facebook_tooltip_blue_background)
                .placeholder(R.drawable.com_facebook_tooltip_blue_background)
                .into(imgPhoto);
        txtDetails.setText(Html.fromHtml(feedItem.getTitle()));

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgPrev){
            if (postion > 0){
                updateView(--postion);
            }
        } else if(v.getId() == R.id.imgNext){
            if (postion <= (size-1)){
                updateView(++postion);
            }

        }

    }
}
