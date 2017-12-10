package com.acebook.beans;

public class PostRequest {
	Credentials credentials;
	String postbody;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((postbody == null) ? 0 : postbody.hashCode());
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
		PostRequest other = (PostRequest) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (postbody == null) {
			if (other.postbody != null)
				return false;
		} else if (!postbody.equals(other.postbody))
			return false;
		return true;
	}

	public PostRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostRequest(Credentials credentials, String postbody) {
		super();
		this.credentials = credentials;
		this.postbody = postbody;
	}

	@Override
	public String toString() {
		return "PostRequest [credentials=" + credentials + ", postbody=" + postbody + "]";
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getPostbody() {
		return postbody;
	}

	public void setPostbody(String postbody) {
		this.postbody = postbody;
	}

}
