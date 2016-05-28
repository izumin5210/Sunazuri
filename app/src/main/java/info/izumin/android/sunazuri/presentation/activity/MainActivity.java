package info.izumin.android.sunazuri.presentation.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import info.izumin.android.sunazuri.R;
import info.izumin.android.sunazuri.Sunazuri;
import info.izumin.android.sunazuri.databinding.ActivityMainBinding;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.presentation.fragment.WelcomeFragment;
import info.izumin.android.sunazuri.presentation.fragment.posts.PostsFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    @Inject RootStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        initializeViews();
        initializeFragment(savedInstanceState);
    }

    private void inject() {
        Sunazuri.get(this).getComponent().inject(this);
    }

    private void initializeViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        final ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void initializeFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Fragment fragment = (store.getLoginInfo() == null) ? new WelcomeFragment() : new PostsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }
}
