package io.swagger.api;

import io.swagger.configuration.BaseService;
import io.swagger.model.*;

import io.swagger.model.Gcategory;

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
@RequestMapping(value = "/category", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/category", description = "the category API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T13:14:52.242+08:00")
public class CategoryApi {
  @Autowired
  BaseService baseService;

  @ApiOperation(value = "获得产品分类", notes = "通过parentId获得产品分类", response = Gcategory.class, responseContainer = "List")
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid level supplied"),
    @ApiResponse(code = 404, message = "Area list not found") })
  @RequestMapping(value = "/{pid}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<List<Gcategory>> getCategoryByPid(
@ApiParam(value = "取pid为给定值的所有Gcategory的list",required=true ) @PathVariable("pid") String pid

)
      throws NotFoundException {
      // do some magic!
      return baseService.getCategoryService().getCategoryByPid(pid);
  }

  
}
