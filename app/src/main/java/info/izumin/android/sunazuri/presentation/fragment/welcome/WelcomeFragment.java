package info.izumin.android.sunazuri.presentation.fragment.welcome;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.izumin.android.sunazuri.R;
import info.izumin.android.sunazuri.RequestCode;
import info.izumin.android.sunazuri.databinding.FragmentWelcomeBinding;
import info.izumin.android.sunazuri.presentation.activity.main.MainContract;
import info.izumin.android.sunazuri.presentation.activity.oauth.OauthActivity;
import info.izumin.android.sunazuri.presentation.fragment.posts.PostsFragment;
import onactivityresult.ActivityResult;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment implements WelcomeContract.View {
    public static final String TAG = WelcomeFragment.class.getSimpleName();

    public WelcomeFragment() {
        // Required empty public constructor
    }

    private FragmentWelcomeBinding binding;
    private WelcomeComponent component;
    private MainContract.View mainView;

    @Inject WelcomeContract.Controller controller;
    @Inject WelcomeContract.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainContract.View) {
            mainView = (MainContract.View) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupComponent();
        binding = FragmentWelcomeBinding.bind(view);
        binding.setController(controller);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(controller);
    }

    @Override
    public void showOauthUi() {
        startActivityForResult(new Intent(getActivity(), OauthActivity.class), RequestCode.OAUTH);
    }

    @Override
    public void showPostsUi() {
        mainView.setFragment(new PostsFragment());
    }

    private void setupComponent() {
        component = mainView.getMainComponent().plus(new WelcomeModule(this));
        component.inject(this);
    }
}
