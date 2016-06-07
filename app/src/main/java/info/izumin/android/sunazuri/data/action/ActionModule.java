package info.izumin.android.sunazuri.data.action;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.data.action.team.TeamActionCreator;
import info.izumin.android.sunazuri.data.action.user.UserActionCreator;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Module
public class ActionModule {
    public static final String TAG = ActionModule.class.getSimpleName();

    @Provides
    UserActionCreator userActionCreator(OauthRepository oauthRepo,
                                        UsersRepository usersRepo,
                                        TeamActionCreator teamActionCreator) {
        return new UserActionCreator(oauthRepo, usersRepo, teamActionCreator);
    }

    @Provides
    TeamActionCreator teamActionCreator(TeamsRepository teamsRepo) {
        return new TeamActionCreator(teamsRepo);
    }
}
