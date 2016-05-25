package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.pref.PrefsProvider
import rx.Observable
import rx.Single

/**
 * Created by izumin on 5/24/2016 AD.
 */
internal class OauthLocalDataSource(
        val dao: AccessTokenDao,
        val prefs: PrefsProvider
) : OauthDataSource {
    override fun getCurrentToken(): Observable<AccessTokenEntity> {
        if (prefs.defaultPrefs.hasUserId()) {
            return dao.find(prefs.defaultPrefs.userId)
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
