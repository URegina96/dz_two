package com.example.astar_dz_two;
import android.content.Context;
public class ResourceProviderImpl implements ResourceProvider { // шаг 30 - В этом коде реализуется класс ResourceProviderImpl, который позволит получить доступ к строковым ресурсам в Android-приложении.
    // В качестве параметра он принимает объект Context, который обеспечивает доступ к ресурсам приложения. Затем методы string() переопределяются для получения и форматирования строковых значений с использованием объекта Context.
    // Это обеспечивает простой и эффективный доступ к строковым ресурсам для использования в пользовательском интерфейсе приложения
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
