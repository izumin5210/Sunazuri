package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.cache.LoginCache
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import rx.Observable
import rx.Single

/**
 * Created by izumin on 5/24/2016 AD.
 */
internal class OauthLocalDataSource(
        val dao: AccessTokenDao,
        val loginCache: LoginCache
) : OauthDataSource {
    override fun getCurrentToken(): Observable<AccessTokenEntity> {
        if (loginCache.isUserCached) {
            return dao.find(loginCache.userId)
        } else {
            return Observable.empty()
        }
    }

    override fun getTokens(): Single<MutableList<AccessTokenEntity>> {
        return dao.findAll().toSingle()
    }

    override fun getToken(code: String): Single<AccessTokenEntity> {
        throw UnsupportedOperationException()
    }

}
