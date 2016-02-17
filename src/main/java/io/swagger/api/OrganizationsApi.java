package io.swagger.api;

import io.swagger.model.*;

import io.swagger.model.Organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

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
@RequestMapping(value = "/organizations", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/organizations", description = "the organizations API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-01-29T15:24:50.663+08:00")
public class OrganizationsApi {
  

  @ApiOperation(value = "Get Organization by user name", notes = "Get Organization by user name", response = Organization.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "successful operation"),
    @ApiResponse(code = 400, message = "Invalid username supplied"),
    @ApiResponse(code = 404, message = "Organization not found") })
  @RequestMapping(value = "/{username}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.GET)
  public ResponseEntity<Organization> getOrganizationByName(
@ApiParam(value = "The organizations which is related to the username of user that needs to be fetched",required=true ) @PathVariable("username") String username

)
      throws NotFoundException {
      // do some magic!
      return new ResponseEntity<Organization>(HttpStatus.OK);
  }

  

  @ApiOperation(value = "Updated Organization", notes = "This can only be done by the logged in user.", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid user supplied"),
    @ApiResponse(code = 404, message = "Organization not found") })
  @RequestMapping(value = "/{username}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.PUT)
  public ResponseEntity<Void> updateOrganization(
@ApiParam(value = "Organization that need to be update",required=true ) @PathVariable("username") String username

,
    

@ApiParam(value = "Updated Organization object"  ) @RequestBody Organization body
)
      throws NotFoundException {
      // do some magic!
      return new ResponseEntity<Void>(HttpStatus.OK);
  }

  

  @ApiOperation(value = "Delete Organization", notes = "This can only be done by the logged in user.", response = Void.class)
  @ApiResponses(value = { 
    @ApiResponse(code = 400, message = "Invalid username supplied"),
    @ApiResponse(code = 404, message = "Organization not found") })
  @RequestMapping(value = "/{username}", 
    produces = { "application/json", "application/xml" }, 
    
    method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteOrganization(
@ApiParam(value = "The Organization that needs to be deleted",required=true ) @PathVariable("username") String username

)
      throws NotFoundException {
      // do some magic!
      return new ResponseEntity<Void>(HttpStatus.OK);
  }

  
}
