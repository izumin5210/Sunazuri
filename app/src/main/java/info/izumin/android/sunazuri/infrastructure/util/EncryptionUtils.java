package info.izumin.android.sunazuri.infrastructure.util;

import android.util.Base64;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;

import java.io.IOException;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public final class EncryptionUtils {
    private EncryptionUtils() {
        throw new AssertionError("constructor of the utility class should not be called");
    }

    private static final String ENCODING = "UTF-8";
    private static final int BASE64_FLAG = Base64.DEFAULT;

    public static String encrypt(Crypto crypto, String alias, String plainText)
            throws IOException, KeyChainException, CryptoInitializationException {
        final byte[] bytes = crypto.encrypt(plainText.getBytes(ENCODING), Entity.create(alias));
        return Base64.encodeToString(bytes, BASE64_FLAG);
    }

    public static String decrypt(Crypto crypto, String alias, String encryptedText)
            throws IOException, KeyChainException, CryptoInitializationException {
        final byte[] bytes = crypto.decrypt(Base64.decode(encryptedText, BASE64_FLAG), Entity.create(alias));
        return new String(bytes, ENCODING);
    }
}
