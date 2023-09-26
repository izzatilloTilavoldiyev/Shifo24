package com.company.shifo24.service.auth;

import com.company.shifo24.domains.dtos.request.LoginDTO;
import com.company.shifo24.domains.dtos.response.TokenDTO;

public interface AuthService {

    TokenDTO login(LoginDTO loginDTO);

    TokenDTO refreshToken(String refreshToken);

}
