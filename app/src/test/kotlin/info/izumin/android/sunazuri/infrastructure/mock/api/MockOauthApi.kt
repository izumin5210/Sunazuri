package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.OauthApi
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import rx.Completable
import rx.Single

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockOauthApi(val delegate: BehaviorDelegate<OauthApi>) : OauthApi {

    companion object {
        fun getInstance(mockRetrofit: MockRetrofit): MockOauthApi {
            return MockOauthApi(mockRetrofit.create(OauthApi::class.java))
        }
    }

    var token = DataFactory.createAccessTokenEntity()
        get set

    override fun fetchToken(clientId: String?, clientSecret: String?, grantType: String?, redirectUri: String?, code: String?): Single<AccessTokenEntity>? {
        return delegate.returningResponse(token).fetchToken(clientId, clientSecret, grantType, redirectUri, code)
    }

    override fun revoke(authorization: String?): Completable? {
        throw UnsupportedOperationException()
    }
}