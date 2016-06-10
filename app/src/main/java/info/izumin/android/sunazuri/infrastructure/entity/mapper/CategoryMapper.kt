package info.izumin.android.sunazuri.infrastructure.entity.mapper

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import info.izumin.android.sunazuri.infrastructure.entity.CategoryEntity
import rx.Observable

/**
 * Created by izumin on 6/10/2016 AD.
 */
class CategoryMapper {
    fun transform(res: List<TeamsApi.Category>): List<CategoryEntity> {
        return Observable.from(res)
                .flatMap { transform(null, it) }
                .toList().toBlocking().first()
    }

    fun transform(parent: CategoryEntity?, res: TeamsApi.Category): Observable<CategoryEntity> {
        val entity = CategoryEntity()
        entity.count = res.count
        if (parent != null) {
            entity.path = "${parent.path}/${res.name}"
        } else {
            entity.path = res.name
        }

        val entities = arrayListOf(entity)

        if (res.children != null) {
            entities.addAll(
                    Observable.from(res.children)
                            .flatMap { transform(entity, it) }
                            .toList().toBlocking().first()
            )
        }

        return Observable.from(entities)
    }
}
