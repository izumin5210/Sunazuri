package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.OauthApi
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import retrofit2.mock.BehaviorDelegate
import rx.Completable
import rx.Single

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockOauthApi(val delegate: BehaviorDelegate<OauthApi>) : OauthApi {
    val TOKEN = AccessTokenEntity()

    init {
        TOKEN.accessToken   = "testtoken"
        TOKEN.tokenType     = "bearer"
        TOKEN.scope         = "read+write"
    }

    override fun fetchToken(clientId: String?, clientSecret: String?, grantType: String?, redirectUri: String?, code: String?): Single<AccessTokenEntity>? {
        return delegate.returningResponse(TOKEN).fetchToken(clientId, clientSecret, grantType, redirectUri, code)
    }

    override fun revoke(authorization: String?): Completable? {
        throw UnsupportedOperationException()
    }
}