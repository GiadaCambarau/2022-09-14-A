package it.polito.tdp.itunes.model;

import java.util.Objects;

public class Coppie {
	private Album a1;
	private Album a2;
	
	public Coppie(Album a1, Album a2) {
		super();
		this.a1 = a1;
		this.a2 = a2;
	}

	public Album getA1() {
		return a1;
	}

	public void setA1(Album a1) {
		this.a1 = a1;
	}

	public Album getA2() {
		return a2;
	}

	public void setA2(Album a2) {
		this.a2 = a2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a1, a2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coppie other = (Coppie) obj;
		return Objects.equals(a1, other.a1) && Objects.equals(a2, other.a2);
	}
	
	

}
