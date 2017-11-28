package model;

import java.sql.Date;

public class Seance {
	
	private int id;
	private Date date;
	private int idProces;
	
	public Seance(int id, Date date, int idProces) {
		super();
		this.id = id;
		this.date = date;
		this.idProces = idProces;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void print() {
		System.out.println(this.date);
		
	}

	public int getIdProces() {
		return idProces;
	}

	public void setIdProces(int idProces) {
		this.idProces = idProces;
	}
	
}
