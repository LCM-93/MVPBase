package com.chaman.app.dagger.component;

import com.chaman.android.dagger.scope.ActivityScope;
import com.chaman.app.main.MainActivity;

import dagger.Component;

/**
 * ****************************************************************
 * Author: Chaman
 * Date: 2017/5/23 下午1:59
 * Desc:
 * *****************************************************************
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface RepoComponent {

    void inject(MainActivity mainActivity);
}
