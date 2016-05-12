package info.izumin.android.sunazuri.data.usecase;

import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.domain.usecase.FetchTeamsUseCase;
import rx.Completable;

import javax.inject.Inject;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class FetchTeamsUseCaseImpl implements FetchTeamsUseCase {
    public static final String TAG = FetchTeamsUseCaseImpl.class.getSimpleName();

    private final TeamsRepository teamsRepo;

    @Inject
    FetchTeamsUseCaseImpl(TeamsRepository teamsRepo) {
        this.teamsRepo = teamsRepo;
    }

    @Override
    public Completable execute() {
        return null;
    }
}
