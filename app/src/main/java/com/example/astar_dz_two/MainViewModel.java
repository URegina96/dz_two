package com.example.astar_dz_two;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainViewModel extends ViewModel { //Этот класс управляет данными, связанными с нашими пользователями, и взаимодействует с нашими компонентами пользовательского интерфейса
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>(); //   содержит список объектов User. Это будет использоваться для наблюдения за изменениями в списке пользователей и соответствующего обновления нашего пользовательского интерфейса
    private final UserDao userDao; //  отвечает за получение данных из нашей базы данных. Это передается в конструктор MainViewModel

    public MainViewModel(UserDao userDao) { //  метод reloadUsers(), который извлекает список пользователей из UserDao и устанавливает его в качестве значения нашего объекта userLiveData
        this.userDao = userDao;
        reloadUsers();
    }

    void observeUsers(LifecycleOwner owner, Observer<List<User>> observer) { //  используется для настройки наблюдателя для объектаusersLiveData. Это позволяет нам получать обновления при каждом изменении списка пользователей
        this.usersLiveData.observe(owner, observer);
    }

    public void reloadUsers() { //  снова вызывается метод reloadUsers(), на этот раз с использованием метода postValue(). Это связано с тем, что мы вносим изменения в данные в фоновом потоке, а использование postValue() гарантирует, что изменения будут правильно переданы в поток пользовательского интерфейса
        List<User> users = userDao.getUsers();
        usersLiveData.postValue(users);
    }


    static class Factory implements ViewModelProvider.Factory { //  статический внутренний класс Factory, который реализует интерфейс ViewModelProvider.Factory. Этот класс отвечает за создание экземпляров наших моделей представления

        private final UserDao userDao; //  объект UserDao, который передается в конструктор. Это будет использоваться для получения данных из нашей базы данных

        public Factory(UserDao userDao) {
            this.userDao = userDao;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            /*
            Метод create() используется для фактического создания экземпляра ViewModel. Он проверяет, может ли данный modelClass быть назначен из класса MainViewModel, и если да, то возвращает новый экземпляр MainViewModel, передавая объект userDao
            Если данный modelClass не может быть назначен из MainViewModel, создается исключение IllegalArgumentException. Это гарантирует, что этой фабрикой создаются только экземпляры MainViewModel
             */
            if (modelClass.isAssignableFrom(MainViewModel.class)) {
                return (T) new MainViewModel(userDao);
            }
            throw new IllegalArgumentException("view model not found");
        }
    }
}
