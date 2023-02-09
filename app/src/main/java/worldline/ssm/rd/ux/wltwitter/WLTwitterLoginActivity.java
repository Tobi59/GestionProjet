package worldline.ssm.rd.ux.wltwitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

public class WLTwitterLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mLoggingEditText;
    private EditText mPasswordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mLoggingEditText = (EditText) findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
        final String storedLogin = PreferenceUtils.getLogin();
        if (!TextUtils.isEmpty(storedLogin)){
            startActivity(getIntent(storedLogin));
        }
    }

    @Override
    public void onClick(View view) {
        if(TextUtils.isEmpty(mLoggingEditText.getText())) {
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }


        if (TextUtils.isEmpty(mPasswordEditText.getText())) {
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show();
            return;
        }
        PreferenceUtils.setLogin((mLoggingEditText.getText().toString()));
startActivity(getIntent(mLoggingEditText.getText().toString()));
    }
private Intent getIntent(String userName) {
    Intent intent = new Intent(this, WLTwitterActivity.class);
    final Bundle extras = new Bundle();
    extras.putString(Constants.Login.EXTRA_LOGIN, userName);
    intent.putExtras(extras);
    return intent;
}
}
