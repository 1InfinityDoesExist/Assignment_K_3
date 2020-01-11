package com.primary.autowired.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.primary.autowired.entity.Avatar;
import com.primary.autowired.service.AvatarService;
import com.primary.autowired.service.ErrorMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/object/avatar")
@Api(value = "/api/object/avatar", description = "Avatar Crud Respository")
public class AvatarController {

	private static final Logger logger = LoggerFactory.getLogger(AvatarController.class);

	@Autowired
	private AvatarService avatarService;

	@Autowired
	private ErrorMap errorMapping;

	@RequestMapping(path = "saveResource", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "/create", notes = "Create Avatar Resource", response = Avatar.class)
	public ResponseEntity<?> createResource() throws UnirestException {
		logger.info("*********************Inside Post Operation of Avatar*****************************");

		Avatar avatar = null;

		HttpResponse<JsonNode> jsonNode = Unirest.get("https://reqres.in/api/users?page=2")
				.header("Accept", "application/json").header("Content-Type", "application/json").asJson();
		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object obj : jsonObject.keySet()) {
			String propName = (String) obj;

			switch (propName) {
			case "page":
				Object pageObject = jsonObject.get(propName);
				Integer pageValue = (Integer) pageObject;
				logger.info("PageValue:- " + pageValue);
				continue;

			case "per_page":
				Object perPageObject = jsonObject.get(propName);
				Integer perPageValue = (Integer) perPageObject;
				logger.info("PerPage:-" + perPageValue);
				continue;

			case "total":
				Object perTotal = jsonObject.get(propName);
				Integer totalValue = (Integer) perTotal;
				logger.info("Total:- " + perTotal);
				continue;

			case "total_pages":
				Object totalPage = jsonObject.get(propName);
				Integer totalPageValue = (Integer) totalPage;
				logger.info("Total Page:- " + totalPageValue);
				continue;

			case "data":
				JSONArray jsonArray = (JSONArray) jsonObject.get(propName);
				for (int iter = 0; iter < jsonArray.length(); iter++) {
					avatar = new Avatar();
					JSONObject dataJsonObject = (JSONObject) jsonArray.get(iter);
					for (Object dataObj : dataJsonObject.keySet()) {
						String dataPropName = (String) dataObj;
						switch (dataPropName) {
						case "id":
							continue;
						case "email":
							Object emailObject = dataJsonObject.get(dataPropName);
							String emailValue = (String) emailObject;
							avatar.setEmail(emailValue);
							continue;

						case "first_name":
							Object firstNameObject = dataJsonObject.get(dataPropName);
							String firstNameValue = (String) firstNameObject;
							avatar.setFirstName(firstNameValue);
							continue;

						case "last_name":
							Object lastNameObject = dataJsonObject.get(dataPropName);
							String lastNameValue = (String) lastNameObject;
							avatar.setLastName(lastNameValue);
							continue;

						case "avatar":
							Object avatarObject = dataJsonObject.get(dataPropName);
							String avatarValue = (String) avatarObject;
							avatar.setAvatar(avatarValue);
							continue;
						default:
							logger.info("*****************End Of Data Switch Case********************");
						}
					}
				}
				continue;

			default:
				logger.info("*************End of Switch Case*******************");
			}
		}

		Avatar avatarFromDB = avatarService.createResource(avatar);
		return new ResponseEntity<Avatar>(avatarFromDB, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "/get", notes = "Get Resource By Avatar", response = Avatar.class)
	public ResponseEntity<?> getResource(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		logger.info("*************************Get Resource By ID*****************************");
		Avatar avatar = avatarService.getAvatarById(id);
		if (avatar == null) {
			return new ResponseEntity<Avatar>(avatar, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "/get", notes = "Get Resource By Avatar", response = Avatar.class, responseContainer = "LIST")
	public ResponseEntity<?> getAllResource() {

		logger.info("******************************Update Resource By ID*****************************");
		List<Avatar> listOfAvatar = avatarService.getAllAvatar();
		if (listOfAvatar == null || listOfAvatar.size() == 0) {
			return new ResponseEntity<String>("Could Not Retrieve Data From DataBase", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Avatar>>(listOfAvatar, HttpStatus.OK);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete", notes = "Remove Avatar Resource From DB", response = String.class)
	public ResponseEntity<?> deleteResource(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {
		logger.info("**********************************Delete Resource By ID******************************");
		String response = avatarService.deleteAvatarBy(id);
		if (response == null) {
			return new ResponseEntity<String>("Could Not Retrieve Data From DataBase", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PATCH, produces = "applicaion/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update", notes = "Update Avatar Resource", response = Avatar.class)
	public ResponseEntity<?> updateAvtar(@Valid @RequestBody String avatar,
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Long id) {

		logger.info("***********Inside The Update Avatar Resource***********************");
		Avatar avatarFromDB = avatarService.updateAvatarById();
		if (avatarFromDB == null) {
			return new ResponseEntity<String>("Sorry Coud Not Retrieve Data From DataBase", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Avatar>(avatarFromDB, HttpStatus.OK);

	}

}
