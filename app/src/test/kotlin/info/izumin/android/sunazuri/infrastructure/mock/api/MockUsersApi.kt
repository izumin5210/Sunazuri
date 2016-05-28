package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.UsersApi
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import rx.Single

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockUsersApi(val delegate: BehaviorDelegate<UsersApi>) : UsersApi {

    companion object {
        fun getInstance(mockRetrofit: MockRetrofit): MockUsersApi {
            return MockUsersApi(mockRetrofit.create(UsersApi::class.java))
        }
    }

    var user = DataFactory.createAuthorizedUserEntity()
        get set

    override fun fetch(accessToken: String?): Single<AuthorizedUserEntity>? {
        return delegate.returningResponse(user).fetch(accessToken)
    }
}