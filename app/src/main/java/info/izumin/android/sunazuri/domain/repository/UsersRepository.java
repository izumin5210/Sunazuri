package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import rx.Observable;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/24/2016 AD.
 */
public interface UsersRepository {
    Observable<AuthorizedUser> getCurrentUser();
    Single<List<AuthorizedUser>> getAuthorizedUsers();
}
