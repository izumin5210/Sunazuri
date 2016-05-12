package info.izumin.android.sunazuri.infrastructure.api;

import info.izumin.android.sunazuri.domain.entity.Team;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsApi {
    @GET("/v1/teams")
    Observable<List<Team>> fetch();

    @GET("/v1/teams/{teamName}")
    Observable<Team> fetch(
            @Path("teamName") String teamName
    );

    @GET("v1/teams/{teamName}/stats")
    Observable<Team> stats(
            @Path("teamName") String teamName
    );
}
