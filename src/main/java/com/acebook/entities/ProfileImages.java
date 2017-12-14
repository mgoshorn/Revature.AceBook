package com.acebook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE_IMAGES")
public class ProfileImages {
	
	@Id
	@Column(name="user_id")
	private int userId;

	@Column(name="profile_photo")
	private String profileImage64;
	
	@Column(name="profile_photo_type")
	private String profileImageType;
	
	@Column(name="banner")
	private String bannerImage64;
	
	@Column(name="banner_type")
	private String bannerImageType;

	public ProfileImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfileImages(int userId, String profileImage64, String profileImageType, String bannerImage64,
			String bannerImageType) {
		super();
		this.userId = userId;
		this.profileImage64 = profileImage64;
		this.profileImageType = profileImageType;
		this.bannerImage64 = bannerImage64;
		this.bannerImageType = bannerImageType;
	}

	@Override
	public String toString() {
		return "ProfileImages [userId=" + userId + ", profileImage64=" + profileImage64 + ", profileImageType="
				+ profileImageType + ", bannerImage64=" + bannerImage64 + ", bannerImageType=" + bannerImageType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bannerImage64 == null) ? 0 : bannerImage64.hashCode());
		result = prime * result + ((bannerImageType == null) ? 0 : bannerImageType.hashCode());
		result = prime * result + ((profileImage64 == null) ? 0 : profileImage64.hashCode());
		result = prime * result + ((profileImageType == null) ? 0 : profileImageType.hashCode());
		result = prime * result + userId;
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
		ProfileImages other = (ProfileImages) obj;
		if (bannerImage64 == null) {
			if (other.bannerImage64 != null)
				return false;
		} else if (!bannerImage64.equals(other.bannerImage64))
			return false;
		if (bannerImageType == null) {
			if (other.bannerImageType != null)
				return false;
		} else if (!bannerImageType.equals(other.bannerImageType))
			return false;
		if (profileImage64 == null) {
			if (other.profileImage64 != null)
				return false;
		} else if (!profileImage64.equals(other.profileImage64))
			return false;
		if (profileImageType == null) {
			if (other.profileImageType != null)
				return false;
		} else if (!profileImageType.equals(other.profileImageType))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProfileImage64() {
		return profileImage64;
	}

	public void setProfileImage64(String profileImage64) {
		this.profileImage64 = profileImage64;
	}

	public String getProfileImageType() {
		return profileImageType;
	}

	public void setProfileImageType(String profileImageType) {
		this.profileImageType = profileImageType;
	}

	public String getBannerImage64() {
		return bannerImage64;
	}

	public void setBannerImage64(String bannerImage64) {
		this.bannerImage64 = bannerImage64;
	}

	public String getBannerImageType() {
		return bannerImageType;
	}

	public void setBannerImageType(String bannerImageType) {
		this.bannerImageType = bannerImageType;
	}

	
	
}

	