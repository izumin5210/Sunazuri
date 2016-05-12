package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.domain.entity.Team;
import info.izumin.android.sunazuri.domain.entity.TeamStats;
import rx.Observable;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsRepository {
    Observable<List<Team>> get();
    Observable<Team> get(String teamName);
    Observable<TeamStats> getStats(String teamName);
}
