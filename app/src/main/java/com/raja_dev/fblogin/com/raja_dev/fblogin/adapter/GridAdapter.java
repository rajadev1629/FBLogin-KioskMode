package com.raja_dev.fblogin.com.raja_dev.fblogin.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.raja_dev.fblogin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends BaseAdapter {

	Context context;
	private List<FeedItem> feedItemList;
	LayoutInflater inflater;
	int size;

	public GridAdapter(Context context, List<FeedItem> feedItemList) {
		this.context = context;
		this.feedItemList = feedItemList;
		this.size = feedItemList.size();
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return size;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.grid_view, parent, false);
				holder = new viewHolder();
				holder.imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
				holder.txtHeading = (TextView) convertView.findViewById(R.id.txtHeading);
				convertView.setTag(holder);

			} else {
				holder = (viewHolder) convertView.getTag();
			}

			FeedItem feedItem = feedItemList.get(position);
			Picasso.with(context).load(feedItem.getThumbnail())
					.error(R.drawable.com_facebook_tooltip_blue_background)
					.placeholder(R.drawable.com_facebook_tooltip_blue_background)
					.into(holder.imgPhoto);
			holder.txtHeading.setText(Html.fromHtml(feedItem.getTitle()));
		return convertView;
	}

	private static class viewHolder {
		ImageView imgPhoto;
		TextView txtHeading;
	}

}
