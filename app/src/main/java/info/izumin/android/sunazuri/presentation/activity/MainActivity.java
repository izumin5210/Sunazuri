package info.izumin.android.sunazuri.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import info.izumin.android.sunazuri.R;
import info.izumin.android.sunazuri.presentation.activity.oauth.OAuthActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, OAuthActivity.class));
    }
}
