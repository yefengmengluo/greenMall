package io.swagger.api;

import io.swagger.configuration.BaseService;
import io.swagger.model.*;

import io.swagger.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping(value = "/users", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/users", description = "the users API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T13:14:52.242+08:00")
public class UsersApi {
  @Autowired
  BaseService baseService;

  @ApiOperation(value = "获得用户", notes = "根据personId获得用户Id", response = User.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid username supplied"),
    @ApiResponse(code = 404, message = "User not found") })
  @RequestMapping(value = "/{userId}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<User> getUserByUserId(
@ApiParam(value = "personId,根据用户person的id获得用户id",required=true ) @PathVariable("userId") String userId

)
      throws NotFoundException {
      // do some magic!
    return baseService.getUserService().getUserByUserId(userId);
  }

  

  @ApiOperation(value = "更新用户信息", notes = "通过用户id更新用户信息", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid user supplied"),
    @ApiResponse(code = 404, message = "User not found") })
  @RequestMapping(value = "/{userId}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.POST)
  public ResponseEntity<Void> updateUser(
@ApiParam(value = "用户名，用户查询出登陆凭证",required=true ) @PathVariable("userId") String userId

,
    

@ApiParam(value = "要添加的用户放到post请求body中,提交给后台"  ) @RequestBody User body
)
      throws NotFoundException {
      // do some magic!
    return baseService.getUserService().updateUser(userId, body);
  }

  
}
