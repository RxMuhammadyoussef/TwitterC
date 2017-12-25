package com.rxmuhammadyoussef.twitterc.di.activity;

/**
 This interface is used by dagger to generate the code that defines the connection between the provider of objects
 (i.e. {@link com.rxmuhammadyoussef.twitterc.di.activity.ActivityModule}), and the object which expresses a dependency.
 */

import com.rxmuhammadyoussef.twitterc.ui.login.LoginActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);
}
