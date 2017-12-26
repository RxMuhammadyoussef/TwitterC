package com.rxmuhammadyoussef.twitterc.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rxmuhammadyoussef.twitterc.R;
import com.rxmuhammadyoussef.twitterc.TwitterCApplication;
import com.rxmuhammadyoussef.twitterc.di.activity.ActivityModule;
import com.rxmuhammadyoussef.twitterc.di.activity.ActivityScope;
import com.rxmuhammadyoussef.twitterc.models.user.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 This class represents the view layer of the Home page which handles all the UI interactions
 */

@ActivityScope
public class HomeActivity extends Activity implements HomeScreen {

    @BindView(R.id.refresh_layout_home)
    SwipeRefreshLayout homeLayout;
    @BindView(R.id.rv_followers)
    RecyclerView followersRecyclerView;

    @Inject
    RecyclerAdapter adapter;
    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TwitterCApplication.getComponent(this)
                .plus(new ActivityModule(this))
                .inject(this);
        ButterKnife.bind(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(followersRecyclerView.getContext(), layoutManager.getOrientation());
        followersRecyclerView.setLayoutManager(layoutManager);
        followersRecyclerView.addItemDecoration(divider);
        followersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setupRefreshListener() {
        homeLayout.setOnRefreshListener(() -> presenter.fetchFollowers());
    }

    @Override
    public void showLoadingAnimation() {
        homeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingAnimation() {
        homeLayout.setRefreshing(false);
    }

    @Override
    public void updateFollowers(List<UserViewModel> userViewModels) {
        adapter.update(userViewModels);
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logout() {
        finish();
    }

    public void onLogoutClick(MenuItem item) {
        presenter.onLogoutClick();
    }
}
