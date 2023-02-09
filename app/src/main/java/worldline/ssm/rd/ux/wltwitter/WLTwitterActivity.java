package worldline.ssm.rd.ux.wltwitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.fragment.TweetFragment;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WLTwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extra = intent.getExtras();
            if(null!= extra){
                final String login = extra.getString((Constants.Login.EXTRA_LOGIN));
                getSupportActionBar().setSubtitle(login);
                getSupportFragmentManager().beginTransaction().add(R.id.container, new TweetFragment()).commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            PreferenceUtils.setLogin(null);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}



