package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
internal class TeamsRemoteDataSource @Inject constructor(
        val teamsApi: TeamsApi
) : TeamsDataSource {
}
