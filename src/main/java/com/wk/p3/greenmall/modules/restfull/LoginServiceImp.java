package com.wk.p3.greenmall.modules.restfull;

import io.swagger.model.LoginToken;
import io.swagger.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cc on 16-2-16.
 */
@Service
@Transactional(readOnly = true)
public class LoginServiceImp implements LoginService {
    @Override
    public ResponseEntity<String> loginToken(LoginToken loginToken) {
        return null;
    }

    @Override
    public ResponseEntity<String> loginUser(LoginToken loginToken) {
        return null;
    }

    @Override
    public ResponseEntity<String> logoutUser(LoginToken loginToken) {
        return null;
    }

}
