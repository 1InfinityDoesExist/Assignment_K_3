package com.primary.autowired.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Avatar")
@Table(name = "avatar_table")
@ApiModel(value = "Avatar Class", description = "Avatar Class Peoperties")
public class Avatar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key Of Avatar Class")
	private Long id;

	@Email
	@Column(name = "email", nullable = false, updatable = true, unique = true)
	@NotEmpty(message = "Email Field Must Not Be Empty")
	@NotBlank(message = "Email Field Must Not Be Blank")
	@ApiModelProperty(notes = "Avatar Properties :-  email")
	private String email;

	@Column(name = "first_name")
	@Size(min = 5, max = 10, message = "First Name Length Must Be Between 5 to 10")
	@ApiModelProperty(notes = "Avatar Properties = :- firstName")
	private String firstName;

	@Column(name = "last_name")
	@Size(min = 5, max = 10, message = "First Name Length Must Be Between 5 to 10")
	@ApiModelProperty(notes = "Avatar Properties = :- firstName")
	private String lastName;

	@Column(name = "avatar")
	@NotBlank(message = "Avatar Cannot be Blank")
	@NotEmpty(message = "Avatar Cannot Be Empty")
	private String avatar;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "creation_date")
	@ApiModelProperty(notes = "Creation Date Of Avatar")
	private LocalDateTime creationDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "modification_date")
	@ApiModelProperty(notes = "Modification Date of Avatar")
	private LocalDateTime modificationDate;

	public Avatar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avatar(Long id,
			@Email @NotEmpty(message = "Email Field Must Not Be Empty") @NotBlank(message = "Email Field Must Not Be Blank") String email,
			@Size(min = 5, max = 10, message = "First Name Length Must Be Between 5 to 10") String firstName,
			@Size(min = 5, max = 10, message = "First Name Length Must Be Between 5 to 10") String lastName,
			@NotBlank(message = "Avatar Cannot be Blank") @NotEmpty(message = "Avatar Cannot Be Empty") String avatar) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avatar other = (Avatar) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avatar [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", avatar=" + avatar + "]";
	}

}
