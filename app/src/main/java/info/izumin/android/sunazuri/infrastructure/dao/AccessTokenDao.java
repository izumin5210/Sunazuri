package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.domain.entity.AccessToken;
import info.izumin.android.sunazuri.domain.entity.AccessToken_Relation;
import info.izumin.android.sunazuri.domain.entity.OrmaDatabase;
import rx.Completable;
import rx.Observable;
import rx.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Singleton
public class AccessTokenDao {
    public static final String TAG = AccessTokenDao.class.getSimpleName();

    private final OrmaDatabase orma;

    @Inject
    public AccessTokenDao(OrmaDatabase orma) {
        this.orma = orma;
    }

    public Observable<List<AccessToken>> findAll() {
        return accessTokenRelation().selector().executeAsObservable().toList();
    }

    public Single<AccessToken> insert(AccessToken token) {
        return accessTokenRelation().inserter()
                .executeAsObservable(token)
                .map(_l -> token);
    }

    public Completable deleteAll() {
        return accessTokenRelation().deleter().executeAsObservable().toCompletable();
    }

    private AccessToken_Relation accessTokenRelation() {
        return orma.relationOfAccessToken();
    }
}
