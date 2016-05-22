package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.domain.entity.Team;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface TeamsRepository {
    Single<List<Team>> get();
    Single<Team> get(String teamName);
}
