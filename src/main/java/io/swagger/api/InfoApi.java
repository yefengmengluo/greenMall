package io.swagger.api;

import io.swagger.configuration.BaseService;
import io.swagger.model.*;

import io.swagger.model.Info;

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
@RequestMapping(value = "/info", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/info", description = "the info API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class InfoApi {

  @Autowired
  BaseService baseService;

  @ApiOperation(value = "Add Info", notes = "Add Info,This can only be done by the logged in user.", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid Info supplied"),
    @ApiResponse(code = 405, message = "Invalid input") })
  @RequestMapping(value = "", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.POST)
  public ResponseEntity<Void> addInfo(

@ApiParam(value = "Add Info object"  ) @RequestBody Info body
)
      throws NotFoundException {
      // do some magic!
      return baseService.getInfoService().addInfo(body);
  }

  

  @ApiOperation(value = "点击添加", notes = "{infoId}为某条用户曾经发送的供(求)id,body中的info为与之对应的info求(供)的json对象(只需要id即可)", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid Info supplied"),
    @ApiResponse(code = 405, message = "Invalid input") })
  @RequestMapping(value = "/clickInfo/{infoId}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.POST)
  public ResponseEntity<Void> addClickInfo(
@ApiParam(value = "针对某条供(求)id，发布一条求(供)消息",required=true ) @PathVariable("id") String id
,
@ApiParam(value = "Add Info object" ,required=true ) @RequestBody Info body
)
      throws NotFoundException {
      // do some magic!
      return baseService.getInfoService().addClickInfo(id, body);
  }

  

  @ApiOperation(value = "Get Info list by infoId", notes = "", response = Info.class, responseContainer = "List")
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Info not found") })
  @RequestMapping(value = "/guessList", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<List<Info>> guessList()
      throws NotFoundException {
      // do some magic!
    return baseService.getInfoService().guessList();
  }

  

  @ApiOperation(value = "Get Info list by infoId", notes = "", response = Info.class, responseContainer = "List")
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Info not found") })
  @RequestMapping(value = "/infoList", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<List<Info>> getInfoListBySpecificInfo(

@ApiParam(value = "根据某个供(求)信息，获得push过来的求(供)信息列表" ,required=true ) @RequestBody Info body
)
      throws NotFoundException {
      // do some magic!
    return baseService.getInfoService().getInfoListBySpecificInfo(body);
  }

  

  @ApiOperation(value = "指定某条信息发布一条纯文本供求", notes = "{infoId}为某条供(求)的id,body中的info为某条info求(供)的json对象，针对没有发布产品的情况", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid Info supplied"),
    @ApiResponse(code = 405, message = "Invalid input") })
  @RequestMapping(value = "/publishInfo/{infoId}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.POST)
  public ResponseEntity<Void> publishInfo(
@ApiParam(value = "针对某条供(求)id，发布一条求(供)消息",required=true ) @PathVariable("infoId") String infoId

,
    

@ApiParam(value = "Add Info object" ,required=true ) @RequestBody Info body
)
      throws NotFoundException {
      // do some magic!
    return baseService.getInfoService().publishInfo(infoId,body);
  }

  

  @ApiOperation(value = "Get Info by Info id", notes = "", response = Info.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid id supplied"),
    @ApiResponse(code = 404, message = "Info not found") })
  @RequestMapping(value = "/{id}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<Info> getInfoById(
@ApiParam(value = "The Info that needs to be fetched",required=true ) @PathVariable("id") String id

)
      throws NotFoundException {
      // do some magic!
    return baseService.getInfoService().getInfoById(id);
  }

  

  @ApiOperation(value = "Update Info", notes = "Update Info ,This can only be done by the logged in user.", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid Info supplied"),
    @ApiResponse(code = 404, message = "Info not found") })
  @RequestMapping(value = "/{id}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.PUT)
  public ResponseEntity<Void> updateInfo(
@ApiParam(value = "Info that need to be update",required=true ) @PathVariable("id") String id

,
    

@ApiParam(value = "Updated Info object"  ) @RequestBody Info body
)
      throws NotFoundException {
      // do some magic!
    return baseService.getInfoService().updateInfo(id,body);
  }

  
}
