package worldline.ssm.rd.ux.wltwitter.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;



import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private final List<Tweet> mValues;

    public MyTweetRecyclerViewAdapter(List<Tweet> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list_tweet, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tweet tweet = (Tweet) mValues.get(position);
        holder.mItem = tweet;
        holder.name.setText(tweet.user.name);
        holder.alias.setText(tweet.user.screenName);
        holder.text.setText(tweet.text);
        holder.button.setTag(position);
        if(!tweet.user.profileImageUrl.isEmpty()){
            Picasso.get().load(tweet.user.profileImageUrl).into(holder.image);
        }



        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView alias;
        public final TextView text;
        public final ImageView image;
        public final Button button;
        public Tweet mItem;

        public ViewHolder(View binding) {
            super(binding);
            image = (ImageView) itemView.findViewById(R.id.tweetListImageView);
            name = (TextView) itemView.findViewById(R.id.tweetListNameTextView);
            alias = (TextView) itemView.findViewById(R.id.tweetListAliasTextView);
            text = (TextView) itemView.findViewById(R.id.tweetListTweetTextTextView);
            button = (Button) itemView.findViewById(R.id.tweetListRTButton);

        }

        @Override
        public String toString() {
            return super.toString() + text.getText();
        }
    }
}