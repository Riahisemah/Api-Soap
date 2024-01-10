package org.example.metier;

import java.sql.Date;
import java.sql.Timestamp;

public class historique {
	private int codecin;
	private int idActe;
	private String date;

	public historique(int codecin, int idActe, String date) {
		super();
		this.codecin = codecin;
		this.idActe = idActe;
		this.date = date;
	}

	public int getCodecin() {
		return codecin;
	}

	public void setCodecin(int codecin) {
		this.codecin = codecin;
	}

	public int getIdActe() {
		return idActe;
	}

	public void setIdActe(int idActe) {
		this.idActe = idActe;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "historique [codecin=" + codecin + ", idActe=" + idActe + ", date=" + date + "]";
	}

}
