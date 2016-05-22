package info.izumin.android.sunazuri.infrastructure.api;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import rx.Observable;

import java.io.IOException;
import java.util.List;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class EnvelopeTypeAdapterFactory implements TypeAdapterFactory {
    public static final String TAG = EnvelopeTypeAdapterFactory.class.getSimpleName();

    private final List<String> envelopeKeys;

    public EnvelopeTypeAdapterFactory(List<String> envelopeKeys) {
        this.envelopeKeys = envelopeKeys;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> adapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return delegate.fromJsonTree(getElement(adapter.read(in)));
            }
        };
    }

    private JsonElement getElement(JsonElement element) {
        if (!element.isJsonObject()) {
            return element;
        }

        JsonObject json = element.getAsJsonObject();

        return Observable.from(envelopeKeys)
                .filter(json::has)
                .map(json::get)
                .filter(JsonElement::isJsonArray)
                .defaultIfEmpty(element)
                .toBlocking().first();
    }
}
