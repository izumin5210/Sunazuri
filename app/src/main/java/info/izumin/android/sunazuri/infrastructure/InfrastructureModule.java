package info.izumin.android.sunazuri.infrastructure;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider;
import info.izumin.android.sunazuri.infrastructure.qualifier.KeyStoreAlias;
import info.izumin.android.sunazuri.infrastructure.util.Encryptor;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Module
public class InfrastructureModule {
    public static final String TAG = InfrastructureModule.class.getSimpleName();

    private final Context context;
    private final String keyStoreAlias;

    public InfrastructureModule(Context context, String keyStoreAlias) {
        this.context = context;
        this.keyStoreAlias = keyStoreAlias;
    }

    @Provides
    @Singleton
    Context context() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    @KeyStoreAlias
    String keyStoreAlias() {
        return keyStoreAlias;
    }

    @Provides
    @Singleton
    OrmaProvider ormaProvider(Context context) {
        return new OrmaProvider(context);
    }

    @Provides
    Encryptor encryptor(Context context, @KeyStoreAlias String keyStoreAlias) {
        return new Encryptor(context, keyStoreAlias);

    }
}
