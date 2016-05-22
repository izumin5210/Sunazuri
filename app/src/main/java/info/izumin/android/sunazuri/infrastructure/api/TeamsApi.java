package info.izumin.android.sunazuri.infrastructure.api;

import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity;
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsApi {
    @GET("/v1/teams")
    Single<List<TeamEntity>> fetch(
            @Query("access_token") String accessToken
    );

    @GET("/v1/teams/{team_name}")
    Single<TeamEntity> fetch(
            @Path("team_name") String teamName,
            @Query("access_token") String accessToken
    );

    @GET("/v1/teams/{team_name}/stats")
    Single<TeamStatsEntity> stats(
            @Path("team_name") String teamName,
            @Query("access_token") String accessToken
    );
}
