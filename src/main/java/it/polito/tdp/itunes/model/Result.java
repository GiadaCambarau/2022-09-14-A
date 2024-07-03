package it.polito.tdp.itunes.model;

import java.util.Objects;

public class Result {
	private int connesse;
	private double durata;
	public Result(int connesse, double durata) {
		super();
		this.connesse = connesse;
		this.durata = durata;
	}
	public int getConnesse() {
		return connesse;
	}
	public void setConnesse(int connesse) {
		this.connesse = connesse;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	@Override
	public int hashCode() {
		return Objects.hash(connesse, durata);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		return connesse == other.connesse && Double.doubleToLongBits(durata) == Double.doubleToLongBits(other.durata);
	}
	
	

}
