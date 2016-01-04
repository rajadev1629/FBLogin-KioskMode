package com.raja_dev.fblogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.raja_dev.fblogin.com.raja_dev.fblogin.adapter.GridAdapter;

/**
 * Created by raja_dev on 5/1/16.
 */
public class GridActivity extends Activity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(GridActivity.this, MainActivity.feedsList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GridActivity.this, ImageDetailsActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
