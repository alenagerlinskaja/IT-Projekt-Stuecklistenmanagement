package de.hdm.gruppe3.itprojekt.shared.bo;

import java.io.Serializable;


public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {

		return this.getClass().getName() + " #" + this.id;
	}

	public boolean equals(Object object) {

		if (object != null && object instanceof BusinessObject) {
			BusinessObject businessObject = (BusinessObject) object;
			try {
				if (businessObject.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {

				return false;
			}
		}

		return false;
	}
}
