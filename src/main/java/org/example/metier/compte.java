package org.example.metier;

import java.util.Date;

public class compte {
	private int codecin;
	private float solde;
	private String pass;

	public compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public compte(int code, float solde, String pass) {
		super();
		this.codecin = code;
		this.solde = solde;
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCode() {
		return codecin;
	}

	public void setCode(int code) {
		this.codecin = code;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}
}
