package info.izumin.android.sunazuri.data;

import dagger.Component;
import info.izumin.android.sunazuri.data.action.ActionModule;
import info.izumin.android.sunazuri.data.action.user.UserActionCreator;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.infrastructure.InfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@DataScope
@Component(
        dependencies = {
                InfrastructureComponent.class
        },
        modules = {
                ActionModule.class,
                DataModule.class
        }
)
public interface DataComponent {
    RootStore rootStore();
    OauthParams oauthParams();

    UserActionCreator userActionCreator();
}
