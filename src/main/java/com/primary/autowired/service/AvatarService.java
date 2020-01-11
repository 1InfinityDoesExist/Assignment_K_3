package com.primary.autowired.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primary.autowired.Exception.MyException;
import com.primary.autowired.entity.Avatar;
import com.primary.autowired.repository.AvatarRepository;

@Service
public class AvatarService {

	private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);

	@Autowired
	private AvatarRepository avatarRepository;

	public Avatar createResource(Avatar avatar) {
		Avatar avatarResponse = null;
		try {
			avatarResponse = avatarRepository.save(avatar);
			if (avatarResponse == null) {
				throw new MyException("Sorry Could Not Persist Data In The Database");
			}
			return avatarResponse;
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return avatarResponse;
	}

	public Avatar getAvatarById(Long id) {
		Avatar avatarFromDB = null;
		try {
			avatarFromDB = avatarRepository.getAvatarByID(id);
			if (avatarFromDB == null) {
				throw new MyException("Sorry Could Not Retrieve Data From The Database");
			}
		} catch (final MyException ex) {
			logger.debug(ex.getMessage());
		}
		return avatarFromDB;
	}

	public List<Avatar> getAllAvatar() {
		List<Avatar> listOfAvatar = null;
		try {
			listOfAvatar = avatarRepository.getAllAvatar();
			if (listOfAvatar == null || listOfAvatar.size() == 0) {
				throw new MyException("Sorry Could Not Retrieve Data From DB");
			}
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return listOfAvatar;
	}

	public String deleteAvatarBy(Long id) {
		Avatar avatarFromDB = getAvatarById(id);
		String response = null;
		if (avatarFromDB == null) {
			throw new MyException("Sorry No Data Found For This Id" + id);
		}
		try {
			avatarRepository.deleteById(id);
			response = "SuccessFully Deleted";
		} catch (final MyException ex) {
			logger.error(ex.getMessage());
		}
		return response;
	}

	public Avatar updateAvatarById() {
		return null;
	}

}
