package info.izumin.android.sunazuri.infrastructure.entity.mapper

import info.izumin.android.sunazuri.domain.entity.AccessToken
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Singleton
class AuthorizedUserMapper @Inject constructor(
        val teamMapper: TeamMapper
) {
    fun transform(entity: AccessTokenEntity?): AuthorizedUser? {
        var user: AuthorizedUser? = null
        if (entity!= null && entity.user != null) {
            user = AuthorizedUser(
                    entity.user.id,
                    entity.user.name,
                    entity.user.screenName,
                    entity.user.createdAt,
                    entity.user.updatedAt,
                    entity.user.icon,
                    entity.user.email,
                    transformAccessToken(entity)
            )
        }
        return user
    }

    private fun transformAccessToken(entity: AccessTokenEntity): AccessToken {
        return AccessToken(
                entity.accessToken,
                entity.tokenType,
                entity.scope
        )
    }
}