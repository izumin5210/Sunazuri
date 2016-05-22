package info.izumin.android.sunazuri.data.action.common;

import info.izumin.android.droidux.Action;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public abstract class BaseAction<R extends BaseAction.RequestValue> implements Action {
    public static final String TAG = BaseAction.class.getSimpleName();

    private R value;

    public R getValue() {
        return value;
    }

    public void setValue(R value) {
        this.value = value;
    }

    public interface RequestValue {
    }
}
