package com.example.astar_dz_two;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private final UserDao userDao;

    public MainViewModel(UserDao userDao) {
        this.userDao = userDao;
        reloadUsers();
    }

    void observeUsers(LifecycleOwner owner, Observer<List<User>> observer) {
        this.usersLiveData.observe(owner, observer);
    }

    public void reloadUsers() {
        List<User> users = userDao.getUsers();
        usersLiveData.postValue(users);
    }


    static class Factory implements ViewModelProvider.Factory {

        private final UserDao userDao;

        public Factory(UserDao userDao) {
            this.userDao = userDao;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MainViewModel.class)) {
                return (T) new MainViewModel(userDao);
            }
            throw new IllegalArgumentException("view model not found");
        }
    }
}
