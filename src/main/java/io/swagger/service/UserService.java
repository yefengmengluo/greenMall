package io.swagger.service;

import io.swagger.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by cc on 16-1-29.
 */
public interface UserService {

    /**
     * 通过person的id获得当前用户
     * @param userId
     * @return
     */
    public ResponseEntity<User> getUserByUserId(String userId);;

    /**
     * 根据用户名更新用户
     * @param userId
     * @param body
     * @return
     */
    public ResponseEntity<Void> updateUser(String userId, User body);



}
