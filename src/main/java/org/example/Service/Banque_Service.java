package org.example.Service;

import org.example.connexion.SingletonConnection;
import org.example.metier.compte;
import org.example.metier.historique;
import org.example.metier.lesactions;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
@WebService(serviceName = "BanqueWS")
public class Banque_Service {

	public Banque_Service() {
	}

	@WebMethod(operationName = "retirer")
	public  int retirer(compte c, float mont) {
		Connection conn = SingletonConnection.getConnection();
		int i = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde() - mont);
			ps.setInt(2, c.getCode());
			i = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@WebMethod(operationName = "verser")
	public int verser(compte c, Float mont) {
		Connection conn = SingletonConnection.getConnection();
		int i = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde() + mont);
			ps.setInt(2, c.getCode());
			i = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@WebMethod(operationName = "transfert")
	public int transfert(compte c, Float mont) {
		Connection conn = SingletonConnection.getConnection();
		int i = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE compte SET solde=? WHERE codecin=?;");
			ps.setFloat(1, c.getSolde() + mont);
			ps.setInt(2, c.getCode());
			i = ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@WebMethod
	public List<compte> getComptes() {
		Connection conn = SingletonConnection.getConnection();
		List<compte> p = new ArrayList();
		try {

			PreparedStatement ps = conn.prepareStatement("select * from compte");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.add(new compte(rs.getInt("codecin"), rs.getFloat("solde"), rs.getString("pass")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@WebMethod
	public compte getCompte(int codecin) {
		Connection conn = SingletonConnection.getConnection();
		compte c = new compte();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from compte where codeCin = ?");
			ps.setInt(1, codecin);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setCode(rs.getInt("codecin"));
				c.setSolde(rs.getFloat("solde"));
				c.setPass(rs.getString("pass"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@WebMethod(operationName = "saveCompte")
	public compte saveUser(@WebParam compte c) {
		Connection conn = SingletonConnection.getConnection();
		try {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO compte VALUES(?,?,?)");

			ps.setInt(1, c.getCode());
			ps.setFloat(2, 0f);
			ps.setString(3, c.getPass());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@WebMethod(operationName = "exist")
	public boolean exist(@WebParam compte c) {
		Connection conn = (Connection) SingletonConnection.getConnection();
		try {

			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement("select * from compte where codeCin= ? and pass = ?");

			ps.setInt(1, c.getCode());
			ps.setString(2, c.getPass());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@WebMethod(operationName = "setHistorique")
	public int setHistorique(int codecin, int idActe) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
		Connection conn = SingletonConnection.getConnection();
		int i = 0;
		try {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO historique VALUES(?,?,?)");

			ps.setInt(1, codecin);
			ps.setInt(2, idActe);
			ps.setTimestamp(3, currentTimestamp);
			;
			i = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@WebMethod
	public List<historique> getHist(int codecin) {
		Connection conn = SingletonConnection.getConnection();
		List<historique> p = new ArrayList();
		try {

			PreparedStatement ps = conn.prepareStatement("select * from historique where codecin =?");
			ps.setInt(1, codecin);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String formattedDate = dateFormat.format(rs.getTimestamp("date"));
				p.add(new historique(rs.getInt("codecin"), rs.getInt("idActe"), formattedDate));

			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@WebMethod
	public String getActe(int idcode) {
		Connection conn = SingletonConnection.getConnection();
		lesactions act = new lesactions();
		try {

			PreparedStatement ps = conn.prepareStatement("select * from lesactions where id =?");
			ps.setInt(1, idcode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				act.setId(rs.getInt("id"));
				act.setAction(rs.getString("action"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return act.getAction();
	}

}