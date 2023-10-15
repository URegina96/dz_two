package com.example.astar_dz_two;

import android.content.Context;

/**
 * Простая обертка для предоставления ресурсов. Пока что просто строки предоставляет
 */
public class ResourceProviderImpl implements ResourceProvider {
    private final Context context;

    public ResourceProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public String string(int resource) {
        return context.getString(resource);
    }

    @Override
    public String string(int resource, Object... args) {
        return context.getString(resource, args);
    }
}
