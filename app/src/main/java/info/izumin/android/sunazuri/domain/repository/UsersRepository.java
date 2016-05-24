package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/24/2016 AD.
 */
public interface UsersRepository {
    boolean exists();
    Single<AuthorizedUser> getCurrentUser();
    Single<List<AuthorizedUser>> getAuthorizedUsers();
}
