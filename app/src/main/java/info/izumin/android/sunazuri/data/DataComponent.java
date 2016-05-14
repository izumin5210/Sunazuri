package info.izumin.android.sunazuri.data;

import dagger.Component;
import info.izumin.android.sunazuri.data.usecase.UseCaseModule;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.domain.usecase.FetchTeamsUseCase;
import info.izumin.android.sunazuri.infrastructure.InfrastructureComponent;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@DataScope
@Component(
        dependencies = {
                InfrastructureComponent.class
        },
        modules = {
                DataModule.class,
                UseCaseModule.class
        }
)
public interface DataComponent {
    FetchTeamsUseCase fetchTeamsUseCase();
}
