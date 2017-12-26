package com.rxmuhammadyoussef.twitterc.models.user;

/**
 This class represents the user view model object, a view-layer-ready version of {@link User}
 */

public class UserViewModel {

    private final long userId;
    private final String fulName;
    private final String userName;
    private final String bio;
    private final String imageUrl;

    UserViewModel(long userId, String fulName, String userName, String bio, String imageUrl) {
        this.userId = userId;
        this.fulName = fulName;
        this.userName = userName;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    public long getUserId() {
        return userId;
    }

    public String getFulName() {
        return fulName;
    }

    public String getUserName() {
        return "@".concat(userName);
    }

    public String getBio() {
        return bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
