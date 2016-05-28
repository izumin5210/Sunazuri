package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

/**
 * Created by izumin on 5/28/2016 AD.
 */
public interface OauthDataSourceFactory {
    OauthDataSource createLocalDataSource();
    OauthDataSource createRemoteDataSource();
}
