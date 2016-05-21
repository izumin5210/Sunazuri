package info.izumin.android.sunazuri.infrastructure;

import android.content.Context;
import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.keychain.KeyChain;
import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider;
import info.izumin.android.sunazuri.infrastructure.qualifier.KeyStoreAlias;

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
    @Singleton
    KeyChain keyChain(Context context) {
        return new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
    }

    @Provides
    @Singleton
    Crypto crypto(KeyChain keyChain) {
        return AndroidConceal.get().createDefaultCrypto(keyChain);
    }
}
