package worldline.ssm.rd.ux.wltwitter.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import worldline.ssm.rd.ux.wltwitter.fragment.TweetFragment;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

public class RetriveTweetsExecutor {

    private ExecutorService mExecutorService;
    private Handler mHandler;
    private TweetChangeListener mListener;

    public RetriveTweetsExecutor(TweetFragment listener){
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mListener = listener;
    }

    public void getTweets(String User){
        mExecutorService.execute( () -> {
        List<Tweet> tweets = TwitterHelper.getTweetsOfUser(User);
        if(null!= tweets){
            mHandler.post(()-> {
                mListener.onTweetRetrieved(tweets);

            });
        }
        });

    }

}
