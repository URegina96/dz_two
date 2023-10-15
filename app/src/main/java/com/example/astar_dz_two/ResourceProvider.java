package com.example.astar_dz_two;

import androidx.annotation.StringRes;

public interface ResourceProvider {
    String string(@StringRes int resource);
    String string(@StringRes int resource, Object... args);
}
