package info.izumin.android.sunazuri.data.usecase;

import android.net.Uri;
import info.izumin.android.sunazuri.data.action.SetAccessTokenAction;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;
import info.izumin.android.sunazuri.domain.usecase.FetchTokenUseCase;
import rx.Completable;

import javax.inject.Inject;

/**
 * Created by izumin on 5/14/2016 AD.
 */
class FetchTokenUseCaseImpl implements FetchTokenUseCase {
    public static final String TAG = FetchTokenUseCaseImpl.class.getSimpleName();

    private final RootStore store;
    private final OauthRepository repo;

    @Inject
    FetchTokenUseCaseImpl(RootStore store, OauthRepository repo) {
        this.store = store;
        this.repo = repo;
    }

    @Override
    public Completable execute(String callbackUri) {
        final String code = Uri.parse(callbackUri).getQueryParameter("code");
        return repo.getToken(code)
                .map(SetAccessTokenAction::new)
                .toObservable()
                .flatMap(store::dispatch)
                .toCompletable();
    }
}
