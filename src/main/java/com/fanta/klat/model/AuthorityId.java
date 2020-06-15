package com.fanta.klat.model;

import java.io.Serializable;

public class AuthorityId implements Serializable {
	private static final long serialVersionUID = 1L;
	private int mNum;
	private String authority;

<<<<<<< HEAD
	public AuthorityId() {
=======
	AuthorityId() {
>>>>>>> refs/heads/amy

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + mNum;
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
		AuthorityId other = (AuthorityId) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (mNum != other.mNum)
			return false;
		return true;
	}

}
