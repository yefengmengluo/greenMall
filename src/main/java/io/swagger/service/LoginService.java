package io.swagger.service;

import io.swagger.model.LoginToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by cc on 16-1-29.
 */
public interface LoginService {

    /**
     * 添加一条登陆记录，即注册
     * @param loginToken
     * @return
     */
    public ResponseEntity<String> loginToken(LoginToken loginToken);

    /**
     * 登录
     * @param loginToken
     * @return
     */
    public ResponseEntity<String> loginUser(LoginToken loginToken);

    /**
     * 退出登录，即注销
     * @param loginToken
     * @return
     */
    public ResponseEntity<String> logoutUser(LoginToken loginToken);

}
