package info.izumin.android.sunazuri.presentation.fragment;


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
import info.izumin.android.sunazuri.presentation.activity.oauth.OauthActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    public static final String TAG = WelcomeFragment.class.getSimpleName();

    public interface Handler {
        void onBtnLoginClick(View view);
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }

    private FragmentWelcomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWelcomeBinding.bind(view);
        binding.setHandler(handler);
    }

    private final Handler handler = new Handler() {
        @Override
        public void onBtnLoginClick(View view) {
            startActivityForResult(new Intent(getActivity(), OauthActivity.class), RequestCode.OAUTH.code);
        }
    };
}
