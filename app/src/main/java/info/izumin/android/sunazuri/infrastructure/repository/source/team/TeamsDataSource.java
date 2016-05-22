package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsDataSource {
    Single<List<TeamStatsEntity>> getTeams(AccessTokenEntity entity);
}
