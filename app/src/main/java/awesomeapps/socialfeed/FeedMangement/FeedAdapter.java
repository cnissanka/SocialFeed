package awesomeapps.socialfeed.FeedMangement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import awesomeapps.socialfeed.R;

/**
 * Created by codeguy on 2/14/16.
 */
public class FeedAdapter extends BaseAdapter{
    Context context;

    protected ArrayList<FeedEntity> feedArray;
    LayoutInflater inflater;

    public FeedAdapter(Context context, ArrayList<FeedEntity> feedList){
        this.feedArray = feedList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return feedArray.size();
    }

    @Override
    public Object getItem(int position) {
        return feedArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.feed_layout, parent, false);

        TextView tv_username = (TextView) row.findViewById(R.id.tv_postUsername);
        TextView tv_summary = (TextView) row.findViewById(R.id.tv_postSummary);

        FeedEntity feed = feedArray.get(position);
        tv_username.setText(feed.getUsername());
        tv_summary.setText(feed.getFeedSummary());
       return null;
    }
}
