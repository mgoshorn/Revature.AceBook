package com.acebook.beans;

public class ProfilePhotoUpload {
	private Credentials credentials;
	private String fileType;
	private String image;

	public ProfilePhotoUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfilePhotoUpload(Credentials credentials, String fileType, String image) {
		super();
		this.credentials = credentials;
		this.fileType = fileType;
		this.image = image;
	}

	@Override
	public String toString() {
		return "ProfilePhotoUpload [credentials=" + credentials + ", fileType=" + fileType + ", image=" + image + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		ProfilePhotoUpload other = (ProfilePhotoUpload) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
