package info.izumin.android.sunazuri.domain.usecase;

import rx.Completable;

/**
 * Created by izumin on 5/14/2016 AD.
 */
public interface FetchTokenUseCase {
    Completable execute(String callbackUri);
}
