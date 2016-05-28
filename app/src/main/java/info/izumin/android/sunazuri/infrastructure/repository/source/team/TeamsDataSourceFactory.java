package info.izumin.android.sunazuri.infrastructure.repository.source.team;

/**
 * Created by izumin on 5/28/2016 AD.
 */
public interface TeamsDataSourceFactory {
    TeamsDataSource createLocalDataSource();
    TeamsDataSource createRemoteDataSource();
}
