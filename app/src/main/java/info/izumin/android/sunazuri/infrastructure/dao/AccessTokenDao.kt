package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Singleton
class AccessTokenDao @Inject constructor(val orma: OrmaProvider) {
    fun findAll(): Observable<MutableList<AccessTokenEntity>> {
        return accessTokenRelation().selector().executeAsObservable().toList()
    }

    fun insert(token: AccessTokenEntity) {
        accessTokenRelation().inserter().execute(token)
    }

    fun deleteAll() {
        accessTokenRelation().deleter().execute()
    }

    private fun accessTokenRelation() = orma.db.relationOfAccessTokenEntity()
}
