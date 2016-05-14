package info.izumin.android.sunazuri.data.usecase;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.domain.usecase.FetchTeamsUseCase;
import info.izumin.android.sunazuri.domain.usecase.FetchTokenUseCase;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Module
public class UseCaseModule {
    public static final String TAG = UseCaseModule.class.getSimpleName();

    @Provides
    FetchTeamsUseCase fetchTeamsUseCase(FetchTeamsUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    FetchTokenUseCase fetchTokenUseCase(FetchTokenUseCaseImpl useCase) {
        return useCase;
    }
}
