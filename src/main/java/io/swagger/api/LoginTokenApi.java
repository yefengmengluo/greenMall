package io.swagger.api;

import io.swagger.model.LoginToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import io.swagger.configuration.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping(value = "/loginToken", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/loginToken", description = "the loginToken API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-01-29T15:24:50.663+08:00")
public class LoginTokenApi{

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "注册用户", notes = "在http的Post请求中携带一个LoginToken实例对象,在后台添加一个登陆凭证", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "添加用户成功"),
            @ApiResponse(code = 400, message = "用户名或密码格式有误")})
    @RequestMapping(value = "",
            produces = {"application/json", "application/xml"},

            method = RequestMethod.POST)
    public ResponseEntity<String> loginToken(

            @ApiParam(value = "注册用户,参数放到body中", required = true) @RequestBody LoginToken loginToken
    )
            throws NotFoundException {
        return baseService.getLoginService().loginToken(loginToken);
    }


    @ApiOperation(value = "使用LoginToken实例登陆系统", notes = "登陆系统", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登陆成功"),
            @ApiResponse(code = 400, message = "登陆失败,用户名或密码错误")})
    @RequestMapping(value = "/login",
            produces = {"application/json", "application/xml"},

            method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(

            @ApiParam(value = "LoginToken放在post请求中,发送到服务器实现登陆", required = true) @RequestBody LoginToken loginToken
    )
            throws NotFoundException {
        return baseService.getLoginService().loginUser(loginToken);
    }


    @ApiOperation(value = "注销", notes = "退出登陆", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "退出登陆成功"),
            @ApiResponse(code = 400, message = "用户名和密码错误")})
    @RequestMapping(value = "/logout",
            produces = {"application/json", "application/xml"},

            method = RequestMethod.POST)
    public ResponseEntity<String> logoutUser(

            @ApiParam(value = "使用用户名和密码实现退出登录", required = true) @RequestBody LoginToken loginToken
    )
            throws NotFoundException {
        return baseService.getLoginService().logoutUser(loginToken);
    }


}
