package worldline.ssm.rd.ux.wltwitter.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.executor.RetriveTweetsExecutor;
import worldline.ssm.rd.ux.wltwitter.fragments.MyTweetRecyclerViewAdapter;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

/**
 * A fragment representing a list of Items.
 */
public class TweetFragment extends Fragment implements TweetChangeListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TweetFragment() {
    }

    private RetriveTweetsExecutor mExecutor;

    @Override
    public void onStart(){
        super.onStart();
        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)){
            mExecutor = new RetriveTweetsExecutor(this);
            mExecutor.getTweets(login);

        }
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TweetFragment newInstance(int columnCount) {
        TweetFragment fragment = new TweetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView  = (RecyclerView) view;
            mRecyclerView.setAdapter(new MyTweetRecyclerViewAdapter(new ArrayList<Tweet>()));
        }
        return view;
    }
    @Override
    public void onTweetRetrieved(List<Tweet> tweets){
        mRecyclerView.setAdapter((new MyTweetRecyclerViewAdapter(tweets)));
        //for(Tweet t : tweets){ Log.d(this.getClass().toString(),t.text);}
    }

}