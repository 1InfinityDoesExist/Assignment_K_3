package com.primary.autowired.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.primary.autowired.entity.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long>, CrudRepository<Avatar, Long> {

	@Query("Select Avatar from #{#entityName} Avatar where id = ?1")
	public Avatar getAvatarByID(Long id);

	@Query("Select Avatar from #{#entityName} Avatar")
	public List<Avatar> getAllAvatar();

}
