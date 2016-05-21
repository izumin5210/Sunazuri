package info.izumin.android.sunazuri.presentation.activity.oauth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import info.izumin.android.sunazuri.R;
import info.izumin.android.sunazuri.Sunazuri;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.domain.usecase.FetchTokenUseCase;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

public class OauthActivity extends AppCompatActivity {
    public static final String TAG = OauthActivity.class.getSimpleName();

    @Inject RootStore store;
    @Inject OauthParams oauthParams;
    @Inject FetchTokenUseCase fetchTokenUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        inject();

        if (store.getAccessToken() == null) {
            CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
            intent.launchUrl(this, Uri.parse(oauthParams.getAuthorizeUri()));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        inject();
        fetchTokenUseCase.execute(intent.getDataString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::returnToMain);
    }

    private void returnToMain() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void inject() {
        Sunazuri.get(this).getComponent().inject(this);
    }
}
