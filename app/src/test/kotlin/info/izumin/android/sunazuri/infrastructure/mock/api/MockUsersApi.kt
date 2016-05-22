package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.UsersApi
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import retrofit2.mock.BehaviorDelegate
import rx.Single
import java.util.*

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockUsersApi(val delegate: BehaviorDelegate<UsersApi>) : UsersApi {

    val USER = AuthorizedUserEntity()
    val USER_ID: Long = 1

    init {
        USER.id         = USER_ID
        USER.name       = "test user"
        USER.screenName = "test_user"
        USER.createdAt  = Date()
        USER.updatedAt  = Date()
        USER.email      = "test@example.com"
        USER.icon       = "http://example.com/test_user.png"
    }

    override fun fetch(accessToken: String?): Single<AuthorizedUserEntity>? {
        return delegate.returningResponse(USER).fetch(accessToken)
    }
}