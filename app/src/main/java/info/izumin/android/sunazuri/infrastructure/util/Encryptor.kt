package info.izumin.android.sunazuri.infrastructure.util;

import android.content.Context
import android.support.annotation.VisibleForTesting
import com.facebook.android.crypto.keychain.AndroidConceal
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain
import com.facebook.crypto.CryptoConfig
import info.izumin.android.sunazuri.infrastructure.util.EncryptionUtils.decrypt
import info.izumin.android.sunazuri.infrastructure.util.EncryptionUtils.encrypt

/**
 * Created by izumin on 5/21/2016 AD.
 */
@VisibleForTesting
open class Encryptor(val context: Context, val alias: String) {

    @VisibleForTesting
    open fun encrypt(plainText: String) : String {
        return encrypt(createCrypto(), alias, plainText)
    }

    @VisibleForTesting
    open fun decrypt(encryptedText: String) : String {
        return decrypt(createCrypto(), alias, encryptedText)
    }

    private fun createCrypto() = AndroidConceal.get().createDefaultCrypto(createKeyChain())
    private fun createKeyChain() = SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256)
}
