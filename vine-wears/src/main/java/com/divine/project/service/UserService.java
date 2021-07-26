package com.divine.project.service;

import com.divine.project.model.user.User;
import com.divine.project.payload.UpdateUserRequest;
import com.divine.project.payload.UserChangePasswordRequest;
import com.divine.project.payload.UserForgotPasswordRequest;

import java.text.ParseException;

public interface UserService {
    User editUser(User user, UpdateUserRequest updateUserRequest);
    boolean userChangePassword(User user, UserChangePasswordRequest userChangePasswordRequest);
    boolean userForgotPassword(User user, UserForgotPasswordRequest forgotPasswordRequest);
    boolean sendAndSaveVerificationCode(String userEmail);
    boolean checkVerificationCode(User user, String code) throws ParseException;
}
