package info.izumin.android.sunazuri.infrastructure.api;

import com.github.gfx.static_gson.annotation.JsonSerializable;
import com.google.gson.FieldNamingPolicy;
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity;
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
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

    @GET("/v1/teams/{team_name}/categories")
    Observable<Category> categories(
            @Path("team_name") String teamName,
            @Query("access_token") String accessToken
    );

    @JsonSerializable(fieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    class Category {
        public String name;
        public int count;
        public List<Category> children;
    }
}
