package com.example.restaurantappserver.api.service;

public interface SecurityService {
    String findLoggedInUserName();

    void autoLogin(String userName, String password);

}
