package info.izumin.android.sunazuri.infrastructure.dao;

import android.content.Context
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase

/**
 * Created by izumin on 5/21/2016 AD.
 */
class OrmaProvider {
    lateinit var db: OrmaDatabase

    constructor(context: Context) {
        db = OrmaDatabase.builder(context).build()
    }

    constructor(context: Context, name: String?) {
        db = OrmaDatabase.builder(context).name(name).build()
    }
}
