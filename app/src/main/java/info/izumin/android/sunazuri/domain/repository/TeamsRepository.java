package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity;
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity;
import rx.Observable;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsRepository {
    Observable<List<TeamEntity>> get();
    Observable<TeamEntity> get(String teamName);
    Observable<TeamStatsEntity> getStats(String teamName);
}
