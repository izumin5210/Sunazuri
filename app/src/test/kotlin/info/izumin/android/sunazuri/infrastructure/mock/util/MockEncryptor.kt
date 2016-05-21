package info.izumin.android.sunazuri.infrastructure.mock.util

import android.content.Context
import android.util.Base64
import info.izumin.android.sunazuri.infrastructure.util.Encryptor
import java.nio.charset.Charset

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockEncryptor(context: Context) : Encryptor(context, "testkeystore") {
    override fun encrypt(plainText: String) : String {
        return Base64.encodeToString(plainText.toByteArray(Charset.defaultCharset()), Base64.DEFAULT)
    }

    override fun decrypt(encryptedText: String): String {
        return String(Base64.decode(encryptedText.toByteArray(Charset.defaultCharset()), Base64.DEFAULT))
    }
}
