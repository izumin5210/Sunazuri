package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.domain.entity.AccessToken
import info.izumin.android.sunazuri.domain.entity.OrmaDatabase
import rx.Completable
import rx.Observable
import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Singleton
class AccessTokenDao @Inject constructor(val orma: OrmaDatabase) {
    companion object {
        val TAG = AccessTokenDao::class.simpleName
    }

    fun findAll(): Observable<MutableList<AccessToken>> {
        return accessTokenRelation().selector().executeAsObservable().toList()
    }

    fun insert(token: AccessToken): Single<AccessToken> {
        return accessTokenRelation().inserter()
                .executeAsObservable(token)
                .map({_l -> token});
    }

    fun deleteAll(): Completable {
        return accessTokenRelation().deleter().executeAsObservable().toCompletable()
    }

    private fun accessTokenRelation() = orma.relationOfAccessToken()
}
