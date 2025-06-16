package com.hospitalAuth.AuthHMS.Exception.custom;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
