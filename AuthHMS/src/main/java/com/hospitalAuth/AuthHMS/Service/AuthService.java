package com.hospitalAuth.AuthHMS.Service;

import com.hospitalAuth.AuthHMS.Dto.AuthResponse;
import com.hospitalAuth.AuthHMS.Dto.LoginRequest;
import com.hospitalAuth.AuthHMS.Dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest req);

    AuthResponse login(LoginRequest req);
}
