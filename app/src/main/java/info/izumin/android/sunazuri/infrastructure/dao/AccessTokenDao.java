package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity_Relation;
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase;
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

    public Observable<List<AccessTokenEntity>> findAll() {
        return accessTokenRelation().selector().executeAsObservable().toList();
    }

    public Single<AccessTokenEntity> insert(AccessTokenEntity token) {
        return accessTokenRelation().inserter()
                .executeAsObservable(token)
                .map(_l -> token);
    }

    public Completable deleteAll() {
        return accessTokenRelation().deleter().executeAsObservable().toCompletable();
    }

    private AccessTokenEntity_Relation accessTokenRelation() {
        return orma.relationOfAccessTokenEntity();
    }
}
