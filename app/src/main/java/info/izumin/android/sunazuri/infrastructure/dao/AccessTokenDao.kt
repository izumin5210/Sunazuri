package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
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

    fun upsert(token: AccessTokenEntity) {
        orma.db.transactionSync {
            authorizedUserRelation().idEq(token.user.id).upserter().execute(token.user)
            accessTokenRelation().userEq(token.user.id).upserter().execute(token)
        }
    }

    fun insert(user: AuthorizedUserEntity) {
        if (authorizedUserRelation().selector().idEq(user.id).isEmpty) {
            authorizedUserRelation().upserter().execute(user)
        }
    }

    fun deleteAll() {
        accessTokenRelation().deleter().execute()
    }

    private fun accessTokenRelation() = orma.db.relationOfAccessTokenEntity()
    private fun authorizedUserRelation() = orma.db.relationOfAuthorizedUserEntity()
}
