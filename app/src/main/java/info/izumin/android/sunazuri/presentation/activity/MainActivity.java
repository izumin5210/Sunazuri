package info.izumin.android.sunazuri.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import info.izumin.android.sunazuri.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
